package com.celestialapps.leowallet.presentation.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.celestialapps.leowallet.App;
import com.celestialapps.leowallet.help.Utils;
import com.celestialapps.leowallet.network.api.AuthApi;
import com.celestialapps.leowallet.network.model.request.SignInRequest;
import com.celestialapps.leowallet.presentation.view.LoginView;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

@InjectViewState
public class LoginPresenter extends BasePresenter<LoginView>{

    @Inject
    AuthApi mAuthApi;


    public LoginPresenter() {
        App.getAppComponent().inject(this);
    }


    public void login(SignInRequest signInRequest) {
        getViewState().loginStarted();

        Log.e("AHAHAHHA", signInRequest.getAccount() + " ---- " + signInRequest.getPassword());

        Disposable disposable = mAuthApi
                .login(signInRequest)
                .compose(Utils.applySchedulers())
                .subscribe(authTokenResponse -> {
                    if (authTokenResponse.isSuccessful()) {
                        getViewState().successLogin(authTokenResponse.body());
                    } else {
                        getViewState().failedLogin(authTokenResponse.message());
                    }
                }, Throwable::printStackTrace);

        disposeOnDestroy(disposable);
    }



}
