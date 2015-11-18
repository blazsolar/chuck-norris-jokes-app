package com.github.blazsolar.chuck;

import android.app.Application;
import android.content.Context;

/**
 * Created by Blaz Solar on 23/08/14.
 */
public class App extends Application {

    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        buildComponentAndInject();
    }

    public void buildComponentAndInject() {
        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        component.inject(this);
    }

    public AppComponent getComponent() {
        return component;
    }

    /**
     * Return application instance.
     *
     * @param context Non null context
     * @return {@link App} instance
     */
    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }

}
