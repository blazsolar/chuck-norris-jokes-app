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
public class NetworkErrorAdapter extends BaseAdapter {

    private static final int[] VALUES = {
            0, 3, 10, 25, 100
    };

    public static int getPositionForValue(int value) {
        for (int i = 0; i < VALUES.length; i++) {
            if (VALUES[i] == value) {
                return i;
            }
        }
        return 1; // Default to 3& if something changes.
    }

    private final LayoutInflater inflater;

    public NetworkErrorAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return VALUES.length;
    }

    @Override
    public Integer getItem(int position) {
        return VALUES[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            view = inflater.inflate(android.R.layout.simple_spinner_item, parent, false);
        }

        TextView tv = ButterKnife.findById(view, android.R.id.text1);
        int item = getItem(position);
        if (item == 0) {
            tv.setText("None");
        } else {
            tv.setText(item + "%");
        }

        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            view = inflater.inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        }

        TextView tv = ButterKnife.findById(view, android.R.id.text1);
        int item = getItem(position);
        if (item == 0) {
            tv.setText("None");
        } else {
            tv.setText(item + "%");
        }

        return view;
    }
}
