/*Title: 百度地图相关
  Tag: map, baidu
  Update Time: 2015/07/01
  Description:调用百度地图的方式*/


/**
 * 只标注地址
 * @param lat
 * @param lng
 * @param desc
 * @return
 */
public static Intent getBaiduIntent(double lat, double lng, String desc) {

    String url = "intent://map/marker?" 
    + "location=" + lat 
    + "," + lng 
    + "&title=位置&content=" + desc 
    + "&coord_type=gcj02" 
    + "&src=xbcx|qz#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end";

    Intent baiduIntent = null;
    try {
        baiduIntent = Intent.parseUri(url, 0);
    } catch (URISyntaxException e) {
        e.printStackTrace();
    }
    return baiduIntent;
}