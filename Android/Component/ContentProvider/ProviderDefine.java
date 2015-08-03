/**
 * Title: ContentProviderDefine
 * Tag: Define, Contract, ContentProvider
 * Update: 2015/08/03
 * Description: 定义了ContentProvider的一些常亮
 */

//ContentProvider的认证，必须和Manifest中一致，一般就是包名
public static final String CONTENT_AUTHORITY = "com.liueq.testasyncaccount";

//基础URI
public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
//这里应该需要操作的对象名，一般就是表名的复数
public static final String PATH_ENTRIES = "tests";


/**
 * 实现BaseColumn后已经有了_id和_count两个列了
 */
public static class Test implements BaseColumns{

    /*定义了MIME Type，针对表的list查询, 似乎只有在provider中的getType()里用到*/
    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/vnd.testasyncaccount.tests";

    /*定义MIME Type，针对表的单独的一个对象查询*/
    public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/vnd.testasyncaccount.test";

    /*组合完整的URI，最为关键的Uri，将ContentResolver和Provider联系起来的纽带*/
    public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_ENTRIES).build();

    /**
     * 以下皆为表的具体内容
     */
    public static final String TABLE_NAME = "TEST";

    public static final String COLUMN_NAME_CONTENT = "content";

    public static final String COLUMN_NAME_ADDRESS = "address";

    public static final String COLUMN_NAME_WEATHER = "weather";

}
