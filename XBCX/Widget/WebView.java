/*
 * Title: 使用WebView来加载Url
 * Tag: web, webView, url
 * Update Time: 2015/07/01
 * Description:
 */

webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
webView.setWebViewClient(new XWebViewClient());
webView.getSettings().setUseWideViewPort(true);
webView.getSettings().setLoadWithOverviewMode(true);
webView.getSettings().setJavaScriptEnabled(true);
webView.setBackgroundColor(Color.TRANSPARENT);
webView.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);

webView.loadUrl(url);


public class XWebViewClient extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
	webView.loadUrl(url);
	return super.shouldOverrideUrlLoading(view, url);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
	super.onPageFinished(view, url);
	dismissXProgressDialog();
    }
}
