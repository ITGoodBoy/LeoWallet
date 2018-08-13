package com.celestialapps.leowallet.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.celestialapps.leowallet.network.model.response.Transaction;

import java.util.List;

public interface HistoryView extends MvpView {

    void gettingHistoryStarted();
    void gettingHistorySuccess(List<Transaction> transactions);
    void gettingHistoryFailed(String message);
}
