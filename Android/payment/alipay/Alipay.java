/*
	Title: Alipay, 支付宝支付
	Tag: Alipay, 支付宝, pay
	Update: 2015/07/05
	Description: Android 调用手机支付宝实现支付的流程
*/

// 商户ID
public static final String ALIPAY_COMMERCIAL_ID 		= "2088911944368555";
// 商户收款账号
public static final String ALIPAY_COMMERCIAL_ACCOUNT 	= "sanlian@scsanlian.com.cn";
// 商户私钥
public static final String ALIPAY_COMMERCIAL_RSA		= "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANCcWjiGUClNcOQK"
+ "s7jCdH/UrY5cfuZBpT2LmZNcyKTA0NuYUQCTQUJinsdW7p8X+QVZgQmBwLut49Md"
+ "ZM9+uVNEaCIyuhIBzKgqOQRzP/9ylVczPtnY50QXUYPiniDkZgy/bJssSfwnfglT"
+ "SA9n05EOtVvQVJxlytIk8M0TSUx3AgMBAAECgYEAr0pCMW8DGsGc07H+dIjJVcTD"
+ "FXW5mAefLskIAiCb5Hy0w2EI55/U3VB7NOtiVULSl+EuoWtrJQkn//SaChTk6YND"
+ "6EMMaMoMHZaLDO9tCLLtrHK/PEH58y6OS3A/Od0LgB1wBo+QdZhn0QIPVDcpnBeB"
+ "SY3RLSdEOIBp9eWRxxECQQDw6aka7V942h/PxBDgZJjis3+28yn6/XdwTLVLCLQp"
+ "h2/u7RS2CMEpm6N9+LBrAqy8zJsJPiKXmMAGCsWKWU6PAkEA3azSqPF5YHxFY+U5"
+ "lyJNXu8lBPu14s2BsbfIUYimSM/L3neFKYXmhnYu4HEwbHvJZOIrkUQoqiz1Uafc"
+ "Wb2XmQJAEm19Hu8rT1438reHpvJq82RRj5f4DRfYTAHCGFQ5G7gdasQ9wlLXG6yp"
+ "C9i6/eR9fA+WiFV1Ze0sOwoV5ChCpwJACsv4FrJsLv7eglcPzwSksBMFwpBf84P+"
+ "5SEGaLI3JyS30UYQ/ky/RZ1L+zKIIUCGNputc43WTTOTXz7FxLkvQQJAQ6VdhLAJ"
+ "D3/U+JgvJx0LsNQNSEr73qEoG69WJdPScQ0P7R2TCEA38QVdFo21f8ZA3hVSCFud"
+ "UlFGBtLjg1vfMA==";

public static final String NOTIFY_URL;

public void payByAlipay(String commodityName, String commodityDetail, String commodityPrice, String orderId, String notifyUrl) {
	String orderInfo = getAlipayOrderInfo(
			commodityName,				//商品名
			commodityDetail, 			//商品详细描述
			commodityPrice,				//商品价格
			orderId,					//唯一订单id，自己或后台生成
			notifyUrl);					//回调URL
	String sign = alipaySign(orderInfo);

	try {
		sign = URLEncoder.encode(sign, "UTF-8");
	} catch (UnsupportedEncodingException e) {
		e.printStackTrace();
	}

	final String payInfo = orderInfo + "&sign=\"" + sign + "\"&" + PayUtils.getAlipaySignType();

	Runnable payRunnable = new Runnable() {
		@Override
		public void run() {
			PayTask alipay = new PayTask(PayActivity.this);
			String result = alipay.pay(payInfo);			//调用支付

			Message msg = new Message();
			msg.what = MSG_WHAT_ALIPAY_PAY;
			msg.obj = result;
			mHandler.sendMessage(msg);
		}
	};
	Thread payThread = new Thread(payRunnable);
	payThread.start();
}

