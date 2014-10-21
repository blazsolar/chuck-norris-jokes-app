package com.github.blazsolar.chuck.integration.data.api.model;

import android.os.Parcel;

import junit.framework.TestCase;

public class JokeTest extends TestCase {

    Joke mJoke;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        mJoke = new Joke(10, "Joke", new String[]{"category1", "category2"});
    }

    public void testDescribeContents() throws Exception {
        assertEquals(0, mJoke.describeContents());
    }

    public void testWriteToParcel() throws Exception {

        Parcel parcel = Parcel.obtain();
        mJoke.writeToParcel(parcel, 0);

        parcel.setDataPosition(0);

        assertEquals(10, parcel.readLong());
        assertEquals("Joke", parcel.readString());

        assertEquals(2, parcel.readInt());

        String[] categories = new String[2];
        parcel.readStringArray(categories);

        for (int i = 0; i < categories.length; i++) {
            assertEquals(mJoke.getCategories()[i], categories[i]);
        }

    }

    public void testWriteToParcelNoCategories() throws Exception {

        Joke joke = new Joke(10, "Joke", null);

        Parcel parcel = Parcel.obtain();
        joke.writeToParcel(parcel, 0);

        parcel.setDataPosition(0);

        assertEquals(10, parcel.readLong());
        assertEquals("Joke", parcel.readString());

        assertEquals(-1, parcel.readInt());

    }

    public void testCreator() throws Exception {

        Parcel parcel = Parcel.obtain();
        mJoke.writeToParcel(parcel, 0);

        parcel.setDataPosition(0);

        Joke joke = Joke.CREATOR.createFromParcel(parcel);

        assertEquals(10, joke.getId());
        assertEquals("Joke", joke.getJoke());

        for (int i = 0; i < joke.getCategories().length; i++) {
            assertEquals(mJoke.getCategories()[i], joke.getCategories()[i]);
        }

    }

    public void testCreatorNoCategories() throws Exception {

        Joke joke = new Joke(10, "Joke", null);

        Parcel parcel = Parcel.obtain();
        joke.writeToParcel(parcel, 0);

        parcel.setDataPosition(0);

        Joke parceJoke = Joke.CREATOR.createFromParcel(parcel);

        assertEquals(10, parceJoke.getId());
        assertEquals("Joke", parceJoke.getJoke());
        assertNull(parceJoke.getCategories());

    }

    public void testCreatorArray() throws Exception {

        Joke[] jokes = Joke.CREATOR.newArray(3);
        assertEquals(3, jokes.length);

    }
}