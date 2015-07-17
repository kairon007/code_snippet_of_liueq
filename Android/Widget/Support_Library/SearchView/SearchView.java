/**
 * Title: Toolbar的搜索栏, SearchView
 * Tag: SearchView, Search
 * Update: 2015/07/17
 * Description:
 *  1. 主要有两种方式，一种是点击键盘上的搜索按钮，跳转到SearchResult的Activity；另一种是不跳转，监听SearchView的Text变化，实时的过滤掉当前的List中的结果
 */

/**
 * 采用实时过滤的写法
 * SearchResultActivity就是当前的Activity，注意配置要和Manifest中一致
 */
MenuItem searchItem = menu.findItem(R.id.action_search);
SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
SearchView searchView = null;
if(searchItem != null){
    searchView = (SearchView) searchItem.getActionView();
}
if (searchView != null) {
    searchView.setSearchableInfo(searchManager.getSearchableInfo(new ComponentName(this, this)));
    searchView.setSubmitButtonEnable(true);//设置是否可以提交

    /**
     * 当SearchView输入的内容变化时候触发
     */
    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
	@Override
	public boolean onQueryTextSubmit(String query) {
	    return false;
	}

	@Override
	public boolean onQueryTextChange(String newText) {
	    //进行Search
	    return false;
	}
    });
    
    /**
     * 当焦点从SearchView移开的时候触发
     */
    searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
	@Override
	public void onFocusChange(View v, boolean hasFocus) {
	    //当没有焦点的时候，恢复原本的内容
	    if(!hasFocus){
	    }
	}
    });

    /**
     * 当关闭SearchView的时候触发
     */
    searchView.setOnCloseListener(new SearchView.OnCloseListener() {
	@Override
	public boolean onClose() {
	    presenter.loadData();
	    return false;
	}
    });

}

/*******************************************/

/**
 * 采用跳转到SearchRusltActvity的方法
 */
MenuItem searchItem = menu.findItem(R.id.action_search);
SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
SearchView searchView = null;
if(searchItem != null){
    searchView = (SearchView) searchItem.getActionView();
}
if (searchView != null) {
    searchView.setSearchableInfo(searchManager.getSearchableInfo(new ComponentName(this, SearchResultActivity.class))); //注意这要指明跳转的Activity
}


/**
 * ResultActivity接受搜索信息
 */

@Override
protected void onNewIntent(Intent intent){//activity重新置顶
    super.onNewIntent(intent);
    doQuery(intent);
}


private void doQuery(Intent intent){
    String queryAction=intent.getAction();
    if(Intent.ACTION_SEARCH.equals(intent.getAction())){
	String queryString=intent.getStringExtra(SearchManager.QUERY);
	//TODO 获得Key之后就可以写搜索逻辑
    }
}
