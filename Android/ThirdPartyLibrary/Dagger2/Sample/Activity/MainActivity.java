/**
 * Title: MainActivity
 * Tag: 
 * Update: 2015/08/05
 * Description: 
 */

private void doInject(){

    //实例化Component
    activityComponent = DaggerActivityComponent.builder().
					    applicationComponent(((TestApplication)getApplication()).getApplicationComponent()). //依赖的Component也要传入
					    activityModule(new ActivityModule()) //对应的Module
					    .build(); //生成Component

    mc = activityComponent.maker();//直接调用Component方法来生成实例
    activityComponent.inject(this);//将此Activty纳入ObjectGraph
}
