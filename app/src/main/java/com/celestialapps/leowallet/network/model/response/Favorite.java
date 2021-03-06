package com.celestialapps.leowallet.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Favorite {

    @SerializedName("serviceId")
    @Expose
    private int serviceId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("account")
    @Expose
    private String accountWallet;

}
