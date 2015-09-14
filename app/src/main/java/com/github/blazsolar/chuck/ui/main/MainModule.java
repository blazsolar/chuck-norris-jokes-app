package com.github.blazsolar.chuck.ui.main;

import com.github.blazsolar.chuck.data.JokesDatabase;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Blaz Solar on 23/08/14.
 */
@Module
public class MainModule {

    private MainView view;

    public MainModule(MainView view) {
        this.view = view;
    }

    @Provides
    public MainView provideView() {
        return view;
    }

    @Provides
    public MainPresenter providePresenter(MainView view, JokesDatabase jokesDatabase) {
        return new MainPresenterImpl(view, jokesDatabase);
    }

}
