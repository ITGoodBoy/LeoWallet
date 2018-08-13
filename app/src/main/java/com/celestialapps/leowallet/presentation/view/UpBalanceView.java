package com.celestialapps.leowallet.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.celestialapps.leowallet.network.model.response.Balance;

public interface UpBalanceView extends MvpView {

    void upBalanceStarted();
    void upBalanceSuccess(Balance balance);
    void upBalanceFailed(String message);
}
