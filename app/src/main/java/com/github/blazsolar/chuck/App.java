package com.github.blazsolar.chuck;

import android.app.Application;
import android.content.Context;

import dagger.ObjectGraph;

/**
 * Created by Blaz Solar on 23/08/14.
 */
public class App extends Application {

    /** Application object graph */
    private ObjectGraph mObjectGraph;

    @Override
    public void onCreate() {
        super.onCreate();

        mObjectGraph = ObjectGraph.create(Modules.list(this));
    }

    /**
     * Creates and return scoped object graph. It uses application object graph and
     * adds modules passed in from parameters.
     *
     * @param modules Modules to add to application object graph.
     * @return Merged object graph
     */
    public ObjectGraph createScopedGraph(Object... modules) {
        return mObjectGraph.plus(modules);
    }

    public void inject(Object object) {
        mObjectGraph.inject(object);
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
