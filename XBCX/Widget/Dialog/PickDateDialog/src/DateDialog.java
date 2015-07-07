package com.xbcx.qiuchang.view.dialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.xbcx.qiuchang.R;
import com.xbcx.qiuchang.view.wheel.OnWheelChangedListener;
import com.xbcx.qiuchang.view.wheel.WheelView;
import com.xbcx.qiuchang.view.wheel.adapter.ArrayWheelAdapter;


public class DateDialog extends Dialog implements OnWheelChangedListener{
	
	protected GregorianCalendar 		mGregorianCalendar	= new GregorianCalendar();
	protected ArrayList<String> 		mYears				= new ArrayList<String>();//存放dialog中显示的所有年份的list
	protected ArrayList<String> 		mMonths				= new ArrayList<String>();
	protected ArrayList<String> 		mDays				= new ArrayList<String>();
	protected ArrayList<String> 		mHours				= new ArrayList<String>();
	
	protected String 					mCurrentYear;	//当前选择的年
	protected String 					mCurrentMonth;
	protected String 					mCurrentDay;
	protected String					mCurrentHour;
	
	protected Context					mContext;
	
	protected WheelView					mWheelViewYear;
	protected WheelView					mWheelViewMonth;
	protected WheelView					mWheelViewDay;
	protected WheelView					mWheelViewHour;
	protected TextView					mBtnPositive;
	protected TextView					mBtnNegative;
	protected ImageButton				mBtnBackYear;
	protected ImageButton				mBtnNextYear;
	protected TextView					mTextViewPickYear;
	protected TextView					mTextViewPickDate;
	
	protected final int 				YEAR_COUNT = 10;	//dialog中显示的未来10年
	
	private OnDateChooseEndListener	mOnDateChooseEndListener;
	
	public DateDialog(Context context, boolean haveHour) {
		super(context, R.style.dialog);
		setContentView(R.layout.xdialog_date);
		mContext = context;
		
		mWheelViewYear = (WheelView)findViewById(R.id.wv_year);
		mWheelViewMonth = (WheelView)findViewById(R.id.wv_month);
		mWheelViewDay = (WheelView)findViewById(R.id.wv_day);
		mWheelViewHour = (WheelView)findViewById(R.id.wv_hour);
		mBtnPositive = (TextView)findViewById(R.id.btnOK);
		mBtnNegative = (TextView)findViewById(R.id.btnCancel);
		mBtnBackYear = (ImageButton)findViewById(R.id.btn_back_year);
		mBtnNextYear = (ImageButton)findViewById(R.id.btn_next_year);
		mTextViewPickYear = (TextView)findViewById(R.id.tv_year);
		mTextViewPickDate = (TextView)findViewById(R.id.tv_date);

		mWheelViewYear.addChangingListener(this);	//在滚轮上添加改变的监听器
		mWheelViewMonth.addChangingListener(this);
		mWheelViewDay.addChangingListener(this);
		mWheelViewHour.addChangingListener(this);
		
		//如果没有Hour，将滚轮设置不可见
		if(!haveHour){
			mWheelViewHour.setVisibility(View.GONE);
		}
		
		initView();
		
		setCanceledOnTouchOutside(true);
		
		Window window = this.getWindow();
		window.setGravity(Gravity.CENTER);	//设置dialog窗口在屏幕上的位置

		initData();
	}
	
	@Override
	public void show() {
		super.show();
		getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
	}
	
	public DateDialog setOnDateChooseEndListener(OnDateChooseEndListener onDateChooseEndListener) {
		this.mOnDateChooseEndListener = onDateChooseEndListener;
		return this;
	}
	
