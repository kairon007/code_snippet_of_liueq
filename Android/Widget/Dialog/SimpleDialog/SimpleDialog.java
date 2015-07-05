/*
	Title: 简单的Dialog
	Tag: dialog, simple
	Update: 2015/07/06
	Description: 一个简单的Dailog模板
*/

public class MyDialog extends Dialog{
	
	protected Context					mContext;
	protected View.OnClickListener		mListener;


	protected TextView					mTextViewTitle;
	
	public NoticeDialog(Context context, View.OnClickListener listener) {
		super(context, R.style.dialog);
		setContentView(R.layout.xdialog_notice);
		
		this.mContext = context;
		this.mListener = listener;
	}
	
	private void init(){
		
		mTextViewTitle = (TextView) findViewById(R.id.tv_title);
		mTextViewTitle.setText(mNotice.title);
		setCanceledOnTouchOutside(true);
	}

}