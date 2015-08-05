/**
 * Title: ApplicationModule
 * Tag: 
 * Update: 2015/08/05
 * Description: 
 */

@Module
public class ApplicationModule {

    private Context mContext;

    public ApplicationModule(Context context){
	mContext = context;
    }

    //提供依赖给Component
    @Provides
    @Singleton
    Context provideContext(){
	return mContext;
    }

    @Provides
    Double provideDouble(){
	return 4.5;
    }

}
