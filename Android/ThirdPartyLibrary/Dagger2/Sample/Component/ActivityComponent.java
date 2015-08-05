/**
 * Title: ActivityComponent
 * Tag: 
 * Update: 2015/08/05
 * Description: 使用局部的Component
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)       //在使用了dependencies之后，并不意味着可以使用所有上一层Graph中的依赖，只能使用上一层中在Component有定义返回方法的依赖类型。(这只是针对在Component中使用上一层依赖作为参数的情况，而上一层依赖在没有暴露给下一层的情况下，也是可以被@Inject所使用的，只要该位置是在整个Graph中)
public interface ActivityComponent {

    //将传入的对象纳入Graph中，对其中的@Inject进行依赖注入
    void inject(MainActivity mainActivity); //是否有此方法决定了能否直接在activity中使用@Inject

    //只要调用maker方法，就可以生成一个ModeClass实例
    //可以在定义的时候就在这里显式地传入一些参数，而缺少参数的部分，是有Component提供
    ModeClass maker();
}

