/**
 * Title: Subscriber
 * Tag: Subscriber
 * Update: 2015/08/06
 * Description: Subscriber的各种用法
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
