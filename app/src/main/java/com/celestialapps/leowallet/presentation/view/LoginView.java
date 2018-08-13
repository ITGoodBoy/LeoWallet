package com.celestialapps.leowallet.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.celestialapps.leowallet.network.model.response.AuthToken;

public interface LoginView extends MvpView{

    void loginStarted();
    void successLogin(AuthToken authToken);
    void failedLogin(String message);
}
