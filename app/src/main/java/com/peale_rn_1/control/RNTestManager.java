package com.peale_rn_1.control;

import com.facebook.imagepipeline.producers.NetworkFetcher;
import com.facebook.react.bridge.*;
import com.peale_rn_1.MainApplication;
import com.peale_rn_1.dao.UserDAO;
import com.peale_rn_1.dao.usertest.UserTestDaoImpl;
import com.peale_rn_1.model.UserTest;
import com.peale_rn_1.service.usertest.UserTestServiceImpl;
import com.peale_rn_1.service.wordservice.WordService;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

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
    public void listTestWordAttribute(String userId, int index, Callback successCallback) throws ParseException {

        UserTestServiceImpl userTestService = new UserTestServiceImpl();
        String[] words = {"map", "river", "way", "trip", "round"};
        UserTest userTest = userTestService.TestFourGroup(userId, words, index);
        String word = userTest.getWord();
        int type = userTest.getTestType();
        int aspect = userTest.getTestAspect();
        int difficulty = userTest.getTestDifficulty();
        // UserTest ut = new UserTest(id, type, aspect, difficulty);
        WritableArray writableArray = Arguments.createArray();
        writableArray.pushString(word);
        writableArray.pushInt(type);
        writableArray.pushInt(aspect);
        writableArray.pushInt(difficulty);
        successCallback.invoke(writableArray);
        System.out.println("四元组中的单词"+word);
    }

    /**
     * 获取该测试单词的图片资源，并从寻找随机两个干扰选项，并在资源库中获取干扰选项图片资源
     *
     * @param content 需测试单词
     * @return 该单词的图片资源，以及两个干扰选项及其图片资源，以及该单词的音频资源
     */
    @ReactMethod
    public void listWordResource(String content, Callback successCallback) {
        System.out.println("测试的单词------"+content);
        WordService wordService = new WordService();
        WritableArray writableArray = wordService.search(content);
        successCallback.invoke(writableArray);
    }

    /**
     * 通过用户的用户ID在用户信息表里找到该用户后，并记录该题用户是否回答正确，如果回答正确
     * 在用户信息表中金币数加上此关获得的金币数，rightTimes+1,若回答错误wrongTimes+1
     *
     * @param userId
     * @param rightTimes 回答正确次数
     * @param wrongTimes 回答错误次数
     * @param token      金币数
     */
    @ReactMethod
    public void saveMiddleTest(String userId, String word, int rightTimes, int wrongTimes, int token) throws ParseException {
        UserDAO userDAO = new UserDAO(MainApplication.getContext());
        System.out.println("正确次数-------" + rightTimes);
        System.out.println("错误次数-------" + wrongTimes);
        System.out.println("获得金币数-------" + token);
        userDAO.isAnswerRight(userId, rightTimes, wrongTimes, token);
        //答题之后将UserTest类型的实体类传递给addUserTest
        UserTestServiceImpl userTestService = new UserTestServiceImpl();
        UserTestDaoImpl userTestDao = new UserTestDaoImpl(MainApplication.getContext());
        List<UserTest> list = userTestDao.find(userId, word);
        int testType = list.get(0).getTestType();
        int testAspect = list.get(0).getTestAspect();
        int testDifficult = list.get(0).getTestDifficulty();
        int totalTimes = list.get(0).getTotalTimes();
        Date startTime = list.get(0).getStartTime();
        Date endTime = list.get(0).getEndTime();
        userTestService.addUserTest(userId, word, testType, testAspect, testDifficult, rightTimes, wrongTimes, totalTimes, startTime, endTime);


    }
}
