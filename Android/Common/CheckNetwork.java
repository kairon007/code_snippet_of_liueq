/*
    Title: 检查网络链接状态
    Tag: network, gps, 3g, wifi
    Update: 2015/07/02
    Description: 
*/


//1.判断网络连接是否可用
public static boolean isNetworkAvailable(Context context) {
    ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);   
    if (cm != null) {
        //如果仅仅是用来判断网络连接则可以使用 cm.getActiveNetworkInfo().isAvailable();  
        NetworkInfo[] info = cm.getAllNetworkInfo();   
        if (info != null) {   
            for (int i = 0; i < info.length; i++) {   
                if (info[i].getState() == NetworkInfo.State.CONNECTED) {   
                    return true;   
                }   
            }   
        }   
        return false;   
    } 
}

//2.判断GPS是否打开
public static boolean isGpsEnabled(Context context) {   
    LocationManager lm = ((LocationManager) context.getSystemService(Context.LOCATION_SERVICE));   
    List<String> accessibleProviders = lm.getProviders(true);   
    return accessibleProviders != null && accessibleProviders.size() > 0;   
} 

//3.判断Wifi是否打开
public static boolean isWifiEnabled(Context context) {   
    ConnectivityManager mgrConn = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);   
    TelephonyManager mgrTel = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);   
    return ((mgrConn.getActiveNetworkInfo() != null && mgrConn.getActiveNetworkInfo().getState() == NetworkInfo.State.CONNECTED) || mgrTel.getNetworkType() == TelephonyManager.NETWORK_TYPE_UMTS);   
} 

//4.判断是否是3G网络
public static boolean is3G(Context context) {   
    ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);   
    NetworkInfo networkINfo = cm.getActiveNetworkInfo();   
    if (networkINfo != null && networkINfo.getType() == ConnectivityManager.TYPE_MOBILE) {   
        return true;   
    }   
    return false;   
}  

//5.判断是wifi还是3G

public static boolean isWifi(Context context) {   
    ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);   
    NetworkInfo networkINfo = cm.getActiveNetworkInfo();   
    if (networkINfo != null && networkINfo.getType() == ConnectivityManager.TYPE_WIFI) {   
        return true;   
    }   
    return false;   
}











