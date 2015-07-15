/**
 * Title: GSON
 * Tag: json, Gson
 * Update: 2015/07/15
 * Description:
 *  1. Gradle引入 'com.google.code.gson:gson:2.3.1'
 */

//1.从文件中读取Json字符串
FileReader reader = new FileReader(file);
JsonReader jsonReader = new JsonReader(reader); //这里是Google的类
jsonReader.setLenient(true);//使得保留空格

//2.将Json字符串转换为Class，这里的Json字符串是数组
Gson gson = new Gson();
Type collectionType = new TypeToken<List<Type>>(){}.getType(); //注意这里的Type不能用泛型
List<Type> list = gson.fromJson(json, collectionType);
