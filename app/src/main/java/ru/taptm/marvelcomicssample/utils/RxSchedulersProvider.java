package ru.taptm.marvelcomicssample.utils;

import io.reactivex.Scheduler;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RxSchedulersProvider {
    public static <T> SingleTransformer<T, T> applyOpBeforeAndAfter(Runnable before, Runnable after)  {
        return upstream -> upstream
                .doOnSubscribe(__ -> before.run())
                .doOnError(__ -> after.run())
                .doAfterSuccess(__ -> after.run());
    }

    public static  <T> SingleTransformer<T, T> getComputationToMainTransformerSingle()  {
        return objectObservable -> objectObservable
                .subscribeOn(getComputationScheduler())
                .observeOn(getMainThreadScheduler());
    }

    private static Scheduler getMainThreadScheduler() {
        return AndroidSchedulers.mainThread();
    }

    private static Scheduler getIOScheduler() {
        return Schedulers.io();
    }

    private static Scheduler getComputationScheduler() {
        return Schedulers.computation();
    }
}
