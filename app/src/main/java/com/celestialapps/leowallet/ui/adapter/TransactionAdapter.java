package com.celestialapps.leowallet.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.celestialapps.leowallet.R;
import com.celestialapps.leowallet.network.model.response.Transaction;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder> {

    private Context mContext;
    private List<Transaction> mTransactions;

    private LayoutInflater mLayoutInflater;
    private SimpleDateFormat mSimpleDateFormat;

    public TransactionAdapter(Context context, List<Transaction> transactions) {
        mContext = context;
        mTransactions = transactions;

        mLayoutInflater = LayoutInflater.from(context);
        mSimpleDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa", Locale.getDefault());
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_transaction, parent, false);
        return new TransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        Transaction transaction = mTransactions.get(position);

        holder.mAcTvAmount.setText(mContext.getString(R.string.count) + " " + transaction.getCount());
        holder.mAcTvDate.setText(mContext.getString(R.string.date) + " " + mSimpleDateFormat.format(transaction.getDate()));
        holder.mAcTvRecipient.setText(mContext.getString(R.string.recipient_2) + " " + transaction.getRecipientAccount());
        holder.mAcTvSender.setText(mContext.getString(R.string.sender) + " " + transaction.getSenderAccount());
    }

    @Override
    public int getItemCount() {
        return mTransactions.size();
    }

    static class TransactionViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ac_tv_date)
        AppCompatTextView mAcTvDate;
        @BindView(R.id.ac_tv_amount)
        AppCompatTextView mAcTvAmount;
        @BindView(R.id.ac_tv_recipient)
        AppCompatTextView mAcTvRecipient;
        @BindView(R.id.ac_tv_sender)
        AppCompatTextView mAcTvSender;

        public TransactionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
