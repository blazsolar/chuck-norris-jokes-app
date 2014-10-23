package com.github.blazsolar.chuck;

/**
 * Created by Blaz Solar on 21/10/14.
 */
public class Modules {

    static Object[] list(App app) {
        return new Object[] {
                new AppModule(app)
        };
    }

    private Modules() {
        // no instances
    }

}
