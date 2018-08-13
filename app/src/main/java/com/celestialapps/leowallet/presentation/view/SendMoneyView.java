package com.celestialapps.leowallet.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.celestialapps.leowallet.network.model.response.Balance;

public interface SendMoneyView extends MvpView {

    void sendMoneyStarted();
    void sendMoneySuccess(Balance balance);
    void sendMoneyFailed(String message);
}
