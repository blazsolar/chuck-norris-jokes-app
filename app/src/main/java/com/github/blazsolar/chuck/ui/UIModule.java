package com.github.blazsolar.chuck.ui;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Blaz Solar on 21/10/14.
 */
@Module(
        complete = true,
        library = true
)
public class UIModule {

    @Provides @Singleton AppContainer provideAppConainer() {
        return AppContainer.DEFAULT;
    }

}
