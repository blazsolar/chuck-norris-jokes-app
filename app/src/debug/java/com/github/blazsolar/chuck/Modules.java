package com.github.blazsolar.chuck;

/**
 * Created by Blaz Solar on 21/10/14.
 */
final class Modules {

    public static final Class[] LIST = {
            AppModule.class,
            DebugModule.class
    };

    static Object[] list(App app) {
        return new Object[] {
                new AppModule(app),
                new DebugModule()
        };
    }

    private Modules() {
        // no instances
    }

}
