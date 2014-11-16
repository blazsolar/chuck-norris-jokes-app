package com.github.blazsolar.chuck;

import android.content.Context;

import com.github.blazsolar.chuck.data.DataModule;
import com.github.blazsolar.chuck.ui.UIModule;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Blaz Solar on 23/08/14.
 */
@Module(
        injects = App.class,
        library = true,
        includes = {
                DataModule.class,
                UIModule.class
        }
)
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
