package com.github.blazsolar.chuck.ui;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Blaz Solar on 21/10/14.
 */
@Module
public class ReleaseUIModule {

    @Provides AppContainer provideAppConainer() {
        return AppContainer.DEFAULT;
    }

}
