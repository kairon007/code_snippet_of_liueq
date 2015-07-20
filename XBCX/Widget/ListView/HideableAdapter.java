/*
 * Title: 继承HideableAdapter
 * Tag: adapter, hideable
 * Update Time: 2015/07/01
 * Description: 能够用于SectionAdapter的固定形态的Adapter
 */


public class ExHideableAdapter extends HideableAdapter{

    protected Context mContext;
    protected OnClickListener mListener;
    protected View mConvertView;

    public CourseDetailAdapter(Context context) {
	this.mContext = context;
    }

    public CourseDetailAdapter(Context context, OnClickListener listener) {
	this.mContext = context;
	this.mListener = listener;
	this.mConvertView = SystemUtils.inflate(mContext, R.layout.adapter_course_detail);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
	return mConvertView;
    }


    /**
     *  填充数据
     *  @param course
     */
    public void updateUI(Course course){

    }


}
