/**
 * Title: Application
 * Tag: 
 * Update: 2015/08/05
 * Description: 
 */

public class TestApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Inject
    String tag;

    @Override
    public void onCreate() {
	super.onCreate();

	this.applicationComponent = DaggerApplicationComponent.builder().
					applicationModule(new ApplicationModule(this)). //此Component所对应的Module实例化，如果有dependencies，那么此之前也要调用
					build();
    }

    public ApplicationComponent getApplicationComponent(){
	return applicationComponent;
    }
}
