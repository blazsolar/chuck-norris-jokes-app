package com.github.blazsolar.chuck.ui.main;

import android.os.Bundle;

/**
 * Created by Blaz Solar on 23/08/14.
 */
public interface MainPresenter {

    void onCreate(Bundle savedInstanceState);

    void onSaveInstanceState(Bundle outState);

    void onStop();

}
