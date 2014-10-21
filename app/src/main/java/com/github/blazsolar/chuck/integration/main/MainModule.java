package com.github.blazsolar.chuck.integration.main;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import com.github.blazsolar.chuck.data.JokesDatabase;

/**
 * Created by Blaz Solar on 23/08/14.
 */
@Module(
        injects = MainActivity.class,
        complete = false
)
public class MainModule {

    private MainView mView;

    public MainModule(MainView view) {
        mView = view;
    }

    @Provides @Singleton
    public MainView provideView() {
        return mView;
    }

    @Provides @Singleton
    public MainPresenter providePresenter(MainView view, JokesDatabase jokesDatabase) {
        return new MainPresenterImpl(view, jokesDatabase);
    }

}
