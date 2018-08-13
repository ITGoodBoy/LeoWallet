package com.celestialapps.leowallet.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.celestialapps.leowallet.App;
import com.celestialapps.leowallet.help.Utils;
import com.celestialapps.leowallet.network.api.WalletApi;
import com.celestialapps.leowallet.presentation.view.HistoryView;


import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

@InjectViewState
public class HistoryPresenter extends BasePresenter<HistoryView> {


    @Inject
    WalletApi mWalletApi;

    public HistoryPresenter() {
        App.getAppComponent().inject(this);
    }

    public void getTransactionHistory(String authToken) {
        Disposable disposable = mWalletApi
                .getMyTransactions(authToken)
                .compose(Utils.applySchedulers())
                .subscribe(listResponse -> {
                    if (listResponse.isSuccessful()) {
                         getViewState().gettingHistorySuccess(listResponse.body());
                    } else {
                        getViewState().gettingHistoryFailed(listResponse.message());
                    }
                }, Throwable::printStackTrace);

        disposeOnDestroy(disposable);
    }


}
