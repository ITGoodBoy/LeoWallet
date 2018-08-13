package com.celestialapps.leowallet.ui.activity;

import android.annotation.SuppressLint;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.celestialapps.leowallet.R;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressLint("Registered")
public abstract class BottomBarActivity extends BaseActivity
        implements View.OnClickListener {

    @BindView(R.id.llc_wallet)
    View llcWallet;
    @BindView(R.id.llc_bonus)
    View llcBonus;
    @BindView(R.id.llc_history)
    View llcHistory;
    @BindView(R.id.llc_profile)
    View llcProfile;

    @BindView(R.id.ac_imv_wallet)
    AppCompatImageView acImvWallet;
    @BindView(R.id.ac_imv_bonus)
    AppCompatImageView acImvBonus;
    @BindView(R.id.ac_imv_history)
    AppCompatImageView acImvHistory;
    @BindView(R.id.ac_imv_profile)
    AppCompatImageView acImvProfile;

    @BindView(R.id.ac_tv_wallet)
    AppCompatTextView acTvWallet;
    @BindView(R.id.ac_tv_bonus)
    AppCompatTextView acTvBonus;
    @BindView(R.id.ac_tv_history)
    AppCompatTextView acTvHistory;
    @BindView(R.id.ac_tv_profile)
    AppCompatTextView acTvProfile;

    @BindColor(R.color.colorWhite)
    int colorWhite;

    boolean isCreated;

    @Override
    protected void onStart() {
        super.onStart();
        if (!isCreated) {
            ButterKnife.bind(this);
            setupViews();
            isCreated = true;
        }
    }

    private void setupViews() {
        llcWallet.setOnClickListener(this);
        llcBonus.setOnClickListener(this);
        llcHistory.setOnClickListener(this);
        llcProfile.setOnClickListener(this);

        switch (getActiveButtonId()) {
            case R.id.llc_wallet:
                setColorActiveButton(acImvWallet, acTvWallet, colorWhite);
                break;
            case R.id.llc_bonus:
                setColorActiveButton(acImvBonus, acTvBonus, colorWhite);
                break;
            case R.id.llc_history:
                setColorActiveButton(acImvHistory, acTvHistory, colorWhite);
                break;
            case R.id.llc_profile:
                setColorActiveButton(acImvProfile, acTvProfile, colorWhite);
                break;

        }
    }

    private void setColorActiveButton(AppCompatImageView imageView, AppCompatTextView textView, int color) {
        imageView.setColorFilter(color);
        textView.setTextColor(color);
    }

    @Override
    public void onBackPressed() {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setMessage(R.string.are_you_want_exit)
                .setTitle(R.string.exit)
                .setNegativeButton("Нет", (dialog, which) -> dialog.dismiss())
                .setPositiveButton("Да", (dialog, which) -> finishAffinity())
                .create();

        alertDialog.show();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id != getActiveButtonId()) {

            switch (id) {
                case R.id.llc_wallet:
                    startActivity(LeoWalletActivity.class);
                    break;

                case R.id.llc_bonus:
                    startActivity(BonusActivity.class);
                    break;

                case R.id.llc_history:
                    startActivity(HistoryActivity.class);
                    break;

                case R.id.llc_profile:
                    startActivity(ProfileActivity.class);
                    break;
            }
        }
    }

    public abstract int getActiveButtonId();
}
