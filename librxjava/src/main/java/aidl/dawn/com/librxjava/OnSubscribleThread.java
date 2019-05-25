package aidl.dawn.com.librxjava;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OnSubscribleThread<T> implements OnSubscrible<T> {
    private static ExecutorService executorService = Executors.newCachedThreadPool();
    Observable<T> source;

    public OnSubscribleThread(Observable<T> observable){
        source = observable;
    }


    @Override
    public void call(final Subscrible<? super T> subscrible) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                source.subscrible(subscrible);
            }
        };
        executorService.submit(runnable);
    }
}
