package com.github.blazsolar.chuck.ui;

import android.app.Activity;
import android.support.v4.widget.DrawerLayout;
import android.view.ViewGroup;

import com.github.blazsolar.chuck.R;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Blaz Solar on 21/10/14.
 */
public class DebugAppContainer implements AppContainer {

    private Activity activity;

    @InjectView(R.id.debug_drawer_layout) DrawerLayout drawerLayout;
    @InjectView(R.id.debug_content) ViewGroup content;

    @Inject public DebugAppContainer() {
    }

    @Override
    public ViewGroup get(Activity activity) {
        this.activity = activity;

        activity.setContentView(R.layout.debug_activity_frame);

        ButterKnife.inject(this, activity);

        return content;
    }
}
