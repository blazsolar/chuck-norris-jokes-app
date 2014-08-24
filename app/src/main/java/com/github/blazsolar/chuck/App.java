package com.github.blazsolar.chuck;

import android.app.Application;
import android.content.Context;

import java.util.Arrays;
import java.util.List;

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

        mObjectGraph = ObjectGraph.create(getModules().toArray());
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
     * Builds and returns list of dagger modules for application scope.
     *
     * @return List of dagger modules.
     */
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new AppModule(this));
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
