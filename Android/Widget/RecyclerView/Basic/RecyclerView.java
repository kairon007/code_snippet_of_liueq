/**
 * Title: RecyclerView
 * Update: 2015/07/13
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

    recyclerView.setAdapter(adapter);		//直接设置Adapter即可

}

/**
 * 关于RecyclerViewAdapter的写法
 * 这里继承了RecyclerView.Adapter，泛型需要使用ViewHolder，可以在Adapter中定义一个内部类
 * RecyclerView 的item点击事件，应该在ViewHolder中处理，将listner传入构造方法，然后在onBindViewHolder中设定想要监听的View
 */
public class RecyclerListAdapter extends RecyclerView.Adapter<RecyclerListAdapter.ViewHolder> {

    private Context mContext;
    private List<String> mList;

    //传入的参数依旧是Context和list
    public RecyclerListAdapter(Context context, List<String> list){
	this.mList = list;
	mContext = context;
    }

    //创建ViewHolder，从布局文件中获取ViewHode的布局，并且新建一个Holder，作为返回值
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
	View v = (View) LayoutInflater.from(mContext).inflate(R.layout.list_item, null);
	ViewHolder viewHolder = new ViewHolder(v);
	return viewHolder;
    }

    //对于Holder对象的赋值，在此进行，这里和一般的ListView中的Adapter不同，ViewHolder的生成和对其的赋值是在两个不同的方法中进行
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
	viewHolder.mTextView.setText(mList.get(i));

    }

    @Override
    public int getItemCount() {
	return mList.size();
    }


    /**
     * 定义一个内部类作为ViewHolder，需要继承RecyclerView.ViewHolder
     * 另外一定值得注意的地方是静态内部类可以节省内存
     */
    public static class ViewHolder extends RecyclerView.ViewHolder{

	public final TextView mTextView;

	public ViewHolder(View itemView) {
	    super(itemView);
	    mTextView = (TextView) itemView.findViewById(R.id.tv);

	}
    }
}
