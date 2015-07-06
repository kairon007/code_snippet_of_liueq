/*
 *	Title: 微信支付
 *	Tag: WXPay, weixing, pay
 *	Update: 2015/07/06
 *
 主要流程:
 GetPrepayIdTask						--从微信服务器获得Prepay ID
 genProductArgs()						--配置参数，发送到服务器，从而获得Prepay ID
 genPackageSign()						--将生成的参数进行签名
 toXml()							--转换为XML格式
 httpPost()							--发送到服务器，返回值为Prepay ID
 decodeXml()							--解码返回的内容
 genPayReq()							--配置PayReq的参数
 genAppSign()							--将获得的参数进行第二次签名，本次签名和第一次的参数无关
 sendPayReq							--将配置好的PayReq发送到服务器，调用微信支付

 回调部分：
 1. 回调的Activity的命名方式必须遵循 <包名>.wxapi.WXPayEntryActivity 的形式；
 2. 貌似需要注册一个Receiver，按照Demo中样式即可；

 其他：
 1. 调用支付的Activity需要在AndroidManifest 中定义scheme 字段，内容为APPID；
 2. 如果是手机端进行第一次签名，body为中文的时候，会导致签名错误，需要在签名后，转换为xml格式的字符串后进行编码
 String xmlstring = toXml(packageParams);
 return new String(xmlstring.toString().getBytes(), "ISO8859-1");//这句加上就可以了吧xml转码下
 3. 在调用微信支付前，需要判断手机上是否安装有微信

*/

/**
 * 判断手机上是否安装有微信
 */
public isWXInstalled(){
    IWXAPI api = WXAPIFactory.createWXAPI(this, APP_ID);
    if(api.isWXAppInstalled() && api.isWXAppSupportAPI()){
	//TODO 调用微信支付
    }
}





