/*Title: Json对应的class编写规格
 *   Tag: json, bean, model
 *     Update: 2015/07/01
 *       Description: 直接将Json转换为普通对象所需要遵守的规则
 */

@JsonImplementation(idJsonKey="_id")
public class Course extends IDObject{

    private static final long serialVersionUID = 1L;

    public String name;					    //普通Json变量，默认需要和传递过来的Json变量同名，如果不同名的情况下，需要用jsonKey字段说明

    @JsonAnnotation(listItem = String.class)
	public List<String> image = new ArrayList<String>();	//数组的Json变量

    @JsonAnnotation(listItem = TimeRange.class, jsonKey = "list")
	public List<TimeRange> time_range = new ArrayList<TimeRange>();			//指定了Jsonkey的数组

    public Course(String id) {
	super(id);
    }
}
