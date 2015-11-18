package com.github.blazsolar.chuck;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Blaz Solar on 23/08/14.
 */
@Module
public class AppModule {

    private App mApp;

    public AppModule(App app) {
        mApp = app;
    }

    @Provides public Context provideApplicationContext() {
        return mApp;
    }

    @Provides public App provideApplication() {
        return mApp;
    }
}
