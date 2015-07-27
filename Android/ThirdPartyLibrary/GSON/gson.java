/**
 * Title: GSON
 * Tag: json, Gson
 * Update: 2015/07/27
 * Description:
 *  1. Gradle引入 'com.google.code.gson:gson:2.3.1'
 *  2. Json的key和value都必须用双引号圈起来
 */

//1.从文件中读取Json字符串
FileReader reader = new FileReader(file);
JsonReader jsonReader = new JsonReader(reader); //这里是Google的类
jsonReader.setLenient(true);//使得保留空格

//2.将Json字符串转换为Class，这里的Json字符串是数组
Gson gson = new Gson();
Type collectionType = new TypeToken<List<Type>>(){}.getType(); //注意这里的Type不能用泛型
List<Type> list = gson.fromJson(json, collectionType);

//3.简单的Serializer
public class JsonSerializer {

    private final Gson gson = new Gson();

    public JsonSerializer() {}

    /**
     * Serialize an object to Json.
     *
     * @param userEntity {@link UserEntity} to serialize.
     */
    public String serialize(UserEntity userEntity) {
	String jsonString = gson.toJson(userEntity, UserEntity.class);
	return jsonString;
    }

    /**
     * Deserialize a json representation of an object.
     *
     * @param jsonString A json string to deserialize.
     * @return {@link UserEntity}
     */
    public UserEntity deserialize(String jsonString) {
	UserEntity userEntity = gson.fromJson(jsonString, UserEntity.class);
	return userEntity;
    }
}

//对于UserEntity来说，可以使用Annotation来使得Field和Json key对应
@SerializedName("id")
private int userId;

//4. JsonReader的使用
//类似于XMLSerializer

//下面代码解析的是此字符串(如果是在java代码中，需要用反斜杠反义双引号)： [{"name" : "liueq", "age" : "23", "ok" : "yes"},{"name" : "liueq2", "age" : "232", "ok" : "yes2"}]
jsonReader.beginArray();
while(jsonReader.hasNext()) {
    jsonReader.beginObject();
    while (jsonReader.hasNext()) {
	String tagName = jsonReader.nextName(); //注意每一处的next都必须要和json字符串相匹配
	if (tagName.equals("name")) {
	    value = jsonReader.nextString();
	}else if(tagName.equals("age")){
	    jsonReader.nextString();
	}else if(tagName.equals("ok")){
	    jsonReader.nextString();
	}
    }
    jsonReader.endObject();
}
jsonReader.endArray();
