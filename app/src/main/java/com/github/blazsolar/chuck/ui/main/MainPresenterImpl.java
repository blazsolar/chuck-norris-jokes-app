package com.github.blazsolar.chuck.ui.main;

import android.os.Bundle;

import javax.inject.Inject;

import rx.Subscription;
import rx.observers.EmptyObserver;
import com.github.blazsolar.chuck.data.JokesDatabase;
import com.github.blazsolar.chuck.data.api.model.Joke;

/**
 * Created by Blaz Solar on 23/08/14.
 */
public class MainPresenterImpl implements MainPresenter {

    private static final String SAVED_STATE_JOKE = "joke";

    private final MainView mView;
    private final JokesDatabase mJokesDatabase;

    private Subscription mRequest;

    private Joke mJoke;

    @Inject
    public MainPresenterImpl(MainView view, JokesDatabase jokesDatabase) {
        mView = view;
        mJokesDatabase = jokesDatabase;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        handleJoke(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (mJoke != null) {
            outState.putParcelable(SAVED_STATE_JOKE, mJoke);
        }
    }

    @Override
    public void onStop() {
        if (mRequest != null) {
            mRequest.unsubscribe();
        }
    }

    private void handleJoke(Bundle savedInstanceState) {
        Joke joke = getSavedJoke(savedInstanceState);

        if (joke != null) {
            setJoke(joke);
        } else {
            makeJokeRequest();
        }
    }

    private Joke getSavedJoke(Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            return savedInstanceState.getParcelable(SAVED_STATE_JOKE);
        } else {
            return null;
        }

    }

    private void makeJokeRequest() {
        mRequest = mJokesDatabase.loadJoke(new EmptyObserver<Joke>() {

            @Override
            public void onNext(Joke joke) {
                if (joke != null) {
                    setJoke(joke);
                }
            }
        });
    }

    private void setJoke(Joke joke) {
        mView.displayJoke(joke);
        mJoke = joke;
    }

}
