package com.github.blazsolar.chuck.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.github.blazsolar.chuck.data.api.ApiModule;
import com.github.blazsolar.chuck.data.api.ReleaseApiModule;
import com.squareup.okhttp.OkHttpClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Blaz Solar on 23/08/14.
 */
@Module(includes = {ApiModule.class, ReleaseApiModule.class})
public class ReleaseDataModule {

    private static final String TAG = "DataModule";

    static final int DISK_CACHE_SIZE = 50 * 1024 * 1024; // 50MB

    @Provides @Singleton
    OkHttpClient provideHttpClient(Context context) {
        return createOkHttpClient(context);
    }

    @Provides @Singleton SharedPreferences provideSharedPreferences(Context context) {
        return context.getSharedPreferences("chuckNorris", Context.MODE_PRIVATE);
    }

    static OkHttpClient createOkHttpClient(Context context) {
        OkHttpClient client = new OkHttpClient();

        // TODO add http cache

        return client;
    }

}
