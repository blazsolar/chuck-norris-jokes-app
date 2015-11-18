package com.github.blazsolar.chuck.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ViewGroup;

import javax.inject.Inject;

/**
 * Created by Blaz Solar on 23/08/14.
 */
public abstract class BaseActivity extends ActionBarActivity {

    @Inject AppContainer appContainer;

    private ViewGroup container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        inject();

        container = appContainer.get(this);
        getLayoutInflater().inflate(getContentLayout(), container);
    }

    protected abstract void inject();

    protected abstract int getContentLayout();
}
