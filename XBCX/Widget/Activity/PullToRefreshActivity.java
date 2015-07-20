/**
 * Title: PullToRefreshActivity
 * Tag: PullToRefresh, activity
 * Update: 2015/07/20
 * Description
 */

/* 0001. 第一次打开的时候是否隐藏View */
setIsHideViewFirstLoad();

/* 0002. 设置List Item的长按事件 */
mPullToRefreshPlugin.setOnItemLongClickListener(this);

/* 0003. 获取PullToRefresh 中的ListView */
getListView();


/* 0004. 禁止刷新 */
disableRefresh();

/* 0005. 设置当没有内容显示的默认文字 */
setNoResultTextId();


/* 0006. 手动设置下拉刷新 */
@Override
public void onPullDownToRefresh() {
    pushEventRefresh(BFMEventCode.HTTP_GoldBuy);
}


