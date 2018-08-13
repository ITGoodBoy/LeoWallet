package com.celestialapps.leowallet.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.celestialapps.leowallet.network.model.response.Wallet;

public interface LeoWalletView extends MvpView {

    void gettingWalletStarted();
    void gettingWalletSuccess(Wallet wallet);
    void gettingWalletFailed(String message);
}
