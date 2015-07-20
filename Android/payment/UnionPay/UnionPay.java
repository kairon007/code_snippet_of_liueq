/**
 * Title: UnionPay
 * Tag: UnionPay, 银联支付
 * Update: 2015/07/20
 * Description:
 *  1. 当前只是总结了所了解到的内容，并不一定正确
 */


//从服务器获取数据，貌似银联支付的主要内容都是在服务器端完成
String tn = (String) event.getReturnParamAtIndex(0);
payByUnionPay(tn);

protected void payByUnionPay(String tn){
    Message msg = mHandler.obtainMessage();
    msg.obj = tn;
    msg.what = MSG_WHAT_UP_PAY;
    mHandler.sendMessage(msg);
}

private Handler mHandler = new Handler(){
    public void handleMessage(Message msg){
	switch(msg.what){
	    case MSG_WHAT_UP_PAY:
		String tn = (String) msg.obj;
		UPPayAssistEx.startPayByJAR(BuyYunbiActivity.this, PayActivity.class, null, null, tn, PayUtils.UP_PAY_MODE_OFFICIAL); //客户端代码中最核心的部分
		break;
	}
    }
}
