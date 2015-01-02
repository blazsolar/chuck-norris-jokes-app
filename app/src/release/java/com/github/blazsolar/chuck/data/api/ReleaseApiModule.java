package com.github.blazsolar.chuck.data.api;

import com.github.blazsolar.chuck.data.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.Endpoint;
import retrofit.Endpoints;
import retrofit.RestAdapter;

/**
 * Created by Blaz Solar on 02/01/15.
 */
@Module
public class ReleaseApiModule {

    @Provides @Singleton Endpoint provideEndpoint() {
        return Endpoints.newFixedEndpoint(Constants.API_ENDPOINT);
    }

    @Provides @Singleton
    JokesService provideJokesService(RestAdapter adapter) {
        return adapter.create(JokesService.class);
    }

}
