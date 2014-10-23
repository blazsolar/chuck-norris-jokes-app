package com.github.blazsolar.chuck.data.api;

import com.github.blazsolar.chuck.data.Constants;

/**
 * Created by Blaz Solar on 22/10/14.
 */
public enum ApiEndpoints {

    PRODUCTION("Production", Constants.API_ENDPOINT),
    MOCK_MODE("Mock Mode", "mock://"),
    CUSTOM("Custom", null);

    public final String name;
    public final String url;

    ApiEndpoints(String name, String url) {
        this.name = name;
        this.url = url;
    }

    @Override
    public String toString() {
        return name;
    }

    public static ApiEndpoints from(String endpoint) {
        for (ApiEndpoints value : values()) {
            if (value.url != null && value.url.equals(endpoint)) {
                return value;
            }
        }
        return CUSTOM;
    }

    public static boolean isMockMode(String endpoint) {
        return from(endpoint) == MOCK_MODE;
    }
}
