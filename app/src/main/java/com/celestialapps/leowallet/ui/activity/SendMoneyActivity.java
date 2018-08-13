package com.celestialapps.leowallet.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.celestialapps.leowallet.R;
import com.celestialapps.leowallet.network.model.request.SendMoneyRequest;
import com.celestialapps.leowallet.network.model.response.Balance;
import com.celestialapps.leowallet.network.model.response.Wallet;
import com.celestialapps.leowallet.presentation.presenter.LeoWalletPresenter;
import com.celestialapps.leowallet.presentation.presenter.SendMoneyPresenter;
import com.celestialapps.leowallet.presentation.view.LeoWalletView;
import com.celestialapps.leowallet.presentation.view.SendMoneyView;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SendMoneyActivity extends BaseActivity
        implements SendMoneyView, LeoWalletView {

    @InjectPresenter
    SendMoneyPresenter mSendMoneyPresenter;
    @InjectPresenter
    LeoWalletPresenter mLeoWalletPresenter;

    @BindView(R.id.tiet_recipient)
    TextInputEditText mTietRecipient;
    @BindView(R.id.tiet_amount)
    TextInputEditText mTietAmount;
    @BindView(R.id.ac_tv_send_money)
    AppCompatButton mAcTvSendMoney;
    @BindView(R.id.ac_tv_balance)
    AppCompatTextView mAcTvBalance;

    @BindString(R.string.current_balance)
    String mCurrentBalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_money);
        ButterKnife.bind(this);

        mLeoWalletPresenter.getMyWallet(getAuthToken());

        mAcTvSendMoney.setOnClickListener(v -> mSendMoneyPresenter.sendMoney(getAuthToken(),
                new SendMoneyRequest(mTietRecipient.getText().toString(), Long.valueOf(mTietAmount.getText().toString()))));
    }

    @Override
    public void sendMoneyStarted() {

    }

    @Override
    public void sendMoneySuccess(Balance balance) {
        mAcTvBalance.setText(mCurrentBalance + balance);
    }

    @Override
    public void sendMoneyFailed(String message) {
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
