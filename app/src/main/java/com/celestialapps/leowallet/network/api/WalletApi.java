package com.celestialapps.leowallet.network.api;

import com.celestialapps.leowallet.network.model.request.PromotionalCodeRequest;
import com.celestialapps.leowallet.network.model.request.ReceiveMoneyRequest;
import com.celestialapps.leowallet.network.model.request.SendMoneyRequest;
import com.celestialapps.leowallet.network.model.request.UpBalanceRequest;
import com.celestialapps.leowallet.network.model.response.Balance;
import com.celestialapps.leowallet.network.model.response.Transaction;
import com.celestialapps.leowallet.network.model.response.Wallet;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;

public interface WalletApi {


    @GET("/api/wallet/my-wallet")
    Observable<Response<Wallet>> getMyWallet(@Header("X-AUTH-TOKEN") String authToken);

    @GET("/api/wallet/get-transactions")
    Observable<Response<List<Transaction>>> getMyTransactions(@Header("X-AUTH-TOKEN") String authToken);

    @PUT("/api/wallet/enter-promotional-code")
    Observable<Response<Balance>> enterPromotionalCode(@Header("X-AUTH-TOKEN") String authToken,
                                                       @Body PromotionalCodeRequest promotionalCodeRequest);

    @PUT("/api/wallet/receive-money")
    Observable<Response<Balance>> receiveMoney(@Header("X-AUTH-TOKEN") String authToken,
                                               @Body ReceiveMoneyRequest receiveMoneyRequest);

    @PUT("/api/wallet/send-money")
    Observable<Response<Balance>> sendMoney(@Header("X-AUTH-TOKEN") String authToken,
                                            @Body SendMoneyRequest sendMoneyRequest);

    @PUT("/api/wallet/uo-balance")
    Observable<Response<Balance>> upBalance(@Header("X-AUTH-TOKEN") String authToken,
                                            @Body UpBalanceRequest upBalanceRequest);
}
