package com.celestialapps.leowallet.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.celestialapps.leowallet.App;
import com.celestialapps.leowallet.help.Utils;
import com.celestialapps.leowallet.network.api.WalletApi;
import com.celestialapps.leowallet.presentation.view.LeoWalletView;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

@InjectViewState
public class LeoWalletPresenter extends BasePresenter<LeoWalletView> {

    @Inject
    WalletApi mWalletApi;

    public LeoWalletPresenter() {
        App.getAppComponent().inject(this);
    }

    public void getMyWallet(String authToken) {
        getViewState().gettingWalletStarted();

        Disposable disposable = mWalletApi
                .getMyWallet(authToken)
                .compose(Utils.applySchedulers())
                .subscribe(walletResponse -> {
                    if (walletResponse.isSuccessful()) {
                        getViewState().gettingWalletSuccess(walletResponse.body());
                    } else {
                        getViewState().gettingWalletFailed(walletResponse.message());
                    }
                }, Throwable::printStackTrace);

        disposeOnDestroy(disposable);
    }

}
