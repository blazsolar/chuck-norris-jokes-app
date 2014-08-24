package com.github.blazsolar.chuck.ui.main;

import android.os.Bundle;
import android.widget.TextView;

import com.github.blazsolar.chuck.R;
import com.github.blazsolar.chuck.data.api.model.Joke;
import com.github.blazsolar.chuck.ui.BaseActivity;

import javax.inject.Inject;


public class MainActivity extends BaseActivity implements MainView {

    @Inject MainPresenter mPresenter;

    private TextView mJokeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mJokeView = (TextView) findViewById(R.id.joke);

        mPresenter.onCreate(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        mPresenter.onSaveInstanceState(outState);
    }

    @Override
    protected void onStop() {
        super.onStop();

        mPresenter.onStop();
    }

    @Override
    public Object[] getModules() {
        return new Object[] { new MainModule(this) };
    }

    @Override
    public void displayJoke(Joke joke) {
        mJokeView.setText(joke.getJoke());
    }
}
