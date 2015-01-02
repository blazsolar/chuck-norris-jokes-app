package com.github.blazsolar.chuck.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.github.blazsolar.chuck.data.api.ApiEndpoints;
import com.github.blazsolar.chuck.data.api.DebugApiModule;
import com.github.blazsolar.chuck.data.prefs.StringPreference;
import com.squareup.okhttp.OkHttpClient;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Blaz Solar on 22/10/14.
 */
@Module(
        includes = {
                DataModule.class,
                DebugApiModule.class
        }
)
public class DebugDataModule {

    @Provides OkHttpClient provideOkHttpClient(Context context) {
        OkHttpClient client = DataModule.createOkHttpClient(context);
        client.setSslSocketFactory(createBadSslSocketFactory());
        return client;
    }

    @Provides @ApiEndpoint
    StringPreference provideEndpointPreference(SharedPreferences preferences) {
        return new StringPreference(preferences, "debug_endpoint", ApiEndpoints.MOCK_MODE.url);
    }

    @Provides @IsMockMode boolean provideIsMockMode(@ApiEndpoint StringPreference preferences) {
        return ApiEndpoints.isMockMode(preferences.get());
    }

    private static SSLSocketFactory createBadSslSocketFactory() {
        try {
            // Construct SSLSocketFactory that accepts any cert.
            SSLContext context = SSLContext.getInstance("TLS");
            TrustManager permissive = new X509TrustManager() {
                @Override public void checkClientTrusted(X509Certificate[] chain, String authType)
                        throws CertificateException {
                }

                @Override public void checkServerTrusted(X509Certificate[] chain, String authType)
                        throws CertificateException {
                }

                @Override public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };
            context.init(null, new TrustManager[] { permissive }, null);
            return context.getSocketFactory();
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }

}
