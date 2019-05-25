package aidl.dawn.com.librxjava;

/**
 * 容器观察者和被观察者存放的容器
 * @param <T>
 */
public class Observable<T> {
    private OnSubscrible<T> onSubscrible;

    public static <T> Observable<T> create(OnSubscrible<T> onSubscrible){
        return new Observable<>(onSubscrible);
    }

    public Observable(OnSubscrible<T> onSubscrible){
        this.onSubscrible = onSubscrible;
    }

    public void subscrible(Subscrible<? super T> subscrible){
        onSubscrible.call(subscrible);
    }

    public <R> Observable<R> map(Func1<? super T, ? extends R> func1){
        return new Observable<R>(new OnSubscribleLift<T,R>(onSubscrible, func1));
    };

    public Observable<T> subscribleOnIO(){
        return create(new OnSubscribleThread<T>(this));
    }
}
