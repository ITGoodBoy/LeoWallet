package com.celestialapps.leowallet.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.RelativeLayout;

import com.celestialapps.leowallet.R;
import com.celestialapps.leowallet.network.model.response.Wallet;
import com.celestialapps.leowallet.presentation.view.LeoWalletView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileActivity extends BottomBarActivity implements LeoWalletView{

    @BindView(R.id.ac_imv_avatar)
    AppCompatImageView mAcImvAvatar;
    @BindView(R.id.cardView)
    CardView mCardView;
    @BindView(R.id.rl_avatar)
    RelativeLayout mRlAvatar;
    @BindView(R.id.ac_tv_name)
    AppCompatTextView mAcTvName;
    @BindView(R.id.ac_btn_exit)
    AppCompatButton mAcBtnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        mAcBtnExit.setOnClickListener(v -> {
            setAuthToken("");
            startActivity(LoginActivity.class);
            finish();
        });
    }

    @Override
    public int getActiveButtonId() {
        return R.id.llc_profile;
    }

    @Override
    public void gettingWalletStarted() {

    }

    @Override
    public void gettingWalletSuccess(Wallet wallet) {
        mAcTvName.setText(wallet.getAccount());
    }

    @Override
    public void gettingWalletFailed(String message) {

    }
}
