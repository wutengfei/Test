package com.peale_rn_1.control;

import android.widget.Toast;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.IllegalViewOperationException;
import com.peale_rn_1.dao.UserDAO;
import com.peale_rn_1.model.Tb_user;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/9/2.
 */
public class RNUserManager extends ReactContextBaseJavaModule {

    public RNUserManager(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "UserAction";
    }

    @ReactMethod
    public void UserRegister(ReadableMap user, Callback successCallback){
        Tb_user userInfo = new Tb_user();
        userInfo.setUserName(user.getString( "userName"));
        userInfo.setPassWord(user.getString("passWord"));
        userInfo.setRealName(user.getString("realName"));
        userInfo.setSex(user.getString("sex"));
        userInfo.setSchool(user.getString("school"));
        userInfo.setGrade(user.getString("grade"));
        userInfo.setClassNum(user.getString("classNum"));
        userInfo.setBirthYear(user.getString("birthYear"));
//Toast.makeText(getReactApplicationContext(),userInfo.getUserName()+" "+userInfo.getPassWord(),Toast.LENGTH_LONG).show();
        UserDAO userDao = new UserDAO(getReactApplicationContext());
        try{
            if(userDao.insert(userInfo)) {
                successCallback.invoke("ok");
            }
        }catch (IllegalViewOperationException e){
            e.printStackTrace();
        }
    }

    @ReactMethod
    public void UserLogin(ReadableMap user,Callback successCallback,Callback failCallback){
        String username = user.getString("userName");
        UserDAO userDao = new UserDAO(getReactApplicationContext());
        try{
            String string = userDao.findUserByName(username);
            if(string == null){
                failCallback.invoke("该用户不存在");
            }else{
                if(string.equals(user.getString("passWord"))){
                    successCallback.invoke("success");
                }else{
                    failCallback.invoke("密码不正确");
                }
            }
        }catch (IllegalViewOperationException e){
            e.printStackTrace();
        }
    }


}
