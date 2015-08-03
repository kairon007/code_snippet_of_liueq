/**
 * Title: ContentProvider
 * Tag: ContentProvider, Provider
 * Update: 2015/08/03
 * Description: 演示如何使用ContentProvider
 */


public class MyContentProvider extends ContentProvider {

    /*ContentProvider大多数情况下都是对DB进行操作，这里以DB载体作为示例*/
    MyDatabase mDatabaseHelper;

    private static final String AUTHORITY = MyContentProviderDefine.CONTENT_AUTHORITY;

    /*对于 /tests 的URI ID ，这里只是区分查询的种类，并不是很关键*/
    public static final int ROUTE_TESTS = 1;

    /*对于 /tests/{ID} 的URI ID*/
    public static final int ROUTE_TESTS_ID = 2;

    //使用Matcher来判断URI中是否有特定查询内容
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
	sUriMatcher.addURI(AUTHORITY, "tests", ROUTE_TESTS);
	sUriMatcher.addURI(AUTHORITY, "tests/*", ROUTE_TESTS_ID);
    }

    @Override
    public boolean onCreate() {
	mDatabaseHelper = new MyDatabase(getContext());
	return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
	SQLiteDatabase db = mDatabaseHelper.getReadableDatabase();
	SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
	Cursor c;
	
	//其实完全可以不要uriMatcher这一部分，直接就可以使用db进行查询操作
	int uriMatcher = sUriMatcher.match(uri);
	switch (uriMatcher){
	    case ROUTE_TESTS:
		builder.setTables(MyContentProviderDefine.Test.TABLE_NAME);
		c = builder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
		return c;
	    case ROUTE_TESTS_ID:
		builder.setTables(MyContentProviderDefine.Test.TABLE_NAME);
		c = builder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
		return c;
	    default:
		throw new UnsupportedOperationException("Unknown uri: " + uri);

	}
    }

    /**
     * 将uri通过UriMatcher来对应到自定义的MIME TYPE中
     * @param uri
     * @return
     */
    @Override
    public String getType(Uri uri) {
	final int match = sUriMatcher.match(uri);
	switch (match){
	    case ROUTE_TESTS:
		return MyContentProviderDefine.Test.CONTENT_TYPE;
	    case ROUTE_TESTS_ID:
		return MyContentProviderDefine.Test.CONTENT_ITEM_TYPE;
	    default:
		throw new UnsupportedOperationException("Unknown uri: " + uri);
	}
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
	SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
	int match = sUriMatcher.match(uri);
	Uri result;

	switch(match){
	    case ROUTE_TESTS:
		long id = db.insertOrThrow(MyContentProviderDefine.Test.TABLE_NAME, null, values); //其实到这一步已经插入完了，后边只是返回url而已
		result = Uri.parse(MyContentProviderDefine.Test.CONTENT_URI + "/" + id);
		break;
	    default:
		throw new UnsupportedOperationException("Unknown uri: " + uri);

	}

	Context ctx = getContext();
	ctx.getContentResolver().notifyChange(uri, null, false);
	return result;
    }

    /**
     * 批量插入
     * @param uri
     * @param values
     * @return
     */
    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
	SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
	//建立一个Transaction，重复调用insert方法，在SQLite中使用Transaction能够大幅提升效率
	    db.beginTransaction();
	int length = values.length;
	for(int i = 0; i < length; i++){
	    insert(uri, values[i]);
	}

	db.setTransactionSuccessful();
	db.endTransaction();
	return length;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
	SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
	int result = db.delete(MyContentProviderDefine.Test.TABLE_NAME,
		selection,
		selectionArgs);

	return result;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
	SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
	int result = db.update(MyContentProviderDefine.Test.TABLE_NAME, values, selection, selectionArgs);

	return result;
    }

    /**
     * ContentProvider所操纵的DB
     */
    static class MyDatabase extends SQLiteOpenHelper{

	public static final String DATABASE_NAME = "my.db";
	public static final int DATABASE_VERSION = 1;

	public MyDatabase(Context context){
	    super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
	    String sql = "CREATE TABLE TEST( _id INTEGER PRIMARY KEY, content TEXT, address TEXT, weather TEXT);";
	    db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
    }

}
