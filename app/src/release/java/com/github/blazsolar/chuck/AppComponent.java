package com.github.blazsolar.chuck;

import com.github.blazsolar.chuck.data.JokesDatabase;
import com.github.blazsolar.chuck.data.ReleaseDataModule;
import com.github.blazsolar.chuck.ui.AppContainer;
import com.github.blazsolar.chuck.ui.ReleaseUIModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Blaz Solar on 15/11/14.
 */
@Singleton @Component(modules = {
        AppModule.class,
        ReleaseUIModule.class,
        ReleaseDataModule.class
})
public interface AppComponent {
    void inject(App app);

    AppContainer appContainer();
    JokesDatabase jokesDatabase();
}
