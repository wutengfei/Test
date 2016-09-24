package com.peale_rn_1.control;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

/**
 * Created by dell on 2016/9/18.
 */
public class RNTestManager  extends ReactContextBaseJavaModule {

    public RNTestManager(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "Test";
    }


}
