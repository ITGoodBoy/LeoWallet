package com.celestialapps.leowallet.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.celestialapps.leowallet.R;
import com.celestialapps.leowallet.network.model.request.PromotionalCodeRequest;
import com.celestialapps.leowallet.network.model.response.Balance;
import com.celestialapps.leowallet.network.model.response.Wallet;
import com.celestialapps.leowallet.presentation.presenter.BonusPresenter;
import com.celestialapps.leowallet.presentation.presenter.LeoWalletPresenter;
import com.celestialapps.leowallet.presentation.view.BonusView;
import com.celestialapps.leowallet.presentation.view.LeoWalletView;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BonusActivity extends BottomBarActivity
        implements LeoWalletView, BonusView {

    @InjectPresenter
    LeoWalletPresenter mLeoWalletPresenter;
    @InjectPresenter
    BonusPresenter mBonusPresenter;

    @BindView(R.id.ac_tv_bill)
    AppCompatTextView mAcTvBill;
    @BindView(R.id.tiet_promo_code)
    TextInputEditText mTietPromoCode;
    @BindView(R.id.ac_btn_get_bonus)
    AppCompatButton mAcBtnGetBonus;

    @BindString(R.string.current_balance)
    String mCurrentBalance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bonus);
        ButterKnife.bind(this);

        mAcBtnGetBonus.setOnClickListener(v -> mBonusPresenter.getBonus(getAuthToken(),
                new PromotionalCodeRequest(mTietPromoCode.getText().toString()))
        );

        mLeoWalletPresenter.getMyWallet(getAuthToken());
    }

    @Override
    public int getActiveButtonId() {
        return R.id.llc_bonus;
    }

    @Override
    public void gettingBonusStarted() {

    }

    @Override
    public void gettingBonusSuccess(Balance balance) {
        mAcTvBill.setText(mCurrentBalance + balance.getBalance());
    }

    @Override
    public void gettingBonusFailed(String message) {

    }

    @Override
    public void gettingWalletStarted() {

    }

    @Override
    public void gettingWalletSuccess(Wallet wallet) {
        mAcTvBill.setText(mCurrentBalance + wallet.getBalance());
    }

    @Override
    public void gettingWalletFailed(String message) {

    }
}
