package com.github.blazsolar.chuck.ui;

import android.app.Activity;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * An indicator which allows controlling the root container used for each activity.
 *
 * Created by Blaz Solar on 21/10/14.
 */
public interface AppContainer {

    /** The root {@link android.view.ViewGroup} into which the activity should place its contents. */
    ViewGroup get(Activity activity);

    /** And {@link com.github.blazsolar.chuck.ui.AppContainer} which returns the normal activity content view. */
    AppContainer DEFAULT = new AppContainer() {
        @Override
        public ViewGroup get(Activity activity) {
            return ButterKnife.findById(activity, android.R.id.content);
        }
    };

}
