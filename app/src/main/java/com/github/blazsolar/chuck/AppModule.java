package com.github.blazsolar.chuck;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import com.github.blazsolar.chuck.data.DataModule;

/**
 * Created by Blaz Solar on 23/08/14.
 */
@Module(
        injects = App.class,
        library = true,
        includes = DataModule.class
)
public class AppModule {

    private App mApp;

    public AppModule(App app) {
        mApp = app;
    }

    @Provides
    public Context provideApplicationContext() {
        return mApp;
    }
}
