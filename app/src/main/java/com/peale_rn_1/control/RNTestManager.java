package com.peale_rn_1.control;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.peale_rn_1.model.UserTest;
import com.peale_rn_1.service.usertest.UserTestServiceImpl;

import java.text.ParseException;

/**
 * Created by dell on 2016/9/18.
 */
public class RNTestManager extends ReactContextBaseJavaModule {

    public RNTestManager(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "TestManager";
    }

    @ReactMethod
    public UserTest TestFourGroup(String userId) throws ParseException {

        UserTestServiceImpl userTestService = new UserTestServiceImpl();
        String[] words={"one","two","three","four","five"};
        int index=1;
        return userTestService.TestFourGroup(userId,words,index);

    }

    @ReactMethod
    public void word(String word){

    }

}
