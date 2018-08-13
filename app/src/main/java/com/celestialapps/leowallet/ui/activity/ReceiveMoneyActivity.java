package com.celestialapps.leowallet.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.celestialapps.leowallet.R;
import com.celestialapps.leowallet.network.model.request.ReceiveMoneyRequest;
import com.celestialapps.leowallet.network.model.response.Balance;
import com.celestialapps.leowallet.network.model.response.Wallet;
import com.celestialapps.leowallet.presentation.presenter.LeoWalletPresenter;
import com.celestialapps.leowallet.presentation.presenter.ReceiveMoneyPresenter;
import com.celestialapps.leowallet.presentation.view.LeoWalletView;
import com.celestialapps.leowallet.presentation.view.ReceiveMoneyView;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ReceiveMoneyActivity extends BaseActivity
        implements ReceiveMoneyView, LeoWalletView {


    @InjectPresenter
    LeoWalletPresenter mLeoWalletPresenter;
    @InjectPresenter
    ReceiveMoneyPresenter mReceiveMoneyPresenter;

    @BindView(R.id.ac_tv_name)
    AppCompatTextView mAcTvName;
    @BindView(R.id.ac_tv_my_wallet_account)
    AppCompatTextView mAcTvMyWalletAccount;
    @BindView(R.id.ac_tv_balance)
    AppCompatTextView mAcTvBalance;
    @BindView(R.id.tiet_amount)
    TextInputEditText mTietAmount;
    @BindView(R.id.ac_btn_receive)
    AppCompatButton mAcBtnReceive;

    @BindString(R.string.current_balance)
    String mCurrentBalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_money);
        ButterKnife.bind(this);

        mLeoWalletPresenter.getMyWallet(getAuthToken());

        mAcBtnReceive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mReceiveMoneyPresenter.receiveMoney(getAuthToken(), new ReceiveMoneyRequest(Long.valueOf(mTietAmount.getText().toString())));
            }
        });
    }

    @Override
    public void receiveMoneyStarted() {

    }

    @Override
    public void receiveMoneySuccess(Balance balance) {
        mAcTvBalance.setText(mCurrentBalance + balance.getBalance());
    }

    @Override
    public void receiveMoneyFailed(String message) {
        showSnackBarMessage(message);
    }

    @Override
    public void gettingWalletStarted() {

    }

    @Override
    public void gettingWalletSuccess(Wallet wallet) {
        mAcTvBalance.setText(mCurrentBalance + wallet.getBalance());
    }

    @Override
    public void gettingWalletFailed(String message) {
        showSnackBarMessage(message);
    }
}
