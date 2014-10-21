package com.github.blazsolar.chuck.integration.data;

import java.io.IOException;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;
import com.github.blazsolar.chuck.integration.data.api.JokesService;
import com.github.blazsolar.chuck.integration.data.api.model.Joke;
import com.github.blazsolar.chuck.integration.data.api.model.Response;
import com.github.blazsolar.chuck.integration.data.rx.EndObserver;

/**
 * Created by Blaz Solar on 23/08/14.
 */
public class JokesDatabase {

    private JokesService mJokesService;

    private PublishSubject<Joke> mJokeRequest;

    @Inject public JokesDatabase(JokesService jokesService) {
        mJokesService = jokesService;
    }

    public Subscription loadJoke(Observer<Joke> observer) {

        if (mJokeRequest != null) {
            // There is already request in progress. Joint it.
            return mJokeRequest.subscribe(observer);
        }

        mJokeRequest = PublishSubject.create();

        Subscription subscription = mJokeRequest.subscribe(observer);

        mJokeRequest.subscribe(new EndObserver<Joke>() {

            @Override
            public void onEnd() {
                mJokeRequest = null;
            }

            @Override
            public void onNext(Joke joke) {
                // do nothing
            }
        });

        mJokesService.randomJoke()
                .flatMap(new Func1<Response<Joke>, Observable<Joke>>() {
                    @Override
                    public Observable<Joke> call(Response<Joke> jokeResponse) {
                        if (jokeResponse != null && jokeResponse.getValue() != null) {
                            return Observable.just(jokeResponse.getValue());
                        } else {
                            return Observable.error(new IOException("Empty value"));
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mJokeRequest);

        return subscription;

    }

}
