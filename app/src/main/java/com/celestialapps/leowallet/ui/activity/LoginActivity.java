package com.celestialapps.leowallet.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.celestialapps.leowallet.R;
import com.celestialapps.leowallet.network.model.request.SignInRequest;
import com.celestialapps.leowallet.network.model.response.AuthToken;
import com.celestialapps.leowallet.presentation.presenter.LoginPresenter;
import com.celestialapps.leowallet.presentation.view.LoginView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity
        implements LoginView{

    @InjectPresenter
    LoginPresenter mLoginPresenter;

    @BindView(R.id.ac_edt_login)
    AppCompatEditText mAcEdtLogin;
    @BindView(R.id.til_login)
    TextInputLayout mTilLogin;
    @BindView(R.id.ac_edt_password)
    AppCompatEditText mAcEdtPassword;
    @BindView(R.id.til_password)
    TextInputLayout mTilPassword;
    @BindView(R.id.ac_btn_login)
    AppCompatButton mAcBtnComeIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        if (!getAuthToken().isEmpty()) {
            startActivity(LeoWalletActivity.class);
            finish();
        }

        mAcBtnComeIn.setOnClickListener(v -> mLoginPresenter.login(
                new SignInRequest(mAcEdtLogin.getText().toString(), mAcEdtPassword.getText().toString())));
    }

    @Override
    public void loginStarted() {

    }

    @Override
    public void successLogin(AuthToken authToken) {
        setAuthToken(authToken.getTokenString());
        startActivity(LeoWalletActivity.class);
        finish();
    }

    @Override
    public void failedLogin(String message) {
        showSnackBarMessage(message);
    }
}
