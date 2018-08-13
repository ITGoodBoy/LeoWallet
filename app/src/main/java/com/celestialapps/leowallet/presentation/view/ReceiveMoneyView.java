package com.celestialapps.leowallet.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.celestialapps.leowallet.network.model.response.Balance;

public interface ReceiveMoneyView extends MvpView {

    void receiveMoneyStarted();
    void receiveMoneySuccess(Balance balance);
    void receiveMoneyFailed(String message);
}
