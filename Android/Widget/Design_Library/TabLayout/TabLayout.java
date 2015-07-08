/**
 * Title: TabLayout
 * Tag: TabLayout, ViewPager
 * Update: 2015/07/08
 * Description:
 *  1. 一般来说和ViewPager配合
 */

/*不使用ViewPager，直接添加tab*/
tabLayout = (TabLayout) findViewById(R.id.tabLayout);

tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));

/*使用ViewPager*/
tabLayout.setupWithViewPager(ViewPager);

