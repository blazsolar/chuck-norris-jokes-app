package com.github.blazsolar.chuck.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import butterknife.ButterKnife;

/**
 * Created by Blaz Solar on 22/10/14.
 */
public class NetworkDelayAdapter extends BaseAdapter {

    private static final long[] VALUES = {
            250, 500, 1000, 2000, 3000
    };

    public static int getPositionForValue(long value) {
        for (int i = 0; i < VALUES.length; i++) {
            if (VALUES[i] == value) {
                return i;
            }
        }
        return 3; // Default to 2000 if something changes.
    }

    private final LayoutInflater inflater;

    NetworkDelayAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return VALUES.length;
    }

    @Override
    public Long getItem(int position) {
        return VALUES[position];
    }

    @Override
    public long getItemId(int position) {
        return VALUES[position];
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            view = inflater.inflate(android.R.layout.simple_spinner_item, parent, false);
        }

        TextView tv = ButterKnife.findById(view, android.R.id.text1);
        tv.setText(getItem(position) + " ms");

        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            view = inflater.inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        }

        TextView tv = ButterKnife.findById(view, android.R.id.text1);
        tv.setText(getItem(position) + " ms");

        return view;

    }
}
