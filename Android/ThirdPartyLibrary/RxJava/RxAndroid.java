/**
 * Title: RxAndroid 中常见用法
 * Tag: 
 * Update: 2015/08/07
 * Description: 
 */

/**
 * 1.关于生命周期
 */
AppObservable.bindActivity(); 
AppObservable.bindFragment();
AppObservable.bindSupportFragment(); //区别应该是是否使用v4 lib


/**
 * 2.线程的执行选择
 */
AndroidSchedulers.handlerThread(handler); //选择在一个Handler的线程执行
AndroidSchedulers.mainThread(); //Android 的UI线程
Schedulers.newThread(); //一个新子线程执行
Schedulers.io(); //IO线程，一般是网络或者数据读取使用


/**
 * 3.map操作
 	map操作默认都是在UI线程，暂时没找到方法修改
 */
map(new Func1<T, S>(){

    @Override
    public S call(T t){
	//将T类型转换为S类型
	//中间的变换可随意添加
    }
}


/**
 * 3.5 flatMap操作符
 *	返回一个Observable
 */
flatMap(new Fun1<List<Contributor>, Observable<Contributor>>(){
    @Override
    public Observalble<Contributor> call(List<Contributor> contributors){
	return Observable.from(contributors);
    }
}

/**
 * 4.buffer操作符
 *	增加了Buffer操作复制后，会在每个指定的时间段，将接收到的要处理的对象封装到list中，然后到时间了再交给Subscriber处理
 *	注意，进行了订阅操作，那么buffer会一直监听，每个一段时间就给Subscriber发送内容，即便是null。可能会比较耗资源，暂时没发现比较合适的用法
 */
buffer(2, TimeUnit.SECONDS)

//简单示例
ViewObservable.clicks(mButtonAsync)
    .map(new Func1<OnClickEvent, String>() {
	@Override
        public String call(OnClickEvent onClickEvent) {
        	return "hello world";
        }
    })
    .buffer(2, TimeUnit.SECONDS)
    .observeOn(AndroidSchedulers.mainThread())
    .subscribe(new Action1<List<String>>() {
	@Override
	public void call(List<String> strings) {
	    StringBuffer sb = new StringBuffer();
	    for (String s : strings) {
		sb.append(s);
		sb.append("\n");
	    }

	    Toast.makeText(MainActivity.this, sb.toString(), Toast.LENGTH_SHORT).show();
	}
    });

/**
 * 5. debounce操作符
 *	类似于delay，延迟一段时间之后再传递哥Subscriber
 */

debounce(400, TimeUnit.MILLISECONDS)


