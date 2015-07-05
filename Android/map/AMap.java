/*Title: 高德地图相关
  Tag: map, gaode, amap
  Update Time: 2015/07/01
  Description:调用高德地图的方式*/


/**
* 高德地图，只显示地点
* @param lat
* @param lng
* @param desc
* @return
*/
public static Intent getGaodeIntent(double lat, double lng, String desc) {

    String url = "androidamap://viewMap?sourceApplication=xbcx"
    + "&poiname=" + desc 
    + "&lat=" + lat 
    + "&lon=" + lng 
    + "&dev=0";


    Intent gaodeIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
    gaodeIntent.setPackage("com.autonavi.minimap");

    return gaodeIntent;
}