package com.github.blazsolar.chuck.ui;

import android.app.Activity;
import android.os.Bundle;

import dagger.ObjectGraph;
import com.github.blazsolar.chuck.App;

/**
 * Created by Blaz Solar on 23/08/14.
 */
public abstract class BaseActivity extends Activity {

    private ObjectGraph mObjectGraph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mObjectGraph = ((App) getApplication()).createScopedGraph(getModules());
        mObjectGraph.inject(this);
    }

    @Override
    protected void onDestroy() {
        mObjectGraph = null;

        super.onDestroy();
    }

    public abstract Object[] getModules();
}