//订单信息
public String getAlipayOrderInfo(String commodityName, String commodityDetail, 
	String commodityPrice, String orderId, String notifyUrl) {

	StringBuilder sb = new StringBuilder();
	sb.append("partner=" + "\"" + ALIPAY_COMMERCIAL_ID + "\"");
	sb.append("&seller_id=" + "\"" + ALIPAY_COMMERCIAL_ACCOUNT + "\"");
	// 商户网站唯一订单号
	sb.append("&out_trade_no=" + "\"" + orderId + "\"");
	// 商品名称
	sb.append("&subject=" + "\"" + commodityName + "\"");
	// 商品详情
	sb.append("&body=" + "\"" + commodityDetail + "\"");
	// 商品金额
	sb.append("&total_fee=" + "\"" + commodityPrice + "\"");
	// 服务器异步通知页面路径
	sb.append("&notify_url=" + "\"" + notifyUrl + "\"");
	// 服务接口名称， 固定值
	sb.append("&service=\"mobile.securitypay.pay\"");
	// 支付类型， 固定值
	sb.append("&payment_type=\"1\"");
	// 参数编码， 固定值
	sb.append("&_input_charset=\"utf-8\"");
	// 设置未付款交易的超时时间
	// 默认30分钟，一旦超时，该笔交易就会自动被关闭。
	// 取值范围：1m～15d。
	// m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
	// 该参数数值不接受小数点，如1.5h，可转换为90m。
	sb.append("&it_b_pay=\"30m\"");

	// extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
	// orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

	// 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
	// orderInfo += "&return_url=";

	// 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
	// orderInfo += "&paymethod=\"expressGateway\"";
	return sb.toString();
}

public static String alipaySign(String content) {
	return SignUtils.sign(content, ALIPAY_COMMERCIAL_RSA);
}

/*RSA 加密签名*/
public class SignUtils {
	
	private static final String ALGORITHM = "RSA";

	private static final String SIGN_ALGORITHMS = "SHA1WithRSA";

	private static final String DEFAULT_CHARSET = "UTF-8";

	public static String sign(String content, String privateKey) {
		try {
			PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decode(privateKey));
			KeyFactory keyf = KeyFactory.getInstance(ALGORITHM);
			PrivateKey priKey = keyf.generatePrivate(priPKCS8);
			java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);
			signature.initSign(priKey);
			signature.update(content.getBytes(DEFAULT_CHARSET));
			byte[] signed = signature.sign();
			return Base64.encode(signed);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

/*URL utf-8 编码*/
public class URLEncoder {
    private URLEncoder() {}

    static UriCodec ENCODER = new UriCodec() {
        @Override protected boolean isRetained(char c) {
            return " .-*_".indexOf(c) != -1;
        }
    };

    /**
     * Equivalent to {@code encode(s, "UTF-8")}.
     *
     * @deprecated Use {@link #encode(String, String)} instead.
     */
    @Deprecated
    public static String encode(String s) {
        return ENCODER.encode(s, StandardCharsets.UTF_8);
    }

    /**
     * Encodes {@code s} using the {@link Charset} named by {@code charsetName}.
     */
    public static String encode(String s, String charsetName) throws UnsupportedEncodingException {
        return ENCODER.encode(s, Charset.forName(charsetName));
    }
}

/*签名类型*/
public static String getAlipaySignType() {
	return "sign_type=\"RSA\"";
}

/*返回值的处理*/
private Handler mHandler = new Handler() {
	public void handleMessage(Message msg) {
		switch (msg.what) {
			case MSG_WHAT_ALIPAY_CHECK: {
				boolean exist = (Boolean) msg.obj;
				Log.i("alipy exist", String.valueOf(exist));
				break;
			}
			
			case MSG_WHAT_ALIPAY_PAY: {
				PayResult payResult = new PayResult((String) msg.obj);

				String resultStatus = payResult.resultStatus;
				
				if (TextUtils.equals(resultStatus, "9000")) {
					//支付成功
					Bundle bundle = new Bundle();
					bundle.putString("last_activity", PayActivity.TAG);
					SystemUtils.launchActivity(PayActivity.this, MainActivity.class, bundle);
					//当MainActivity创建之后再关闭
				} else {
					
					if (TextUtils.equals(resultStatus, "8000")) {
						//正在处理中
						mToastManager.show("正在处理");
					} else if(TextUtils.equals(resultStatus, "4000")){
						//订单支付失败
						mToastManager.show("支付失败");
					} else if(TextUtils.equals(resultStatus, "6001")){
						//用户中途取消
						mToastManager.show("取消支付");
					}else if(TextUtils.equals(resultStatus, "6002")) {
						//网络连接出错
						mToastManager.show("网络出错");
					}
					SystemUtils.launchActivity(PayActivity.this, CourseOrderActivity.class);
					finish();
				}
				break;
			}

			default:
			break;
		}
	};
};




