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
public class EnumAdapter<T extends Enum<?>> extends BaseAdapter {

    private final LayoutInflater layoutInflater;
    private final T[] enumConstants;
    private final boolean showNull;
    private final int nullOffset;

    public EnumAdapter(Context context, Class<T> enumType) {
        this(context, enumType, false);
    }

    public EnumAdapter(Context context, Class<T> enumType, boolean showNull) {
        layoutInflater = LayoutInflater.from(context);
        enumConstants = enumType.getEnumConstants();
        this.showNull = showNull;
        this.nullOffset = showNull ? 1 : 0;
    }

    @Override
    public int getCount() {
        return enumConstants.length + nullOffset;
    }

    @Override
    public T getItem(int position) {
        if (showNull && position == 0) {
            return null;
        }

        return enumConstants[position - nullOffset];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(android.R.layout.simple_spinner_item, parent, false);
        }

        TextView tv = ButterKnife.findById(view, android.R.id.text1);
        tv.setText(getName(getItem(position)));

        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        }

        TextView tv = ButterKnife.findById(view, android.R.id.text1);
        tv.setText(getName(getItem(position)));

        return view;

    }

    protected String getName(T item) {
        return String.valueOf(item);
    }

}
