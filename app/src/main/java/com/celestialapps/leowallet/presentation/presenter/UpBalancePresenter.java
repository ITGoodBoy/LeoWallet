package com.celestialapps.leowallet.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.celestialapps.leowallet.App;
import com.celestialapps.leowallet.network.api.WalletApi;
import com.celestialapps.leowallet.network.model.request.UpBalanceRequest;
import com.celestialapps.leowallet.presentation.view.UpBalanceView;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

@InjectViewState
public class UpBalancePresenter extends BasePresenter<UpBalanceView> {

    @Inject
    WalletApi mWalletApi;

    public UpBalancePresenter() {
        App.getAppComponent().inject(this);
    }

    public void upBalance(String authToken, UpBalanceRequest upBalanceRequest) {
        getViewState().upBalanceStarted();

        Disposable disposable = mWalletApi
                .upBalance(authToken, upBalanceRequest)
                .subscribe(balanceResponse -> {
                    if (balanceResponse.isSuccessful()) {
                        getViewState().upBalanceSuccess(balanceResponse.body());
                    } else {
                        getViewState().upBalanceFailed(balanceResponse.message());
                    }
                }, Throwable::printStackTrace);

        disposeOnDestroy(disposable);
    }
}
