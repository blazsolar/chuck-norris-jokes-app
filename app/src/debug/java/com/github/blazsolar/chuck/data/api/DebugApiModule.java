package com.github.blazsolar.chuck.data.api;

import android.content.SharedPreferences;

import com.github.blazsolar.chuck.data.ApiEndpoint;
import com.github.blazsolar.chuck.data.prefs.StringPreference;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.Endpoint;
import retrofit.Endpoints;
import retrofit.MockRestAdapter;
import retrofit.RestAdapter;
import retrofit.android.AndroidMockValuePersistence;

/**
 * Created by Blaz Solar on 22/10/14.
 */
@Module(
        complete = false,
        library = true,
        overrides = true
)
public class DebugApiModule {

    @Provides @Singleton
    Endpoint provideEndpoint(@ApiEndpoint StringPreference apiEndpoint) {
        return Endpoints.newFixedEndpoint(apiEndpoint.get());
    }

    @Provides @Singleton
    MockRestAdapter provideMockRestAdapter(RestAdapter restAdapter, SharedPreferences preferences) {
        MockRestAdapter mockRestAdapter = MockRestAdapter.from(restAdapter);
        AndroidMockValuePersistence.install(mockRestAdapter, preferences);
        return mockRestAdapter;
    }

}
