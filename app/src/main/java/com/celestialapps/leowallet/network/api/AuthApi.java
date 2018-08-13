package com.celestialapps.leowallet.network.api;

import com.celestialapps.leowallet.network.model.request.SignInRequest;
import com.celestialapps.leowallet.network.model.response.AuthToken;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.PUT;

public interface AuthApi {

    @PUT("/api/auth/login")
    Observable<Response<AuthToken>> login(@Body SignInRequest signInRequest);
}
