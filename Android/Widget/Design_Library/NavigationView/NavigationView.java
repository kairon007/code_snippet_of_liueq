/**
 * Title: NavigationView
 * Tag: NavigationView
 * Update: 2015/07/08
 * Description
 */

private NavigationView navigationView;

navigationView = (NavigationView) findViewById(R.id.navigation);
/*设置Menu的点击事件*/
navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
	int id = menuItem.getItemId();
	switch (id){
	    case R.id.navItem1:
		break;
	    case R.id.navItem2:
		break;
	    default:
		break;
	}
	return false;
    }
});
