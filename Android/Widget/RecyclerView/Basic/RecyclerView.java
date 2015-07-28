/**
 * Title: RecyclerView
 * Update: 2015/07/28
 * Description:
 *  1. 关于LayoutMananger，常见的有3中LinearLayoutManager, GridLayoutManager, StaggeredGridLayoutManager
 *  2. 调用RecyclerView默认动画，通过notify不同的类型，RecyclerView会自动显示动画
 *	notifyItemChanged()
 *	notifyItemInserted() 
 *	notifyItemRemoved()
 *	notifyItemRangeChanged()
 *	notifyItemRangeInserted()
 *	notifyItemRangeRemoved()
 *  3. 增加了滑动监听
 */

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv);
    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    String [] strs = {"Hello", "My", "name", "is", "liueq", "how", "are", "you", "today"};
    List<String> list = Arrays.asList(strs);
    RecyclerListAdapter adapter = new RecyclerListAdapter(this, list);

    //Recycler的ScrollListener
    recyclerView.addOnScrollListener(new OnScrollListener() {

	private boolean scrollUp = true;

	@Override
	public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
	    super.onScrollStateChanged(recyclerView, newState);
	    //newState的3种状态，只有在这3种状态切换才会触发
	    //1. SCROLL_STATE_DRAGGING 触摸进行中，正在滑动
	    //2. SCROLL_STATE_IDLE 没有滑动
	    //3. SCROLL_STATE_SETTLING 惯性滑动中，没有触摸
	}

	@Override
	public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
	    super.onScrolled(recyclerView, dx, dy);

	    //dx和dy表示的是滑动的距离，由开始触摸的位置减去结束触摸的位置
	    //常用的判断方式是，当dy>0的时候，认为是向上滑动，反之向下
	}
    }


    recyclerView.setAdapter(adapter);		//直接设置Adapter即可
}

	/**
	 * 关于RecyclerViewAdapter的写法
	 * 这里继承了RecyclerView.Adapter，泛型需要使用ViewHolder，可以在Adapter中定义一个内部类
	 * RecyclerView 的item点击事件，应该在ViewHolder中处理，对ViewHolder中的View进行监听
	 */
	public class RecyclerListAdapter extends RecyclerView.Adapter<RecyclerListAdapter.ViewHolder> {

	    private Context mContext;
	    private List<String> mList;

	    //传入的参数依旧是Context和list
	    public RecyclerListAdapter(Context context, List<String> list){
		this.mList = list;
		mContext = context;
	    }

	    /** 
	     * 创建ViewHolder，从布局文件中获取ViewHode的布局，并且新建一个Holder，作为返回值
	     * 注意第二个参数，根据viewType来生成不同的Holder对象
	     */
	    @Override
	    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
		View v = (View) LayoutInflater.from(mContext).inflate(R.layout.list_item, null);
		ViewHolder viewHolder = new ViewHolder(v);
		return viewHolder;
	    }

	    //对于Holder对象的赋值，在此进行，这里和一般的ListView中的Adapter不同，ViewHolder的生成和对其的赋值是在两个不同的方法中进行
	    @Override
	    public void onBindViewHolder(ViewHolder viewHolder, int i) {
		viewHolder.mTextView.setText(mList.get(i));
		viewHolder.setOnClickListener(new View.setOnClickListener(){
		    @Override
		    public void onClick(View v){
			//TODO 点击事件
		    }
		}

	    }

	    @Override
	    public int getItemCount() {
		return mList.size();
	    }

	    /**
	     * 获取Item的类型，可以使用固定的，也可以根据List中的对象类型来返回不同类型
	     * 这里的返回值会用来在onCreateViewHolder中
	     */
	    @Override
	    public int getItemViewType(int position){
		int type = mList.get(position).getType(); //这里演示的是item获取类型
		return type;
	    }

	    /**
	     * 定义一个内部类作为ViewHolder，需要继承RecyclerView.ViewHolder
	     * 另外一定值得注意的地方是静态内部类可以节省内存
	     * ViewHolder可以有多个
	     */
	    public static class ViewHolder extends RecyclerView.ViewHolder{

		public final TextView mTextView;

		public ViewHolder(View itemView) {
		    super(itemView);
		    mTextView = (TextView) itemView.findViewById(R.id.tv);

		}
	    }
	}
