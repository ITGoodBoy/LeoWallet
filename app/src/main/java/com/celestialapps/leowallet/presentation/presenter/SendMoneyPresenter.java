package com.celestialapps.leowallet.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.celestialapps.leowallet.App;
import com.celestialapps.leowallet.help.Utils;
import com.celestialapps.leowallet.network.api.WalletApi;
import com.celestialapps.leowallet.network.model.request.SendMoneyRequest;
import com.celestialapps.leowallet.presentation.view.SendMoneyView;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

@InjectViewState
public class SendMoneyPresenter extends BasePresenter<SendMoneyView> {

    @Inject
    WalletApi mWalletApi;

    public SendMoneyPresenter() {
        App.getAppComponent().inject(this);
    }

    public void sendMoney(String authToken, SendMoneyRequest sendMoneyRequest) {
        getViewState().sendMoneyStarted();

        Disposable disposable = mWalletApi
                .sendMoney(authToken, sendMoneyRequest)
                .compose(Utils.applySchedulers())
                .subscribe(balanceResponse -> {
                    if (balanceResponse.isSuccessful()) {
                        getViewState().sendMoneySuccess(balanceResponse.body());
                    }  else {
                        getViewState().sendMoneyFailed(balanceResponse.message());
                    }
                });

        disposeOnDestroy(disposable);
    }

}
