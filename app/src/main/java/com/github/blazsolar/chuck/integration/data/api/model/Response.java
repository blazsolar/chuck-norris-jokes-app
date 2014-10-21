package com.github.blazsolar.chuck.integration.data.api.model;

/**
 * Created by Blaz Solar on 23/08/14.
 */
public class Response<T> {

    private String type;
    private T value;

    public Response() {
    }

    public T getValue() {
        return value;
    }
}
