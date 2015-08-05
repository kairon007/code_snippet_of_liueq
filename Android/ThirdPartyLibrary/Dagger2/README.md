*Title: Dagger 2 Tutorial*

*Update: 2015/08/05*

### Declaring Dependencies

Dagger构建你的应用中类的实例，满足它们的依赖。它使用了javax.inject.Inject来标识那些构造方法或者域它是感兴趣的。

使用@Inject来标识那些想要使用Dagger来帮助创建实例的构造方法。当需要一个新的实例的时候，Dagger会获取需要的参数，并且引入到构造方法中。

```java
class Thermosiphon implements Pump {
    private final Heater heater;

    @Inject
	Thermosiphon(Heater heater) {
	    this.heater = heater;
	}

    ...
}
```

Dagger能够直接注入域的实例。下面这个例子就是使用Dagger直接生成了heater和pump的实例

```java
class CoffeeMaker {
    @Inject Heater heater;
    @Inject Pump pump;

    ...
}
```

如果你的类在field上使用了@Inejct而没有在constructor上使用@Inject标注，Dagger会注入这些fields，如果请求的话，但是不会创建一个新的实例（这里的实例是指field还是construtor？？）。加入一个无参的constructor，使用@Inject标识，这会让Dagger创建一个新的实例。

Dagger同样也可以支持method注入，但是construtor和field注入更加推荐。

缺少@Inject的类无法被Dagger所构建。

### Satisfying Dependencies

默认来所，Dagger满足每一个依赖是通过构建上述请求的实例。当你请求了一个CoffeeMaker，它会获取的一个new CoffeeMaker()以及设置到能够注入的field中。

但是@Inject在某些情况下无效：

* 接口无法被构建
* 第三方类无法被标注（是指第三方库？？）
* 需要配置的对象必须被配置

(原文是
 Interfaces can't be constructed.
 Third-party classes can't be annotated.
 Configurable objects must be configured!)

对于这些情况，@Inject是不足或者难以使用的，可以使用@Provides来标注method从而提供依赖。这些方法的返回类型定义了它所能满足的依赖。

例如，provideHeater()会被调用，当一个Heater被需要的时候

```java
@Provides Heater provideHeater() {
    return new ElectricHeater();
}

```

对于@Provides修饰的方法来说，它自己的参数也可以是依赖于其他。例如providePump方法中，依赖pump对象

```java
@Provides Pump providePump(Thermosiphon pump) {
    return pump;
}
```

所有的@Provides 方法都必须属于module。以下便是一个拥有@Module标记的class。

```java
@Module
class DripCoffeeModule {
    @Provides Heater provideHeater() {
	return new ElectricHeater();
    }

    @Provides Pump providePump(Thermosiphon pump) {
	return pump;
    }
}

```

出于习惯，@Provides方法都以provide开头，module类都以Module结尾。

###Building the Graph

@Inject和@Provides所标记的类构成了一个图，连接着它们的依赖。启动代码，例如main方法或者是Android Application对象访问此图的良好定义的根集合。在Dagger2中，这样的集合是被定义为一个接口，方法没有参数，返回所需要的类型。使用@Component来表示此类接口，并且在其中定义module作为参数。Dagger2会完全处理实现。

    ```java
@Component(modules = DripCoffeeModule.class)
    interface CoffeeShop {
	CoffeeMaker maker();
    }

```
这样的实现的名称是由Dagger + 接口名构成。调用builder()方法来设置依赖和build一个新的实例。

    ```java
    CoffeeShop coffeeShop = DaggerCoffeeShop.builder()
.dripCoffeeModule(new DripCoffeeModule())
    .build();

    ```

    任何有着可访问的无参构造方法的module都可以被省略，因为builder会自动创建一个实例，如果没有任何设置。如果所有的依赖都是可以省略的时候，可以直接用create()方法来创建此Component的实例。

    ```java
    CoffeeShop coffeeShop = DaggerCoffeeShop.create();
    ```

    现在，我们的coffeeApp能够简单地由Dagger生成CoffeeShop实例，并且生成一个完全注入的CoffeeMaker.

    ```java
    public class CoffeeApp {
	public static void main(String[] args) {
	    CoffeeShop coffeeShop = DaggerCoffeeShop.create();
	    coffeeShop.maker().brew();
	}
    }
```

### Singletons and Scoped Bindings

标记@Provides方法或者依赖的类为@Singleton.整个Graph中只会提供一个单例对象。

```java
@Provides @Singleton Heater provideHeater() {
    return new ElectricHeater();
}
```

在可被注入类上标记的@Singleton同样可以为文档服务。值得注意的是，在多线程中，不能保证Singleton对象只有一个。

```java
@Singleton
class CoffeeMaker {
    ...
}

```

因为Dagger 2是将实现Component的实例的作用范围和Component本身所联系起来的，所以需要在Component上声明此Component的范围。例如，不会在一个Component上同时声明@Singleton和@RequestScoped，因为这两个scope的生命周期本来就不同。为了给Component声明其作用的范围，只需要在Component上标注即可。

    ```java
@Component(modules = DripCoffeeModule.class)
    @Singleton
    interface CoffeeShop {
	CoffeeMaker maker();
    }

```

### Lazy injections

有时候，你需要一个对象惰性实例化。对于任何的依赖，使用Lazy<T>这样的方式来延迟对象实例化，直到调用了Lazy<T> 的get()方法。如果T是一个Singleton对象，那么整个Graph中调用的都是同一对象。反之，每一处注入地方都会获得它自己的Lazy<T>对象，但是如果在同一个注入地方反复地调用get()方法，还是会返回同一对象。

```java
class GridingCoffeeMaker {
    @Inject Lazy<Grinder> lazyGrinder;

    public void brew() {
	while (needsGrinding()) {
	    // Grinder created once on first call to .get() and cached.
	    lazyGrinder.get().grind();
	}
    }
}

```


### Provider injections

有时候，你需要返回多个实例而不是只获取一个。此时你有多重选择(Factories,Builders等)，一种方法是使用Provider<T> 来代替T。当get()被调用的时候，Provider<T>都会引用生成逻辑来构建一个新对象。如果生成逻辑是一个@Inject constructor，那么每一次都会有一个新的实例被创建，而@Provides方法不会保证这一点。

```java
class BigCoffeeMaker {
    @Inject Provider<Filter> filterProvider;

    public void brew(int numberOfPots) {
	...
	    for (int p = 0; p < numberOfPots; p++) {
		maker.addFilter(filterProvider.get()); //new filter every time.
		maker.addCoffee(...);
		maker.percolate();
		...
	    }
    }
}
```

### Qualifiers

有时候，只依靠类型是无法足以区分依赖的。例如，一个复杂的coffee maker可能会有多重heater。

这种情况下，我们可以加入qualifier annotation(限定词)。任何一种annotation中包含了@Qualifier即可。以下是@Named的情况。

```java
@Qualifier
    @Documented
@Retention(RUNTIME)
    public @interface Named {
	String value() default "";
    }

```

你可以创建自己的限定词标记，或者继续使用@Named.应用限定词在@Inject或者@Provides之后，Dagger会根据次来进行匹配.

```java
class ExpensiveCoffeeMaker {
    @Inject @Named("water") Heater waterHeater;
    @Inject @Named("hot plate") Heater hotPlateHeater;
    ...
}


@Provides @Named("hot plate") Heater provideHotPlateHeater() {
    return new ElectricHeater(70);
}

@Provides @Named("water") Heater provideWaterHeater() {
    return new ElectricHeater(93);
}

```
