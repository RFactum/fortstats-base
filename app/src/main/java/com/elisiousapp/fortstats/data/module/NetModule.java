package com.elisiousapp.fortstats.data.module;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.elisiousapp.fortstats.bean.Player;
import com.elisiousapp.fortstats.utils.ConfigUtils;
import com.elisiousapp.fortstats.utils.MyDeserializer;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Rafael Factum on 22/05/2018.
 */

@Module
public class NetModule {

    String baseURL;

    public NetModule(String baseURL) {
        this.baseURL = baseURL;
    }

    @Singleton
    @Provides
    SharedPreferences providesSharedPreferences(Application application){
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Singleton
    @Provides
    Cache provideHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }

    @Singleton
    @Provides
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder()
                .registerTypeAdapter(Player.class, new MyDeserializer());
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Singleton
    @Provides
    OkHttpClient provideOkhttpClient(Cache cache) {
        OkHttpClient.Builder client = new OkHttpClient.Builder()
                                        .addInterceptor(new Interceptor() {
                                            @Override
                                            public Response intercept(Chain chain) throws IOException {
                                                Request request = chain.request().newBuilder()
                                                        // ADD THE APPLICATION_ID TO THE CONFIGUTILS:
                                                        .addHeader("TRN-Api-Key", ConfigUtils.APPLICATION_ID)
                                                        .build();
                                                return chain.proceed(request);
                                            }
                                        });
        client.cache(cache);
        return client.build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(baseURL)
                .client(okHttpClient)
                .build();
        return retrofit;
    }


}
