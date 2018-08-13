package com.celestialapps.leowallet.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.celestialapps.leowallet.App;
import com.celestialapps.leowallet.help.Utils;
import com.celestialapps.leowallet.network.api.WalletApi;
import com.celestialapps.leowallet.network.model.request.ReceiveMoneyRequest;
import com.celestialapps.leowallet.presentation.view.ReceiveMoneyView;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

@InjectViewState
public class ReceiveMoneyPresenter extends BasePresenter<ReceiveMoneyView> {

    @Inject
    WalletApi mWalletApi;

    public ReceiveMoneyPresenter() {
        App.getAppComponent().inject(this);
    }


    public void receiveMoney(String authToken, ReceiveMoneyRequest receiveMoneyRequest) {
        getViewState().receiveMoneyStarted();

        Disposable disposable = mWalletApi
                .receiveMoney(authToken, receiveMoneyRequest)
                .compose(Utils.applySchedulers())
                .subscribe(balanceResponse -> {
                    if (balanceResponse.isSuccessful()) {
                        getViewState().receiveMoneySuccess(balanceResponse.body());
                    } else {
                        getViewState().receiveMoneyFailed(balanceResponse.message());
                    }
                }, Throwable::printStackTrace);

        disposeOnDestroy(disposable);
    }

}
