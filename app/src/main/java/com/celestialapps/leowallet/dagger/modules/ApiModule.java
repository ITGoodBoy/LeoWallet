package com.celestialapps.leowallet.dagger.modules;



import com.celestialapps.leowallet.network.api.AuthApi;
import com.celestialapps.leowallet.network.api.WalletApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by Sergey on 11.12.2017.
 */

@Module(includes = RetrofitModule.class)
public class ApiModule {

    @Provides
    @Singleton
    public AuthApi provideAuthApi(Retrofit retrofit) {
        return retrofit.create(AuthApi.class);
    }

    @Provides
    @Singleton
    public WalletApi provideProfileApi(Retrofit retrofit) {
        return retrofit.create(WalletApi.class);
    }


}
