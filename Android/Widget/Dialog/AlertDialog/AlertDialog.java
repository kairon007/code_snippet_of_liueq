/**
 * Titile: AlertDialog
 * Tag: Dialog , Alert
 * Update: 2015/07/22
 * Descripton: 简单的是否选择对话框
 */

AlertDialog.Builder builder = new AlertDialog.Builder(context);
builder.setMessage("message")
	.setPositiveButton("yes", listener)
	.setNegativeButton("no", listener)
	.setIcon(icon)
	.setTitle(title);

AlertDialog dialog = builder.create();
