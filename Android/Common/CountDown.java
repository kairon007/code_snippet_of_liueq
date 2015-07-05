/*
 *   Title：倒计时
 *     Tag: countdown, handler
 *       Update Time: 2015/07/01
 *         Description: 
 *
 *         
 */

private void startCountDown(int time){
    if(time > 0){
	button.setText(time+"");
	Message msg = new Message();
	msg.what = MSG_COUNTDOWN;
	msg.arg1 = time;
	mHandler.sendMessageDelayed(msg, 1000);
    }else{
	onCountDownFinish();
    }
}

public void onCountDownFinish(){
    mHandler.removeMessages(MSG_COUNTDOWN);
    button.setText(getString(R.string.register_btn));
    button.setEnabled(true);
    button.setBackgroundResource(R.drawable.gen_btn);
}


private Handler mHandler = new Handler(){
    public void handleMessage(Message msg) {
	startCountDown(msg.arg1 - 1);
    };
};
