/**
 * Title: ApplicationScreen 的使用方法
 * Tag: Screen, Application
 * Update: 2015/07/06
 * Description: 
 */

/**
 *  标题栏右边的按钮布局自定义 
 */
@Override
public View addViewInTitleRight(View v, int width, int height, int nTopMargin, int nRightMargin) {
    final RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
	    RelativeLayout.LayoutParams.WRAP_CONTENT,
	    mContext.getResources().getDimensionPixelSize(R.dimen.title_height));
    v.setPadding(0, 0, SystemUtils.dipToPixel(mContext, 10), 0);
    lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
    mViewTitle.addView(v, lp);
    mViewTitleRight = v;
    updateTitleMargin();
    return v;
}

/**
 *	 * 标题右侧按钮自定义为文字
 *	     * 
 *		 */ 
@Override
public View createTitleRightTextButton(int textId) {
    TextView v = (TextView)LayoutInflater.from(mContext).inflate(R.layout.textview_titleright, null);
    v.setText(textId);
    return v;
}

/**
 *	 * 标题栏左侧返回按钮自定义
 *	     */ 
@Override
public View onCreateTitleBackButton(){
    TextView tv = new TextView(mContext);
    tv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.nav_image_back, 0, 0, 0);
    SystemUtils.setTextColorResId(tv, R.color.white);
    tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
    tv.setCompoundDrawablePadding(SystemUtils.dipToPixel(mContext, 3));
    tv.setGravity(Gravity.CENTER_VERTICAL);
    tv.setPadding(SystemUtils.dipToPixel(mContext, 10), 0, 
	    SystemUtils.dipToPixel(mContext, 10), 0);
    return tv;
}

/**
 *	 * 自定义XProgressDialog
 *	     */
@Override
public View createXProgressDialog() {
    View retView = super.createXProgressDialog();
    ViewGroup view = (ViewGroup)retView.findViewById(R.id.view);
    final Animation anim = AnimationUtils.loadAnimation(mContext, R.anim.rotation);
    LinearInterpolator lin = new LinearInterpolator();
    anim.setInterpolator(lin);
    view.startAnimation(anim);
    return retView;
}
