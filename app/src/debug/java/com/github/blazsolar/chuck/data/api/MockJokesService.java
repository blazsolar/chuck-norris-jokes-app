package com.github.blazsolar.chuck.data.api;

import com.github.blazsolar.chuck.data.api.model.Joke;
import com.github.blazsolar.chuck.data.api.model.Response;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * Created by Blaz Solar on 23/10/14.
 */
@Singleton
final public class MockJokesService implements JokesService {

    @Inject
    public MockJokesService() {
    }

    @Override
    public Observable<Response<Joke>> randomJoke() {

        Joke joke = new Joke(1, "Some random joke", new String[] {"geek"});

        return Observable.from(new Response<>(joke));

    }

}
