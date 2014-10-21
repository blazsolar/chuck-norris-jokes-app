package com.github.blazsolar.chuck.integration.main;

import android.os.Bundle;
import android.test.AndroidTestCase;

import org.mockito.ArgumentCaptor;

import rx.Observer;
import com.github.blazsolar.chuck.data.JokesDatabase;
import com.github.blazsolar.chuck.data.api.model.Joke;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MainPresenterImplTest extends AndroidTestCase {

    MainView mView;
    JokesDatabase mDatabase;
    MainPresenterImpl mPresenter;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        System.setProperty("dexmaker.dexcache", getContext().getCacheDir().getPath());

        mView = mock(MainView.class);
        mDatabase = mock(JokesDatabase.class);
        mPresenter = new MainPresenterImpl(mView, mDatabase);
    }

    public void testOnCreate() throws Exception {

        mPresenter.onCreate(null);

        ArgumentCaptor<Observer> argument = ArgumentCaptor.forClass(Observer.class);
        verify(mDatabase).loadJoke(argument.capture());

        Joke joke = new Joke();
        argument.getValue().onNext(joke);

        verify(mView).displayJoke(joke);

    }

    public void testOnCreateRestore() throws Exception {

        Joke joke = new Joke(10, "Joke", null);

        Bundle savedState = new Bundle();
        savedState.putParcelable("joke", joke);

        mPresenter.onCreate(savedState);

        verify(mView).displayJoke(joke);

    }

    public void testSavedStateNull() throws Exception {

        Bundle savedState = new Bundle();

        mPresenter.onSaveInstanceState(savedState);

        assertFalse(savedState.containsKey("joke"));

    }

    public void testSavedState() throws Exception {

        Joke joke = new Joke(10, "Joke", null);
        Bundle savedState = new Bundle();
        savedState.putParcelable("joke", joke);

        mPresenter.onCreate(savedState);

        Bundle outState = new Bundle();

        mPresenter.onSaveInstanceState(outState);

        assertTrue(outState.containsKey("joke"));

        Joke outJoke = outState.getParcelable("joke");
        assertEquals(joke.getId(), outJoke.getId());
        assertEquals(joke.getJoke(), outJoke.getJoke());
        assertNull(outJoke.getCategories());


    }
}