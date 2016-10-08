package com.peale_rn_1.control;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.peale_rn_1.MainApplication;
import com.peale_rn_1.dao.UserDAO;
import com.peale_rn_1.model.UserTest;
import com.peale_rn_1.service.usertest.UserTestServiceImpl;
import com.peale_rn_1.service.wordservice.WordService;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by Fly Wu on 2016/9/18.
 */

public class RNTestManager extends ReactContextBaseJavaModule {

    public RNTestManager(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "TestManager";
    }

    /**
     * num控制取闯关测试中第几道题。由考察知识点、考察类型、考察方面和难度，四元组确定唯一一道题。
     *
     * @param userId
     * @param index  闯关测试第几题、取值范围1-4
     * @return 四元组
     * @throws ParseException
     */
    @ReactMethod
    public UserTest listTestWordAttribute(String userId, int index) throws ParseException {

        UserTestServiceImpl userTestService = new UserTestServiceImpl();
        String[] words = {"map", "river", "way", "trip", "round"};
        UserTest userTest = userTestService.TestFourGroup(userId, words, index);
        String id = userTest.getUserId();
        int type = userTest.getTestType();
        int aspect = userTest.getTestAspect();
        int difficulty = userTest.getTestDifficulty();
        UserTest ut = new UserTest(id, type, aspect, difficulty);
        return ut;
    }

    /**
     * 获取该测试单词的图片资源，并从寻找随机两个干扰选项，并在资源库中获取干扰选项图片资源
     *
     * @param content 需测试单词
     * @return 该单词的图片资源，以及两个干扰选项及其图片资源，以及该单词的音频资源
     */
    @ReactMethod
    public String[] listWordResource(String content) {
        WordService wordService = new WordService();
        return wordService.search(content);
    }

    /**
     * 通过用户的用户ID在用户信息表里找到该用户后，并记录该题用户是否回答正确，如果回答正确
     * 在用户信息表中金币数加上此关获得的金币数，rightTimes+1,若回答错误wrongTimes+1
     *
     * @param userId
     * @param isTrue 是否回答正确
     */
    @ReactMethod
    public void saveMiddleTest(String userId, boolean isTrue) {
        UserDAO userDAO = new UserDAO(MainApplication.getContext());
        userDAO.isAnswerRight(userId, isTrue);
    }
}
