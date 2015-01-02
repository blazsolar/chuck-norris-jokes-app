package com.github.blazsolar.chuck.integration.data;

import android.test.AndroidTestCase;

import com.github.blazsolar.chuck.data.JokesDatabase;
import com.github.blazsolar.chuck.data.api.JokesService;
import com.github.blazsolar.chuck.data.api.model.Joke;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.observers.EmptyObserver;

import static org.mockito.Mockito.when;

public class JokesDatabaseTest extends AndroidTestCase {

    @Mock JokesService service;
    JokesDatabase database;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        MockitoAnnotations.initMocks(this);

        database = new JokesDatabase(service);
    }

    public void testLoadJoke() throws Exception {

        Joke joke = new Joke(273, "Chuck Norris joke.", new String[]{"nerdy"});

        com.github.blazsolar.chuck.data.api.model.Response<Joke> response = new com.github.blazsolar.chuck.data.api.model.Response<>(joke);

        when(service.randomJoke()).thenReturn(
                Observable.just(response));

        final CountDownLatch lock = new CountDownLatch(1);

        database.loadJoke(new EmptyObserver<Joke>() {

            @Override
            public void onNext(Joke joke) {
                assertEquals(273, joke.getId());
                assertEquals("Chuck Norris joke.", joke.getJoke());
                assertEquals(1, joke.getCategories().length);
                assertEquals("nerdy", joke.getCategories()[0]);
                lock.countDown();
            }

            @Override
            public void onError(Throwable e) {
                fail();
            }
        });

        if (!lock.await(2000, TimeUnit.MILLISECONDS)) {
            fail();
        }

    }

    public void testLoadJokeEmpty() throws Exception {

        com.github.blazsolar.chuck.data.api.model.Response<Joke> response = new com.github.blazsolar.chuck.data.api.model.Response<>();

        when(service.randomJoke()).thenReturn(
                Observable.just(response));

        final CountDownLatch lock = new CountDownLatch(1);

        database.loadJoke(new EmptyObserver<Joke>() {

            @Override
            public void onNext(Joke joke) {
                fail();
            }

            @Override
            public void onError(Throwable e) {
                assertEquals("Empty value", e.getMessage());
                lock.countDown();
            }
        });

        if (!lock.await(2000, TimeUnit.MILLISECONDS)) {
            fail();
        }

    }

}
