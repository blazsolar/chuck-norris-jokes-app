package com.github.blazsolar.chuck.ui;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Blaz Solar on 21/10/14.
 */
@Module(
        injects = DebugAppContainer.class,
        complete = false,
        library = true,
        overrides = true
)
public class DebugUiModule {

    @Provides @Singleton AppContainer provideAppContainer(DebugAppContainer debugAppContainer) {
        return debugAppContainer;
    }

}
