package com.github.blazsolar.chuck.data.rx;

import rx.Observer;

/**
 * Created by Blaz Solar on 23/08/14.
 */
public abstract class EndObserver<T> implements Observer<T> {

    @Override
    public void onCompleted() {
        onEnd();
    }

    @Override
    public void onError(Throwable e) {
        onEnd();
    }

    public abstract void onEnd();
}
