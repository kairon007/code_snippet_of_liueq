/**
 * Title: PermissionRequest
 * Tag: Permission, Request
 * Update: 2015/07/20
 * Description:
 *  1. 注意，请求的权限必须要在Manifest中声明，否则会直接闪退
 *  2. 仅在Android M下才会请求权限的设置： <uses-permission-sdk-m android:name="android.permission.ACCESS_FINE_LOCATION"/>
 */

/***** 请求单一权限 *****/

private static final int REQUEST_LOCATION = 1503;   //用于onRequestPermissionsResult识别

public void requestPermission(){
    String locationPermission = Manifest.permission.ACCESS_FINE_LOCATION;
    int hasPermission = checkSelfPermission(locationPermission);
    String[] permissions = new String[]{locationPermission};
    if(hasPermission != PackageManager.PERMISSION_GRANTED){
	requestPermissions(permissions, REQUEST_LOCATION);
    }else{
	Toast.makeText(MainActivity.this, "Already have permission", Toast.LENGTH_SHORT).show();
    }
}

/**
 * 此方法为Activity的方法，获取请求权限之后的返回内容
 */
@Override
public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
    switch (requestCode){
	case REQUEST_LOCATION:
	    if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
		Toast.makeText(MainActivity.this, "Permission granted", Toast.LENGTH_SHORT).show();
	    }else{
		Toast.makeText(MainActivity.this, "Permission denied", Toast.LENGTH_SHORT).show();
	    }
	    break;
	default:
	    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}


/***** 请求多个权限 *****/

public void requestMultiPermission(){
    String locationPermission = Manifest.permission.ACCESS_FINE_LOCATION;
    String calendarPermission = Manifest.permission.WRITE_CALENDAR;
    int hasLocPermission = checkSelfPermission(locationPermission);
    int hasCalPermission = checkSelfPermission(calendarPermission);

    List<String> permissions = new ArrayList<String>();
    if(hasLocPermission != PackageManager.PERMISSION_GRANTED){
	permissions.add(locationPermission);
    }
    if(hasCalPermission != PackageManager.PERMISSION_GRANTED){
	permissions.add(calendarPermission);
    }

    if(!permissions.isEmpty()){
	String[] params = permissions.toArray(new String[permissions.size()]);
	System.out.println("liueq : params is " + permissions.toString());
	requestPermissions(params, REQUEST_PERMISSIONS);
    }else{
	Toast.makeText(MainActivity.this, "Already have all permission", Toast.LENGTH_SHORT).show();
    }
}
