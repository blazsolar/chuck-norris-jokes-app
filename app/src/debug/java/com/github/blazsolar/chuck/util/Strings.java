package com.github.blazsolar.chuck.util;

/**
 * Created by Blaz Solar on 27/10/14.
 */
public class Strings {

    public static String truncateAt(String string, int length) {
        return string.length() > length ? string.substring(0, length) : string;
    }

    private Strings() {
        // no instances
    }

}
