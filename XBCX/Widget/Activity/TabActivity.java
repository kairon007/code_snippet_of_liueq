/*
 *  Title: TabActivity使用
 *  Tag: Tab
 *  Update Time: 2015/07/01
 *  Description: 快速使用TabActivity，需要继承这里的TabActivity
 */

//子类会调用到的几个方法
protected void reloadTab(){
    getLocalActivityManager().removeAllActivities();
    getTabHost().clearAllTabs();
    mTabClasses.clear();

    addTab(TabOrderActivity.class, R.string.tab_order, R.drawable.selector_tab_order);		    
    addTab(TabMyActivity.class, R.string.tab_my, R.drawable.selector_tab_my);
}

@Override
public void onTabChanged(String tabId) {
    if(getTabWidget().getVisibility() != View.VISIBLE){
	showTabWidget();
    }
}

public void hideTabWidget(){
    if(getTabHost().getCurrentTab() == 0){
	getTabWidget().setVisibility(View.GONE);
    }
}

public void showTabWidget(){
    getTabWidget().setVisibility(View.VISIBLE);
}



BaseTabActivity
public class BaseTabActivity extends TabActivity{

    protected   List<Class<?>>  mTabClasses = new ArrayList<Class<?>>();

    protected void addTab(Class<?> cls,int textId,int iconId){
	addTab(cls, getString(textId), iconId);
    }

    protected void addTab(Class<?> cls,String text,int iconId){
	final TabHost tabHost = getTabHost();
	final TabSpec tabSpec = tabHost.newTabSpec(cls.getName());

	final TextView tv = (TextView)SystemUtils.inflate(this,R.layout.textview_tab);	//自定义Tab位置和样式
	tv.setText(text);
	tv.setCompoundDrawablesWithIntrinsicBounds(0, iconId, 0, 0);
	Intent intent = new Intent(this,cls);
	final Intent data = getIntent();
	final Bundle b = data == null ? null : data.getExtras();
	if(b != null){
	    intent.putExtras(b);
	}
	tabSpec.setIndicator(tv).setContent(intent);
	tabHost.addTab(tabSpec);

	mTabClasses.add(cls);
    }

    protected int getTabIndex(Class<?> clazz){
	return mTabClasses.indexOf(clazz);
    }

    protected int getTabCount(){
	return getTabWidget().getTabCount();
    }

}

