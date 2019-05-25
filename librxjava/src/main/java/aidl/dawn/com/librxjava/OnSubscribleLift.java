package aidl.dawn.com.librxjava;

/**
 *
 * @param <T>   想要的结果
 * @param <R>   实现的结果
 */
public class OnSubscribleLift<T,R> implements OnSubscrible<R>{
    OnSubscrible<T> onSubscrible;
    Func1<? super T, ? extends R> transfromer;

    public OnSubscribleLift(OnSubscrible<T> onSubscrible, Func1<? super T, ? extends R> transfromer){
        this.onSubscrible = onSubscrible;
        this.transfromer = transfromer;
    }


    @Override
    public void call(Subscrible<? super R> subscrible) {
        Subscrible<? super T> mSub = new OperaChange<>(transfromer,subscrible);
        onSubscrible.call(mSub);
    }

    /**
     *
     * @param <T>
     * @param <R>
     */
    class OperaChange<T,R> extends Subscrible<T>{
        Subscrible<? super R> actual;
        private Func1<? super T, ? extends R> transfromer;

        public OperaChange(Func1<? super T, ? extends R> transfromer, Subscrible<R> subscrible){
            this.actual = subscrible;
            this.transfromer = transfromer;
        }

        @Override
        public void onNext(T t) {
            R r = this.transfromer.call(t);
            actual.onNext(r);
        }
    }
}
