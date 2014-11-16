package com.github.blazsolar.chuck.ui;

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

    @Provides AppContainer provideAppConainer() {
        return AppContainer.DEFAULT;
    }

}
