package com.celestialapps.leowallet.dagger.modules;

import android.content.Context;

import com.celestialapps.leowallet.help.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class RetrofitModule {

    private static final String BASE_URL = "http://176.99.9.248:8080"; //"http://192.168.0.105:8000";


    @Provides
    @Singleton
    public Retrofit provideRetrofitBuilder(Converter.Factory gsonConvectorFactory,
                                           CallAdapter.Factory callAdapterFactory,
                                           OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(callAdapterFactory)
                .addConverterFactory(gsonConvectorFactory)
                .build();
    }

    @Provides
    @Singleton
    public CallAdapter.Factory provideCallAdapterFactory() {
        return RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io());
    }

    @Provides
    @Singleton
    public Converter.Factory provideConverterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @Provides
    @Singleton
    @Named("HttpLoggingInterceptor")
    public Interceptor provideHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    @Singleton
    @Named("CacheControlInterceptor")
    public Interceptor provideCacheControlInterceptor(Context context) {
        return chain -> {
            Response originalResponse = chain.proceed(chain.request());
            if (Utils.isNetworkAvailable(context)) {
                int maxAge = 5; // read from cache for 5 seconds
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
        };
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(@Named("HttpLoggingInterceptor") Interceptor httpLoggingInterceptor,
                                            @Named("CacheControlInterceptor") Interceptor cacheControlInterceptor,
                                            Cache cache) {
        return new OkHttpClient
                .Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addNetworkInterceptor(cacheControlInterceptor)
                .cache(cache)
                .build();
    }

    @Provides
    @Singleton
    public Cache provideCache(File file) {
        return new Cache(file, 10 * 1024 * 1024); // 10 MiB
    }

    @Provides
    @Singleton
    public File provideHttpCacheDirectory(Context context) {
        return new File(context.getCacheDir(), "responses");
    }

    @Provides
    @Singleton
    public Gson provideGson() {
        return new GsonBuilder()
                .setLenient()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .serializeNulls()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }

}
