package com.github.blazsolar.chuck.ui.main;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;
import com.github.blazsolar.chuck.App;
import com.github.blazsolar.chuck.R;
import com.github.blazsolar.chuck.data.api.model.Joke;
import com.github.blazsolar.chuck.ui.BaseActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends BaseActivity implements MainView {

    @Inject MainPresenter presenter;

    @InjectView(R.id.toolbar) Toolbar toolbar;
    @InjectView(R.id.joke) TextView jokeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Crashlytics.start(this);
        ButterKnife.inject(this);

        setSupportActionBar(toolbar);
        presenter.onCreate(savedInstanceState);
    }

    @Override
    protected void inject() {
        DaggerMainComponent.builder()
                .appComponent(App.get(this).getComponent())
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        presenter.onSaveInstanceState(outState);
    }

    @Override
    protected void onStop() {
        super.onStop();

        presenter.onStop();
    }

    @Override
    protected int getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void displayJoke(Joke joke) {
        jokeView.setText(joke.getJoke());
    }
}
