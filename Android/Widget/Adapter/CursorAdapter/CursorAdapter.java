/**
 * Title: CursorAdapter
 * Tag: cursor, adapter
 * Update: 2015/08/03
 * Description: 
 *  1. CursorAdapter的简单使用
 *  2. 注意，CursorAdapter对于数据的更新是在UI线程，推荐使用CursorLoader替代
 *
 */


//这里使用匿名类的方式，实际继承的话也类似
CursorAdapter adapter = new CursorAdapter(this, cursor) {

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

	//一定要用ViewHolder，凡事在Android的List中加载，基本都使用了ViewHolder，否则数据显示出错
	ViewHolder holder = new ViewHolder();

	LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	View v = inflater.inflate(R.layout.list_item, null);
	holder.mTvID = (TextView) v.findViewById(R.id.column_id);
	holder.mTvContent = (TextView) v.findViewById(R.id.column_content);
	holder.mTvAddress = (TextView) v.findViewById(R.id.column_address);
	holder.mTvWeather = (TextView) v.findViewById(R.id.column_weather);
	v.setTag(holder);

	return v;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
	ViewHolder holder = (ViewHolder) view.getTag();
	holder.mTvID.setText(String.valueOf(cursor.getInt(0)));
	holder.mTvContent.setText(cursor.getString(1));
	holder.mTvAddress.setText(cursor.getString(2));
	holder.mTvWeather.setText(cursor.getString(3));
    }

    /**
     * 如果不用ViewHolder的形式，会导致BindView的时候数据混乱
     */
    class ViewHolder{

	TextView mTvID;
	TextView mTvContent;
	TextView mTvAddress;
	TextView mTvWeather;
    }

};
