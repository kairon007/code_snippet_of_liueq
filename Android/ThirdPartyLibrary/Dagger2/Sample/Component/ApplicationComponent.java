/**
 * Title: ApplicationComponent
 * Tag: 
 * Update: 2015/08/05
 * Description: 作为整个App的root set
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(Activity activity); //将Activity纳入Object Graph中，可以对其中的@Inject进行注入

    //作为Component中的dependencies的Component，如果想要在下一层的Module中能够使用提供的依赖，需要在这里暴露出来，返回可以使用的类型
    //如果是@Inject的情况，Module中定义的就可以直接使用
    Double getDouble();
}
