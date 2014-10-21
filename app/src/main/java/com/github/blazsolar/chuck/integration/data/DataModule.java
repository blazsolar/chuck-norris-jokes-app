package com.github.blazsolar.chuck.integration.data;

import com.squareup.okhttp.OkHttpClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import com.github.blazsolar.chuck.integration.data.api.ApiModule;
import com.github.blazsolar.chuck.integration.data.api.JokesService;

/**
 * Created by Blaz Solar on 23/08/14.
 */
@Module(
        complete = false,
        library = true,
        includes = ApiModule.class
)
public class DataModule {

    @Provides @Singleton
    OkHttpClient provideHttpClient() {
        return new OkHttpClient();
    }

    @Provides @Singleton
    JokesDatabase prodiveJokesDatabase(JokesService jokesService) {
        return new JokesDatabase(jokesService);
    }

}
