package com.github.blazsolar.chuck.ui.main;

import com.github.blazsolar.chuck.AppComponent;
import com.github.blazsolar.chuck.ui.PerActivity;

import dagger.Component;

/**
 * Created by Blaz Solar on 15/11/14.
 */
@PerActivity
@Component(
        dependencies = AppComponent.class,
        modules = MainModule.class
)
public interface MainComponent {
    void inject(MainActivity module);
}
