package com.github.blazsolar.chuck;

import com.github.blazsolar.chuck.ui.DebugUiModule;

import dagger.Module;

/**
 * Created by Blaz Solar on 21/10/14.
 */
@Module(
    addsTo = AppModule.class,
    includes = {
            DebugUiModule.class
    },
    overrides = true
)
public class DebugModule {
}
