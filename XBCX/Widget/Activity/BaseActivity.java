/**
 * Title: BaseActivity 的使用方法
 * Tag: BaseActivity
 * Update: 2015/07/16
 * Description:
 */


/* 0001. 自定义标题栏右侧按钮样式 */
BaseActivity.addImageButtonInTitleRight();

/* 0002. 自定义标题左侧返回按钮样式 */
TextView tv = (TextView) BaseActivity.getBaseScreen().getButtonBack();
tv.setsetCompoundDrawablesWithIntrinsicBounds();

/* 0003. 处理标题右键的点击事件 */
@Override
protected void onTitleRightButtonClicked(){}


