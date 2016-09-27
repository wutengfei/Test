package com.peale_rn_1.control;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
//import com.nativemoduletest.MyToastModuleTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by dell on 2016/7/19.
 */
public class RNFilePackage implements ReactPackage {
    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        List<NativeModule> modules = new ArrayList<>();
      //  modules.add(new MyToastModuleTest( reactContext));
        modules.add(new RNFileManager(reactContext));
      //  modules.add(new RNUserManager(reactContext));
        return  modules;
    }

    @Override
    public List<Class<? extends JavaScriptModule>> createJSModules() {
        return Collections.emptyList();
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return Collections.emptyList();
    }
}
