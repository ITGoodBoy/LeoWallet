package com.celestialapps.leowallet.ui.activity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.Html;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.celestialapps.leowallet.R;
import com.celestialapps.leowallet.network.model.response.Favorite;
import com.celestialapps.leowallet.network.model.response.Invoice;
import com.celestialapps.leowallet.network.model.response.Last;
import com.celestialapps.leowallet.network.model.response.Wallet;
import com.celestialapps.leowallet.presentation.presenter.LeoWalletPresenter;
import com.celestialapps.leowallet.presentation.view.LeoWalletView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LeoWalletActivity extends BottomBarActivity
        implements LeoWalletView {

    @InjectPresenter
    LeoWalletPresenter mLeoWalletPresenter;

    @BindView(R.id.ac_tv_my_wallet_account)
    AppCompatTextView mAcTvMyWalletAccount;
    @BindView(R.id.ac_tv_amount)
    AppCompatTextView mAcTvAmount;

    @BindView(R.id.ac_btn_up_balance)
    AppCompatButton mAcBtnUpBalance;
    @BindView(R.id.ac_btn_receive)
    AppCompatButton mAcBtnReceive;

    @BindView(R.id.cv_send_money)
    CardView mCvSendMoney;
    @BindView(R.id.cv_pay_services)
    CardView mCvPayServices;
    @BindView(R.id.cv_bill)
    CardView mCvBill;

    @BindView(R.id.llc_send_money)
    LinearLayoutCompat mLlcSendMoney;
    @BindView(R.id.llc_pay_services)
    LinearLayoutCompat mLlcPayServices;
    @BindView(R.id.llc_bill)
    LinearLayoutCompat mLlcBill;

    @BindView(R.id.llc_invoices)
    LinearLayoutCompat mLlcInvoices;
    @BindView(R.id.llc_favorites)
    LinearLayoutCompat mLlcFavorites;
    @BindView(R.id.llc_lasts)
    LinearLayout mLlcLasts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leo_wallet);
        ButterKnife.bind(this);

        mLeoWalletPresenter.getMyWallet(getAuthToken());

        mAcBtnUpBalance.setOnClickListener(v -> startActivity(UpBalanceActivity.class));

        mAcBtnReceive.setOnClickListener(v -> startActivity(ReceiveMoneyActivity.class));

        mLlcSendMoney.setOnClickListener(v -> startActivity(SendMoneyActivity.class));

        mLlcPayServices.setOnClickListener(v -> {
            AlertDialog alertDialog = new AlertDialog.Builder(LeoWalletActivity.this)
                    .setTitle(R.string.pay_services)
                    .setMessage(R.string.paying_for_services)
                    .create();

            alertDialog.show();
        });

        mLlcBill.setOnClickListener(v -> {
            AlertDialog alertDialog = new AlertDialog.Builder(LeoWalletActivity.this)
                    .setTitle(R.string.aamount)
                    .setMessage(R.string.show_amount)
                    .create();

            alertDialog.show();
        });
    }

    @Override
    public int getActiveButtonId() {
        return R.id.llc_wallet;
    }


    @Override
    public void gettingWalletStarted() {

    }

    @Override
    public void gettingWalletSuccess(Wallet wallet) {
        mAcTvMyWalletAccount.setText(Html.fromHtml("LeoWallet <b>" + wallet.getAccount() + "</b>"));

        String amount = "₴" + wallet.getBalance() + "";

        String firstHalf = amount.substring(0, amount.length() - 2);
        String twoHalf = amount.substring(amount.length() - 2, amount.length());

        amount = firstHalf + "." + twoHalf;

        SpannableString spannableString = new SpannableString(amount);
        spannableString.setSpan(new RelativeSizeSpan(2f), 0, amount.length() - 3, 0); // set size

        mAcTvAmount.setText(spannableString);


        mLlcLasts.removeAllViews();
        mLlcFavorites.removeAllViews();
        mLlcInvoices.removeAllViews();

        addInvoiceViews(wallet);
        addFavoriteViews(wallet);
        addLastViews(wallet);
    }

    @Override
    public void gettingWalletFailed(String message) {
        showSnackBarMessage(message);
    }

    private void addLastViews(Wallet wallet) {
        if (wallet.getLasts().size() == 0) return;

        View view = getLayoutInflater().inflate(R.layout.item_lasts_divider, mLlcLasts, false);
        mLlcLasts.addView(view);

        for (int i = 0; i < wallet.getLasts().size(); i++) {
            Last last = wallet.getLasts().get(i);

            if (i == wallet.getLasts().size() - 1) {
                view = createContactViewProgrammatically(last.getService(), last.getName(),
                        last.getAccountWallet(), false, false);
            } else {
                view = createContactViewProgrammatically(last.getService(), last.getName(),
                        last.getAccountWallet(), false, true);
            }

            mLlcLasts.addView(view);
        }
    }

    private void addFavoriteViews(Wallet wallet) {
        if (wallet.getFavorites().size() == 0) return;

        View view = getLayoutInflater().inflate(R.layout.item_favorite_divider, mLlcFavorites, false);
        mLlcFavorites.addView(view);

        for (int i = 0; i < wallet.getFavorites().size(); i++) {
            Favorite favorite = wallet.getFavorites().get(i);

            if (i == wallet.getFavorites().size() - 1) {
                view = createContactViewProgrammatically(favorite.getServiceId(), favorite.getName(),
                        favorite.getAccountWallet(), true, false);
            } else {
                view = createContactViewProgrammatically(favorite.getServiceId(), favorite.getName(),
                        favorite.getAccountWallet(), true, true);
            }

            mLlcFavorites.addView(view);
        }
    }

    private void addInvoiceViews(Wallet wallet) {
        if (wallet.getInvoices().size() == 0) return;

        for (int i = 0; i < wallet.getInvoices().size(); i++) {
            Invoice invoice = wallet.getInvoices().get(i);

            View view;

            if (i == wallet.getInvoices().size() - 1) {
                view = createContactViewProgrammatically(invoice.getId(), invoice.getName(),
                        invoice.getAccountWallet(), false, false);
            } else {
                view = createContactViewProgrammatically(invoice.getId(), invoice.getName(),
                        invoice.getAccountWallet(), false, true);
            }

            mLlcInvoices.addView(view);
        }
    }

    private View createContactViewProgrammatically(long serviceId, String name, String accountWallet,
                                                   boolean isFavorite, boolean isAddDivider) {

        View view = getLayoutInflater().inflate(R.layout.item_contact, null, false);

        AppCompatImageView acImvAvatar = view.findViewById(R.id.ac_imv_avatar);
        AppCompatTextView acTvName = view.findViewById(R.id.ac_tv_name);
        AppCompatTextView acTvNumber = view.findViewById(R.id.ac_tv_number);
        AppCompatCheckBox acChbFavorite = view.findViewById(R.id.ac_chb_favorite);
        View divider = view.findViewById(R.id.divider);

        if (!isAddDivider) {
            divider.setVisibility(View.GONE);
        }

        if (name.isEmpty()) {
            acTvName.setVisibility(View.GONE);

            acTvNumber.setTextColor(Color.parseColor(getString(R.string.colorContactBackground)));
            acTvNumber.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
            acTvNumber.setTypeface(null, Typeface.BOLD);
        } else {
            acTvName.setText(name);
        }

        acTvNumber.setText(accountWallet);
        acChbFavorite.setChecked(isFavorite);

        switch ((int) serviceId) {
            case 123:
                acImvAvatar.setImageResource(R.drawable.ic_mastercard);
                break;
            case 222:
                acImvAvatar.setImageResource(R.drawable.ic_triolan);
                break;
            case 333:
                acImvAvatar.setImageResource(R.drawable.ic_man);
                break;
            case 555:
                acImvAvatar.setImageResource(R.drawable.ic_kyivstar);
                break;
            case 967:
                acImvAvatar.setImageResource(R.drawable.ic_visa);
                break;
            case 9:
                acImvAvatar.setImageResource(R.drawable.ic_lifecell);
                break;
        }

        return view;
    }

}
