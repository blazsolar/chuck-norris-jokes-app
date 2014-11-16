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
        component = Dagger_AppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        component.inject(this);
    }

//    /**
//     * Creates and return scoped object graph. It uses application object graph and
//     * adds modules passed in from parameters.
//     *
//     * @param modules Modules to add to application object graph.
//     * @return Merged object graph
//     */
//    public ObjectGraph createScopedGraph(Object... modules) {
//        return objectGraph.plus(modules);
//    }

//    public void inject(Object object) {
//        objectGraph.inject(object);
//    }


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
