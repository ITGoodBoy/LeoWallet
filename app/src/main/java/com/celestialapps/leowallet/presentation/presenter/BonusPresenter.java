package com.celestialapps.leowallet.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.celestialapps.leowallet.App;
import com.celestialapps.leowallet.help.Utils;
import com.celestialapps.leowallet.network.api.WalletApi;
import com.celestialapps.leowallet.network.model.request.PromotionalCodeRequest;
import com.celestialapps.leowallet.presentation.view.BonusView;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;


@InjectViewState
public class BonusPresenter extends BasePresenter<BonusView> {

    @Inject
    WalletApi mWalletApi;

    public BonusPresenter() {
        App.getAppComponent().inject(this);
    }

    public void getBonus(String authToken, PromotionalCodeRequest promotionalCodeRequest) {
        getViewState().gettingBonusStarted();

        Disposable disposable = mWalletApi
                .enterPromotionalCode(authToken, promotionalCodeRequest)
                .compose(Utils.applySchedulers())
                .subscribe(balanceResponse -> {
                    if (balanceResponse.isSuccessful()) {
                        getViewState().gettingBonusSuccess(balanceResponse.body());
                    }  else {
                        getViewState().gettingBonusFailed(balanceResponse.message());
                    }
                });

        disposeOnDestroy(disposable);
    }

}
