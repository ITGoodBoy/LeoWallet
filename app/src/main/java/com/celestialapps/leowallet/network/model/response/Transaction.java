package com.celestialapps.leowallet.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("count")
    @Expose
    private long count;
    @SerializedName("date")
    @Expose
    private Date date;
    @SerializedName("recipientAccount")
    @Expose
    private String recipientAccount;
    @SerializedName("senderAccount")
    @Expose
    private String senderAccount;

}
