/**
 * Title: SimplePullToRefresh Tips
 * Tag: ListView, SimplePullToRefresh, Tips
 * Update: 2015/07/06
 * Description: 关于SimplePullToRefresh的一些要点
 */

//关联Runner，传递参数
registerPlugin(new SimplePullToRefreshPlugin(mPullToRefreshPlugin, SetAdapter<>)
    .setLoadEventCode(CODE)
    .setLoadEventParamProvider(this));  //提供了参数，最好是用Activity实现接口
