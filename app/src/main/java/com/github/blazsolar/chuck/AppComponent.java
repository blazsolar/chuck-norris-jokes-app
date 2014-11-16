package com.github.blazsolar.chuck;

import com.github.blazsolar.chuck.data.JokesDatabase;
import com.github.blazsolar.chuck.ui.AppContainer;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Blaz Solar on 15/11/14.
 */
@Singleton @Component(modules = {
        AppModule.class,
        DebugModule.class
})
public interface AppComponent {
    void inject(App app);

    AppContainer appContainer();
    JokesDatabase jokesDatabase();
}
