/*Title: ContentProvider
  Tag: Content Provider, system
  Update Time: 2015/07/01
  Description:系统的一些常见的内容提供者调用*/


//调用地图  
Uri uri = Uri.parse("geo:"+desLat+","+desLng);  
Intent it = new Intent("android.intent.action.VIEW", uri);

//调用拨号器
Uri uri = Uri.parse("tel:" + mCourse.phone);
Intent intent = new Intent(Intent.ACTION_DIAL, uri);