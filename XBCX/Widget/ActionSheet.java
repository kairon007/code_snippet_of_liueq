/*
 *   Title: ActionSheet
 *   Tag: actionSheet
 *   Update: 2015/07/01
 *   Description: 仿iOS的ActionSheet
 */


private ActionSheet mActionSheet;

setTheme(R.style.ActionSheetStyleIOS7);
showActionSheet();

private void showActionSheet() {
    mActionSheet = ActionSheet
	.createBuilder(this, getSupportFragmentManager())
	.setCancelButtonTitle(R.string.actionsheet_cancel)
	.setOtherButtonTitles(getString(R.string.actionsheet_refund))
	.setCancelableOnTouchOutside(true)	    //需要继承ActionSheetListener
	.setListener(this)
	.show();
}


@Override
public void onOtherButtonClick(ActionSheet actionSheet, int index) {
    if (actionSheet == mActionSheet) {
	if (index == 0) {
	    if (!mActionSheet.isDismissed()) {
		mActionSheet.dismiss();
	    }
	    pushEvent(QCEventCode.HTTP_DoRefund, mSubOrder.getId());
	}
    }
}

@Override
public void onDismiss(ActionSheet actionSheet, boolean isCancel) {

}
