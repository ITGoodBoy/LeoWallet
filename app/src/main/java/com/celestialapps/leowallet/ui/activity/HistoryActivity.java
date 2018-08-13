package com.celestialapps.leowallet.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.celestialapps.leowallet.R;
import com.celestialapps.leowallet.network.model.response.Transaction;
import com.celestialapps.leowallet.presentation.presenter.HistoryPresenter;
import com.celestialapps.leowallet.presentation.view.HistoryView;
import com.celestialapps.leowallet.ui.adapter.TransactionAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryActivity extends BottomBarActivity implements HistoryView {

    @InjectPresenter
    HistoryPresenter mHistoryPresenter;
    @BindView(R.id.rv_transactions)
    RecyclerView mRvTransactions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);

        mRvTransactions.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRvTransactions.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        mHistoryPresenter.getTransactionHistory(getAuthToken());
    }

    @Override
    public int getActiveButtonId() {
        return R.id.llc_history;
    }

    @Override
    public void gettingHistoryStarted() {

    }

    @Override
    public void gettingHistorySuccess(List<Transaction> transactions) {
        mRvTransactions.setAdapter(new TransactionAdapter(this, transactions));
    }

    @Override
    public void gettingHistoryFailed(String message) {
        showSnackBarMessage(message);
    }
}
