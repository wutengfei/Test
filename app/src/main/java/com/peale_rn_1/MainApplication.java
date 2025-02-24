package com.peale_rn_1;

import android.app.Application;

import android.content.Context;
import com.facebook.react.ReactApplication;

import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.peale_rn_1.control.RNFilePackage;
import com.peale_rn_1.control.RNResourcePackage;
import com.peale_rn_1.control.RNTestPackage;
import com.peale_rn_1.control.RNUserPackage;

import java.util.Arrays;
import java.util.List;

import com.brentvatne.react.ReactVideoPackage;
import com.burnweb.rnsimplealertdialog.RNSimpleAlertDialogPackage;

public class MainApplication extends Application implements ReactApplication {

    private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
        @Override
        protected boolean getUseDeveloperSupport() {
            return BuildConfig.DEBUG;
        }

        @Override
        protected List<ReactPackage> getPackages() {
            return Arrays.<ReactPackage>asList(
                    new MainReactPackage(),

                    new RNFilePackage(),//自定义模块，文件下载
                    new RNUserPackage(),//自定义模块，用户方面
                    new RNResourcePackage(), //自定义模块，资源方面
                    new RNTestPackage(),
                    new ReactVideoPackage(),
                    new RNSimpleAlertDialogPackage()
            );
        }
    };

    @Override
    public ReactNativeHost getReactNativeHost() {
        return mReactNativeHost;
    }

    private static Context context;

    @Override
    public void onCreate() {
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }

}
