/**
 * Title: ConcurrencySchedulerSample
 * Tag: RxJava, RxAndroid, Concurrency
 * Update: 2015/08/06
 * Description: 使用RxJava实现异步加载的简单示例
 */

Subscription mSubscription; //保存关联信息，当destroy的时候，需要调用 unsubscribe()方法

void doLoad(){
    mSubscription = AppObservable.bindSupportFragment(this, getObservable())	//绑定fragment生命周期，获取一个Observable
	.subscribeOn(Schedulers.io())						//在IO线程执行Observable的操作
	.observeOn(AndroidSchedulers.mainThread())				//在UI线程执行Subscriber的操作
	.subscribe(getObserver());						//获取一个Subscriber
}

Observable<Boolean> getObservable(){
    return Observable.just(true)
	.map(aBoolean -> {
	    //实际耗时操作，实际上map是负责变换的，这里只是做演示
	    return aBoolean
	});
}


private Observer<Boolean> getObserver(){
    return new Subscriber<Boolean>(){
	@Override
	public void onCompleted(){};

	@Override
	public void onError(Throwable e){}

	@Override
	public void onNext(Boolean aBoolean){
	    //更新UI
	}
    };
}
