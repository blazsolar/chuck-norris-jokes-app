package com.github.blazsolar.chuck.data;

import android.test.AndroidTestCase;

import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;

import junit.framework.Assert;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.ObjectGraph;
import dagger.Provides;
import retrofit.Endpoint;
import retrofit.Endpoints;
import rx.observers.EmptyObserver;
import com.github.blazsolar.chuck.data.api.JokesService;
import com.github.blazsolar.chuck.data.api.model.Joke;

public class JokesDatabaseTest extends AndroidTestCase {

    @Inject JokesService mService;
    @Inject JokesDatabase mDatabase;
    @Inject MockWebServer mServer;

    ObjectGraph mObjectGraph;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        mObjectGraph = ObjectGraph.create(new TestModule());
        mObjectGraph.inject(this);
    }

    @Override
    public void tearDown() throws Exception {
        mObjectGraph = null;
        mServer.shutdown();

        super.tearDown();
    }

    public void testLoadJoke() throws Exception {

        mServer.enqueue(new MockResponse()
            .setBody("{ \"type\": \"success\", \"value\": { \"id\": 273, \"joke\": \"Chuck Norris joke.\", \"categories\": [\"nerdy\"] } }"));

        final CountDownLatch lock = new CountDownLatch(1);

        mDatabase.loadJoke(new EmptyObserver<Joke>() {

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

        mServer.enqueue(new MockResponse()
                .setBody("{ \"type\": \"success\" }"));

        final CountDownLatch lock = new CountDownLatch(1);

        mDatabase.loadJoke(new EmptyObserver<Joke>() {

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

    @Module(
            injects = JokesDatabaseTest.class,
            complete = false,
            overrides = true,
            includes = DataModule.class
    )
    public class TestModule {

        @Provides @Singleton
        MockWebServer provideMockWebServer() {
            MockWebServer server = new MockWebServer();
            try {
                server.play();
            } catch (IOException e) {
                Assert.fail();
            }
            return server;
        }

        @Provides @Singleton
        Endpoint provideEndpoint(MockWebServer server) {
            return Endpoints.newFixedEndpoint(server.getUrl("/").toString());
        }

    }
}