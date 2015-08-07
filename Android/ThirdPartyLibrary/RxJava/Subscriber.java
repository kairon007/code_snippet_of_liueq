/**
 * Title: Subscriber
 * Tag: Subscriber
 * Update: 2015/08/06
 * Description: Subscriber的各种用法
 *	貌似new 一个Observer和new 一个Subscriber没有太大区别，因为Subscriber继承了Observer接口
 */

/**
 * 1. 创建一个标准的Subscriber对象
 */
new Subscriber<T>() {

    @Override
    public void onStart() {
	//开始调用，这些方法应该在Observable中调用
    }

    @Override
    public void onCompleted() {
	//处理结束调用
    }

    @Override
    public void onError(Throwable e) {
	//异常处理
    }

    @Override
    public void onNext(T t) {
	//需要进行的处理
    }
};
