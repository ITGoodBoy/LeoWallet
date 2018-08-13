package com.celestialapps.leowallet.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.celestialapps.leowallet.network.model.response.Balance;

public interface BonusView extends MvpView {

    void gettingBonusStarted();
    void gettingBonusSuccess(Balance balance);
    void gettingBonusFailed(String message);
}
