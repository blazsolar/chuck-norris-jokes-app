package com.github.blazsolar.chuck.ui.main;

import android.content.Context;
import android.test.ActivityUnitTestCase;

import com.github.blazsolar.chuck.integration.mock.MockApplicationCompact;

import javax.inject.Inject;

public class MainActivityTest extends ActivityUnitTestCase<MainActivity> {

    @Inject MainPresenter mPresenter;

    Context mContext;
    MockApplicationCompact mApp;

    public MainActivityTest() {
        super(MainActivity.class);
    }

//  FIXME
//    @Override
//    protected void setUp() throws Exception {
//        super.setUp();
//
//        System.setProperty("dexmaker.dexcache", getInstrumentation().getTargetContext().getCacheDir().getPath());
//
//        mContext = new ContextThemeWrapper(getInstrumentation().getTargetContext(), R.style.AppTheme) {
//            @Override
//            public Context getApplicationContext() {
//                return mApp;
//            }
//        };
//        setActivityContext(mContext);
//
//        mApp = new TestApp(mContext);
//        mApp.onCreate();
//
//        setApplication(mApp);
//
////        mApp.inject(this);
//    }
//
//    public void testOnCreate() throws Exception {
//
//        Intent intent = new Intent(mContext, MainActivity.class);
//        startActivity(intent, null, null);
//
//        verify(mPresenter).onCreate(null);
//
//    }
//
//    public void testSavedInstanceState() throws Exception {
//
//        Intent intent = new Intent(getInstrumentation().getTargetContext(), MainActivity.class);
//        MainActivity activity = startActivity(intent, null, null);
//
//        Bundle bundle = new Bundle();
//        getInstrumentation().callActivityOnSaveInstanceState(activity, bundle);
//
//        verify(mPresenter).onSaveInstanceState(bundle);
//
//    }
//
//    public void testOnStop() throws Exception {
//
//        Intent intent = new Intent(getInstrumentation().getTargetContext(), MainActivity.class);
//        MainActivity activity = startActivity(intent, null, null);
//
//        getInstrumentation().callActivityOnStop(activity);
//
//        verify(mPresenter).onStop();
//
//    }
//
//    public void testDisplayJoke() throws Exception {
//
//        Intent intent = new Intent(getInstrumentation().getTargetContext(), MainActivity.class);
//        MainActivity activity = startActivity(intent, null, null);
//
//        Joke joke = new Joke(10, "Joke", null);
//        activity.displayJoke(joke);
//
//        CharSequence test = ((TextView) activity.findViewById(R.id.joke)).getText();
//
//        assertEquals("Joke", test);
//
//    }
//
//    @Module(
//            injects = {
//                    MainActivity.class,
//                    MainActivityTest.class,
//                    TestApp.class
//            },
//            library = true,
//            overrides = true,
//            complete = false
//    )
//    public class TestModule {
//
//        @Provides @Singleton
//        public MainPresenter providePresenter() {
//            return mock(MainPresenter.class);
//        }
//
//        @Provides @Singleton AppContainer provideAppContainer() {
//            return AppContainer.DEFAULT;
//        }
//
//    }
//
//    private class TestApp extends MockApplicationCompact {
//
//        public TestApp(Context context) {
//            super(context);
//        }
//
//    }
}
