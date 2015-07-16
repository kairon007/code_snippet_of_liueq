/**
 * Title: ContextMenu 使用指南
 * Tag: ContextMenu, Menu
 * Update: 2015/07/16
 * Description:
 *  1. 唤出ContextMenu的View不需要设定clickable，只要注册了，长按就可以唤出
 *  2. 一个Activity只能有一个ContextMenu，但是可以由多个View注册，唤出的Menu都是一样的
 *  3. 关于ListView的Item如何实现还有待考证，貌似无法直接获取弹出菜单的具体是哪个Item，需要配合ItemLongClick使用?
 */

/* 1. 给View注册ContextView */
TextView tv = (TextView) findViewById(R.id.tv);
registerForContextMenu(tv);	//注册
unregisterForContextMenu(tv);	//注销



/* 2. 重写Activity中的方法 */
@Override
public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
    super.onCreateContextMenu(menu, v, menuInfo);
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_main, menu);
}

@Override
public boolean onContextItemSelected(MenuItem item) {
    AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
    //这里可以通过info来获得关于此View的更多信息
    switch (item.getItemId()){
	case R.id.action_do:
	    Toast.makeText(MainActivity.this, "DO", Toast.LENGTH_SHORT).show();
	    break;
	case R.id.action_undo:
	    Toast.makeText(MainActivity.this, "UNDO", Toast.LENGTH_SHORT).show();
	    break;
	case R.id.action_settings:
	    Toast.makeText(MainActivity.this, "Settings", Toast.LENGTH_SHORT).show();
	    break;
    }
    return super.onContextItemSelected(item);
}
