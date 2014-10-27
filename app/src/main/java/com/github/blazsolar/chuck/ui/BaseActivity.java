package com.github.blazsolar.chuck.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ViewGroup;

import com.github.blazsolar.chuck.App;

import javax.inject.Inject;

import dagger.ObjectGraph;

/**
 * Created by Blaz Solar on 23/08/14.
 */
public abstract class BaseActivity extends ActionBarActivity {

    @Inject AppContainer appContainer;

    private ObjectGraph mObjectGraph;

    private ViewGroup container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mObjectGraph = ((App) getApplication()).createScopedGraph(getModules());
        mObjectGraph.inject(this);

        container = appContainer.get(this);

        getLayoutInflater().inflate(getContentLayout(), container);
    }

    @Override
    protected void onDestroy() {
        mObjectGraph = null;

        super.onDestroy();
    }

    public abstract Object[] getModules();

    public abstract int getContentLayout();
}
