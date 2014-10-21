package com.github.blazsolar.chuck.integration.data.api.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Blaz Solar on 23/08/14.
 */
public class Joke implements Parcelable {

    private long id;
    private String joke;
    private String[] categories;

    public Joke() {
    }

    public Joke(long id, String joke, String[] categories) {
        this.id = id;
        this.joke = joke;
        this.categories = categories;
    }

    private Joke(Parcel in) {
        id = in.readLong();
        joke = in.readString();

        int cnt = in.readInt();
        if (cnt > -1) {
            categories = new String[cnt];
            in.readStringArray(categories);
        }
    }

    public long getId() {
        return id;
    }

    public String getJoke() {
        return joke;
    }

    public String[] getCategories() {
        return categories;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(joke);

        if (categories != null) {
            dest.writeInt(categories.length);
            dest.writeStringArray(categories);
        } else {
            dest.writeInt(-1);
        }
    }

    public static final Creator<Joke> CREATOR = new Creator<Joke>() {

        @Override
        public Joke createFromParcel(Parcel source) {
            return new Joke(source);
        }

        @Override
        public Joke[] newArray(int size) {
            return new Joke[size];
        }
    };
}
