package com.github.blazsolar.chuck.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.github.blazsolar.chuck.data.api.ApiModule;
import com.github.blazsolar.chuck.data.api.JokesService;
import com.squareup.okhttp.OkHttpClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Blaz Solar on 23/08/14.
 */
@Module(
        complete = false,
        library = true,
        includes = ApiModule.class
)
public class DataModule {

    private static final String TAG = "DataModule";

    static final int DISK_CACHE_SIZE = 50 * 1024 * 1024; // 50MB

    @Provides @Singleton SharedPreferences provideSharedPreferences(Context context) {
        return context.getSharedPreferences("chukNorris", Context.MODE_PRIVATE);
    }

    @Provides @Singleton
    OkHttpClient provideHttpClient(Context context) {
        return createOkHttpClient(context);
    }

    @Provides @Singleton
    JokesDatabase prodiveJokesDatabase(JokesService jokesService) {
        return new JokesDatabase(jokesService);
    }

    static OkHttpClient createOkHttpClient(Context context) {
        OkHttpClient client = new OkHttpClient();

        // TODO add http cache

        return client;
    }

}
