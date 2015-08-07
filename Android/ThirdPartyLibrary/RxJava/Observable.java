/**
 * Title: Observable
 * Tag: Observable
 * Update: 2015/08/07
 * Description: Observable的各种用法
 */

/**
 * 1. 创建一个标准的Observable对象
 */
Observable.create(new Observable.OnSubscribe<T>() {
    @Override
    public void call(Subscriber<? super T> subscriber) {
	subscriber.onStart();
	subscriber.onNext(T); //在这里传入想要交给Subscriber处理的对象
	subscriber.onCompleted();
    }
});


/**
 * 2.监听点击事件的Observable
 *  当点击的时候触发，因为此Observable没有实体，所以具体的操作必须要在map或者其他中间层进行
 *  注意，因为此observable是监听的点击事件，所以是在UI线程，从而map的操作也是在ui线程
 *
 */
ViewObservable.clicks(button, true) //true或fales是指是否自动发出第一次点击事件，如果是true，那么第一次会自动发出
    .map(new Func1<OnClickEvent, T>() {
	@Override
	public Integer call(OnClickEvent onClickEvent) {
	    return t;
	}
    });

/**
 * 3.监听EditText的变化
 */

Observable<OnTextChangeEvent> textChangeObservable = WidgetObservable.text(mSearch);//将EditText和WidgetObservable绑定

mSubscription = AppObservable.bindActivity(this, textChangeObservable)
    .debounce(400, TimeUnit.MILLISECONDS) //延迟400毫秒后再执行下一步操作
    .observeOn(AndroidSchedulers.mainThread())
    .subscribe(getSearcheObserver());
