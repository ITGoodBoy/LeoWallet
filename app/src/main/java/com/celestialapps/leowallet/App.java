package com.celestialapps.leowallet;

import android.app.Application;

import com.celestialapps.leowallet.dagger.components.AppComponent;
import com.celestialapps.leowallet.dagger.components.DaggerAppComponent;
import com.celestialapps.leowallet.dagger.modules.ContextModule;

public class App extends Application {


    private static AppComponent appComponent;

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = buildAppComponent();
    }

    protected AppComponent buildAppComponent() {
        return DaggerAppComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
    }

}
