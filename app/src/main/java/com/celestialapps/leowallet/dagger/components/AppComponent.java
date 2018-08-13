package com.celestialapps.leowallet.dagger.components;




import com.celestialapps.leowallet.dagger.modules.ApiModule;
import com.celestialapps.leowallet.dagger.modules.ContextModule;
import com.celestialapps.leowallet.dagger.modules.RetrofitModule;
import com.celestialapps.leowallet.presentation.presenter.BonusPresenter;
import com.celestialapps.leowallet.presentation.presenter.HistoryPresenter;
import com.celestialapps.leowallet.presentation.presenter.LeoWalletPresenter;
import com.celestialapps.leowallet.presentation.presenter.LoginPresenter;
import com.celestialapps.leowallet.presentation.presenter.ReceiveMoneyPresenter;
import com.celestialapps.leowallet.presentation.presenter.SendMoneyPresenter;
import com.celestialapps.leowallet.presentation.presenter.UpBalancePresenter;

import javax.inject.Singleton;

import dagger.Component;


@Component(modules = {ContextModule.class, ApiModule.class, RetrofitModule.class})
@Singleton
public interface AppComponent {

    void inject(LeoWalletPresenter leoWalletPresenter);

    void inject(LoginPresenter loginPresenter);

    void inject(BonusPresenter bonusPresenter);

    void inject(SendMoneyPresenter sendMoneyPresenter);

    void inject(UpBalancePresenter upBalancePresenter);

    void inject(ReceiveMoneyPresenter receiveMoneyPresenter);

    void inject(HistoryPresenter historyPresenter);
}