	/**
	 * 设置dialog中按钮的点击事件
	 */
	protected void initView() {
		
		mBtnBackYear.setVisibility(View.INVISIBLE);
		
		mBtnPositive.setText(mContext.getString(R.string.ok));
		mBtnPositive.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dismiss();
				if(mOnDateChooseEndListener != null){
					String date = getNum(mCurrentYear) + "-" + getNum(mCurrentMonth) + "-" + getNum(mCurrentDay) + "-" + getNum(mCurrentHour);
					mOnDateChooseEndListener.onDateChooseEnd(date);	//通过listener传递选择的日期
				}
			}
		});
		
		mBtnNegative.setText(mContext.getString(R.string.cancel));
		mBtnNegative.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
		
		mBtnBackYear.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mWheelViewYear.setCurrentItem(mWheelViewYear.getCurrentItem() - 1);
				onChanged(mWheelViewYear,0,0);
			}
		});

		mBtnNextYear.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				mWheelViewYear.setCurrentItem(mWheelViewYear.getCurrentItem() + 1);
				onChanged(mWheelViewYear,0,0);
			}
		});
	}
	
	protected void initData() {
		
		initYearData();
		mWheelViewYear.setViewAdapter(new ArrayWheelAdapter<String>(mContext, mYears.toArray(new String[]{})));	//将数据设置到adapter中
		mWheelViewYear.setVisibleItems(5);	//设置界面上出现的item的数量，貌似设置了没用
		mWheelViewMonth.setVisibleItems(5);
		mWheelViewDay.setVisibleItems(5);
		mWheelViewHour.setVisibleItems(5);
		updateMonthData();
		updateDayDate();
		updateHourDate();
		updateUI();
	}
	
	/**
	 * 初始化年份数据，从1900年开始到现在的所有年份都将加入显示的list中
	 */
	protected void initYearData() {
		int nowYear = mGregorianCalendar.get(Calendar.YEAR);
		for(int i = nowYear; i <= nowYear +YEAR_COUNT; i++){
			mYears.add(String.valueOf(i) + mContext.getString(R.string.year));
		}
		
		mCurrentYear = mYears.get(0);
	}
	
	/**
	 * 更新月份的数据，由所选择年分来决定
	 */
	protected void updateMonthData() {
		int pCurrent = mWheelViewYear.getCurrentItem();
		mCurrentYear = mYears.get(pCurrent);	//获取滚轮所选择年份
		
		mMonths.clear();
		
		int nowYear = mGregorianCalendar.get(Calendar.YEAR);	//当前的实际年份
		int nowMonth = 1;
		
		if(nowYear == getNum(mCurrentYear)){	//选中的年份和当前年份一致，那么月份只显示1月到当前实际月份的数据
			nowMonth = mGregorianCalendar.get(Calendar.MONTH) + 1;
		}else{	//如果选中的年份不是今年，那么月份显示1-12月
			nowMonth = 1;
		}
		
		constructListReverse(mMonths,12 , nowMonth, R.string.month);
		
		mWheelViewMonth.setViewAdapter(new ArrayWheelAdapter<String>(mContext, mMonths.toArray(new String[]{})));	//加载数据到adapter
		mWheelViewMonth.setCurrentItem(0);	//当前选择的数据是最后一条
		
		updateDayDate();	//更新完月之后需要同时更新日
	}
	
	/**
	 * 更新日的数据，根据月份和年份决定
	 */
	protected void updateDayDate() {
		int pCurrent = mWheelViewMonth.getCurrentItem();
		mCurrentMonth = mMonths.get(pCurrent);	//获取滚轮选择的月
		
		mDays.clear();
		
		int nowYear = mGregorianCalendar.get(Calendar.YEAR);
		int nowMonth = mGregorianCalendar.get(Calendar.MONTH) + 1;
		int nowDay ;
		
		if (nowYear == getNum(mCurrentYear)
				&& nowMonth == getNum(mCurrentMonth)) { // 如果年月都是当前年月，nowday就是当前日
			nowDay = mGregorianCalendar.get(Calendar.DAY_OF_MONTH);
		} else { //nowday 认为是从1号开始
			nowDay = 1;
		}
		
		int cm = getNum(mCurrentMonth);
		int currentItem = 0;
		
		if (cm == 1 || cm == 3 || cm == 5 || cm == 7 || cm == 8 || cm == 10
				|| cm == 12) {
			constructListReverse(mDays, 31, nowDay, R.string.ri);
			currentItem = 31 - nowDay;
		} else if (cm == 4 || cm == 6 || cm == 9 || cm == 11) {
			constructListReverse(mDays, 30, nowDay, R.string.ri);
			currentItem = 30 - nowDay;
		} else if (cm == 2) { // 二月的时候需要考虑闰年
		// if(BUtils.isLeapYear(getNum(mCurrentYear))){ //这里的工具需要自己实现
			if (true) {
				constructListReverse(mDays, 29, nowDay, R.string.ri);
				currentItem = 29 - nowDay;
			} else {
				constructListReverse(mDays, 28, nowDay, R.string.ri);
				currentItem = 28 - nowDay;
			}
		}
		
		mWheelViewDay.setViewAdapter(new ArrayWheelAdapter<String>(mContext, mDays.toArray(new String[]{})));		//将所日的集合加入到adapter
		mWheelViewDay.setCurrentItem(0);	//设置当前的选择为第0条
		
//		mCurrentDay = mDays.get(0);
		updateHourDate();
	}
	
	/**
	 * 更新小时
	 */
	protected void updateHourDate() {
		int pCurrent = mWheelViewDay.getCurrentItem();
		mCurrentDay = mDays.get(pCurrent);
		
		mHours.clear();
		
		int nowDay = mGregorianCalendar.get(Calendar.DAY_OF_MONTH);
		int nowHour ;
		
		if (nowDay == getNum(mCurrentDay)) {
			nowHour = mGregorianCalendar.get(Calendar.HOUR_OF_DAY) + 1;
		} else {
			nowHour = 0;
		}
		
		constructListReverse(mHours,23 , nowHour, R.string.hour);
		
		mWheelViewHour.setViewAdapter(new ArrayWheelAdapter<String>(mContext, mHours.toArray(new String[]{})));		//将所日的集合加入到adapter
		mWheelViewHour.setCurrentItem(0);	//设置当前的选择为第0条
		mCurrentHour = mHours.get(0);
	}
	
	protected int getNum(String str){
		return Integer.valueOf(str.substring(0, str.length()-1));
	}
	
	/**
	 * 从1月到当前月的集合
	 * @param list
	 * @param top
	 * @param suffixId
	 */
	protected void constructList(List<String> list, int top, int suffixId){
		for(int i = top; i >= 1; i--) {
			list.add(String.valueOf(i) + mContext.getString(suffixId));
		}
	}
	
	/**
	 * 从bottom月到top月的集合
	 * @param list
	 * @param top
	 * @param bottom
	 * @param suffixId
	 */
	protected void constructListReverse(List<String> list,int top, int bottom, int suffixId){
		for(int i = bottom; i <= top; i++){
			list.add(String.valueOf(i) + mContext.getString(suffixId));
		}
	}
	
	@Override
	public void onChanged(WheelView wheel, int oldValue, int newValue) {
		if (wheel == mWheelViewYear) {
			updateMonthData();	//当滚轮选择年份变化， 更新月
		} else if (wheel == mWheelViewMonth) {
			updateDayDate();	//当滚轮选择月份变化，更新日
		} else if (wheel == mWheelViewDay) {
			updateHourDate();
		} else if (wheel == mWheelViewHour) {
			mCurrentHour = mHours.get(newValue);
		}
		
		updateUI();
		
	}
	
	public static interface OnDateChooseEndListener{
		public void onDateChooseEnd(String date);
	}
	
	/**
	 * 将滚轮选择的日期反映到TextView上
	 */
	private void updateUI(){
		if(getNum(mCurrentYear) == mGregorianCalendar.get(Calendar.YEAR)){
			mBtnBackYear.setVisibility(View.INVISIBLE);
		}else{
			mBtnBackYear.setVisibility(View.VISIBLE);
		}
		
		if(getNum(mCurrentYear) == (mGregorianCalendar.get(Calendar.YEAR) + YEAR_COUNT - 1)){
			mBtnNextYear.setVisibility(View.INVISIBLE);
		}else{
			mBtnNextYear.setVisibility(View.VISIBLE);
		}
		
		mTextViewPickYear.setText(mCurrentYear);
		mTextViewPickDate.setText(mCurrentMonth + mCurrentDay);
	}

}
