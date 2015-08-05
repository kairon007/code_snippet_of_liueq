/**
 * Title: ActivityModule
 * Tag: 
 * Update: 2015/08/05
 * Description: 
 */

@Module
public class ActivityModule {

    public ActivityModule(){}

    @Provides
    Integer provideInteger(){
	return 1;
    }

    //需要的参数由上一层的依赖提供，前提是已经暴露给此层
    @Provides
    String provideId(Double d){
	return "hello world" + d;
    }

    @Provides
    ModeClass provideModeClass(String id){
	try {
	    Thread.sleep(10);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
	return new ModeClass(id, Integer.valueOf(String.valueOf(System.currentTimeMillis()/1000)));
    }
}
