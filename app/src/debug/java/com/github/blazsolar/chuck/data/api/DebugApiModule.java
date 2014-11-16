package com.github.blazsolar.chuck.data.api;

import android.content.SharedPreferences;

import com.github.blazsolar.chuck.data.ApiEndpoint;
import com.github.blazsolar.chuck.data.IsMockMode;
import com.github.blazsolar.chuck.data.prefs.StringPreference;

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

    @Provides Endpoint provideEndpoint(@ApiEndpoint StringPreference apiEndpoint) {
        return Endpoints.newFixedEndpoint(apiEndpoint.get());
    }

    @Provides
    MockRestAdapter provideMockRestAdapter(RestAdapter restAdapter, SharedPreferences preferences) {
        MockRestAdapter mockRestAdapter = MockRestAdapter.from(restAdapter);
        AndroidMockValuePersistence.install(mockRestAdapter, preferences);
        return mockRestAdapter;
    }

    @Provides
    JokesService provideJokesService(RestAdapter restAdapter, MockRestAdapter mockRestAdapter,
         @IsMockMode boolean isMockMode, MockJokesService mockJokesService) {
        if (isMockMode) {
            return mockRestAdapter.create(JokesService.class, mockJokesService);
        } else {
            return restAdapter.create(JokesService.class);
        }
    }

}
