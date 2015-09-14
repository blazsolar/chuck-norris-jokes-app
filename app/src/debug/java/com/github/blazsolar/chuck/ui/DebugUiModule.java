package com.github.blazsolar.chuck.ui;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Blaz Solar on 21/10/14.
 */
@Module
public class DebugUiModule {

    @Provides AppContainer provideAppContainer(DebugAppContainer debugAppContainer) {
        return debugAppContainer;
    }

}
