package com.celestialapps.leowallet.network.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpBalanceRequest {

    @SerializedName("balanceUp")
    @Expose
    private long balanceUp;
}