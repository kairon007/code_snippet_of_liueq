/**
 * Title: Toolbar 的代码指南
 * Tag: toolbar, actionbar
 * Update: 2015/07/17
 * Description:
 */


setSupportActionBar(toolbar);
//设置标题
getSupportActionBar().setTitle(R.string.main_activity_title);
//设置是否显示Toolbar最左边的返回按钮
getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//设置Toolbar最左边按钮的图标
getSupportActionBar().setHomeAsUpIndicator();
