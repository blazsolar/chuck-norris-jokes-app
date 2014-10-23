package com.github.blazsolar.chuck.data.api.model;

/**
 * Created by Blaz Solar on 23/08/14.
 */
public class Response<T> {

    private String type;
    private T value;

    public Response() {
    }

    public Response(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
