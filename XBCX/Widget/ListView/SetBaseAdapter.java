/*
 * Title: 继承SetBaseAdapter
 * Tag: adapter, setAdapter
 * Update Time: 2015/07/01
 * Description: 能够用于SectionAdapter的List的Adapter
 */

public class ExSetAdapter extends SetBaseAdapter<T> {

    protected Context mContext;

    public TimeRangeAdapter(Context context) {
	this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
	if (convertView == null) {
	    convertView = SystemUtils.inflate(mContext, R.layout.adapter_time_range);
	}

	T item = mListObject.get(position);
	return convertView;
    }

}
