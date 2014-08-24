package com.github.blazsolar.chuck.ui.main;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.test.ActivityUnitTestCase;
import android.widget.TextView;

import com.github.blazsolar.chuck.R;
import com.github.blazsolar.chuck.data.api.model.Joke;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import si.dlabs.exampleproject.mock.MockApplicationCompact;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MainActivityTest extends ActivityUnitTestCase<MainActivity> {

    @Inject MainPresenter mPresenter;

    Context mContext;
    MockApplicationCompact mApp;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        System.setProperty("dexmaker.dexcache", getInstrumentation().getTargetContext().getCacheDir().getPath());

        mContext = new ContextWrapper(getInstrumentation().getTargetContext()) {
            @Override
            public Context getApplicationContext() {
                return mApp;
            }
        };

        final List<Object> modules = Arrays.<Object>asList(new TestModule());

        mApp = new MockApplicationCompact(mContext) {
            @Override
            protected List<Object> getModules() {
                return modules;
            }
        };
        mApp.onCreate();

        setApplication(mApp);

        mApp.inject(this);
    }

    public void testOnCreate() throws Exception {

        Intent intent = new Intent(getInstrumentation().getTargetContext(), MainActivity.class);
        startActivity(intent, null, null);

        verify(mPresenter).onCreate(null);

    }

    public void testSavedInstanceState() throws Exception {

        Intent intent = new Intent(getInstrumentation().getTargetContext(), MainActivity.class);
        MainActivity activity = startActivity(intent, null, null);

        Bundle bundle = new Bundle();
        getInstrumentation().callActivityOnSaveInstanceState(activity, bundle);

        verify(mPresenter).onSaveInstanceState(bundle);

    }

    public void testOnStop() throws Exception {

        Intent intent = new Intent(getInstrumentation().getTargetContext(), MainActivity.class);
        MainActivity activity = startActivity(intent, null, null);

        getInstrumentation().callActivityOnStop(activity);

        verify(mPresenter).onStop();

    }

    public void testDisplayJoke() throws Exception {

        Intent intent = new Intent(getInstrumentation().getTargetContext(), MainActivity.class);
        MainActivity activity = startActivity(intent, null, null);

        Joke joke = new Joke(10, "Joke", null);
        activity.displayJoke(joke);

        CharSequence test = ((TextView) activity.findViewById(R.id.joke)).getText();

        assertEquals("Joke", test);

    }

    @Module(
            injects = {
                    MainActivity.class,
                    MainActivityTest.class
            },
            library = true,
            overrides = true
    )
    public class TestModule {

        @Provides @Singleton
        public MainPresenter providePresenter() {
            return mock(MainPresenter.class);
        }

    }
}