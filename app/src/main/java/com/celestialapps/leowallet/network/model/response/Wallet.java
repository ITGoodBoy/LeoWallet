package com.celestialapps.leowallet.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wallet {

    @SerializedName("account")
    @Expose
    private String account;
    @SerializedName("balance")
    @Expose
    private long balance;
    @SerializedName("invoices")
    @Expose
    private List<Invoice> invoices;
    @SerializedName("favorites")
    @Expose
    private List<Favorite> favorites;
    @SerializedName("lasts")
    @Expose
    private List<Last> lasts;

}
