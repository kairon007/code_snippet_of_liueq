/**
 * Title: SnackBar
 * Tag: SnackBar, Support Design
 * Update: 2015/07/08
 * Description: 
 *  1. 如果ParentView是CoordinatorLayout，SnackBar会自动增加滑动消失的功能。
 *  2. 在5.0之前，如果配合CoordinatorLayout一起使用，滑动的时候会导致原本上浮的控件无法返回到原始的位置,目前的解决方法是设置CoordinatorLayout的margin为非0dp。
 */

Snackbar.make(rootLayout, "Content", Snackbar.LENGTH_SHORT)
        .setAction("Undo", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Toast.makeText(MainActivity.this, "Undo", Toast.LENGTH_SHORT).show();
            }
        })
	.setActionTextColor(R.color.material_blue) // 设置提示字体的颜色
	.setDuration(4000)	//设置持续时间
        .show();
