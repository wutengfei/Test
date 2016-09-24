package com.peale_rn_1.service.usertest;


import android.content.Context;
import com.peale_rn_1.MainApplication;
import com.peale_rn_1.dao.candidatetest.CandidateTestDaoImpl;

import com.peale_rn_1.dao.candidatetestwords.CandidateTestWordsDaoImpl;

import com.peale_rn_1.dao.testpreference.TestPreferenceDaoImpl;

import com.peale_rn_1.dao.usertest.UserTestDaoImpl;
import com.peale_rn_1.model.CandidateTest;
import com.peale_rn_1.model.CandidateTestWords;
import com.peale_rn_1.model.UserTest;

import com.peale_rn_1.service.candidatetest.CandidateTestServiceImpl;

import com.peale_rn_1.service.testpreference.TestPreferenceServiceImpl;


import java.util.Date;
import java.util.List;
import java.util.Random;


public class UserTestServiceImpl implements UserTestService {

    UserTestDaoImpl userTestDao = new UserTestDaoImpl();
    CandidateTestWordsDaoImpl candidateTestWordsDao = new CandidateTestWordsDaoImpl();
    CandidateTestDaoImpl candidateTestDao = new CandidateTestDaoImpl(MainApplication.getContext());
    CandidateTestServiceImpl candidateTestService = new CandidateTestServiceImpl();
    TestPreferenceDaoImpl testPreferenceDao = new TestPreferenceDaoImpl();
    TestPreferenceServiceImpl testPreferenceService = new TestPreferenceServiceImpl();

    @Override
    public void addUserTest1(String userId, String word, int testType, int testAspect, int testDifficulty,
                             int rightTimes, int wrongTimes, int totalTimes, Date startTime, Date endTime) {

        userTestDao.save(new UserTest(userId, word, testType, testAspect, testDifficulty, rightTimes, wrongTimes, totalTimes, startTime, endTime));
        if (wrongTimes > 0) {
            CandidateTestWords ctw = new CandidateTestWords(userId, word, 2);
            if (candidateTestWordsDao.find(ctw.getUserId(), ctw.getWord()) == null)
                candidateTestWordsDao.save(ctw);
        }
        if (totalTimes > 0) {
            //更新测试类型答题情况
            candidateTestService.UpdateCandidateTestCount(userId, testType, testAspect, testDifficulty, totalTimes);
            //更新测试类型升降级
            candidateTestService.UpdateCandidateStatus(userId, testType, testAspect, testDifficulty);
        }
    }

    @Override
    public UserTest TestFourGroup(String userId, String[] words, int index) {
        // TODO Auto-generated method stub
        //如果是获取第2，3,4道题目，则直接去用户测试表中获取
        for (int i = 0; i < 5; i++)
            System.out.println("要考的单词：" + words[i]);

        if (index > 1) {
            List<UserTest> list = userTestDao.find(userId, 0);
            if (list != null && list.size() >= 5 - index) {
                userTestDao.delete(list.get(0).getId());  //拿走一道题目就删掉出题记录
                return list.get(list.size() - (5 - index));
            } else return null;
        }
        List<CandidateTest> ctlist = candidateTestDao.getCandidate(userId);
        //初始化难度为2的备选测试类型
        if (ctlist == null) {
            candidateTestService.AddBatchCandidateTest(userId);
            ctlist = candidateTestDao.getCandidate(userId);
        }
        double[] Recommend = new double[5];   //5个备选测试类型的擅长度
        int max = 0;
        int min = 0;
        int i = 0;
        for (CandidateTest ct : ctlist) {
            Recommend[i] = testPreferenceService.recommendByPreference(ct.getUserId(), ct.getTestType(), ct.getTestAspect(), ct.getTestDifficulty());
            System.out.println(ct.getTestType() + "/" + ct.getTestAspect() + "/" + ct.getTestDifficulty() + "的推荐度为：" + Recommend[i]);
            if (i > 0 && Recommend[max] < Recommend[i])
                max = i;
            if (i > 0 && Recommend[min] > Recommend[i])
                min = i;
        }
        Object[][] fourGroup = new Object[4][4];  //存储四道题目
        if (max == min)
            min = (max + 1) % 5;
        int[] testindex = new int[4];
        testindex[0] = max;
        testindex[1] = min;
        int tindex = 0;  //随机去掉一个备选测试类型
        while (tindex == max || tindex == min)
            tindex = new Random().nextInt(5);
        int j = 2;
        for (i = 0; i < 5; i++)
            if (i != max && i != min && i != tindex && j < 4)
                testindex[j++] = i;
        for (i = 0; i < 4; i++) {
            fourGroup[i][1] = ctlist.get(testindex[i]).getTestType();
            fourGroup[i][2] = ctlist.get(testindex[i]).getTestAspect();
            fourGroup[i][3] = ctlist.get(testindex[i]).getTestDifficulty();
        }
        //从场景五个单词中随机抽取两个单词
        for (i = 0; i < 2; i++) {
            int testword = new Random().nextInt(5);
            while (words[testword] == null)      //当随机选中的单词下标为已选过的单词时接着随机选择
                testword = new Random().nextInt(5);
            fourGroup[i][0] = words[testword];
            System.out.println("随机抽中的单词有：" + fourGroup[i][0]);
            words[testword] = null;
        }
        //从备考单词中抽取两个单词===================稍后再写此功能
  /*      UserInfo ui = userInfoDao.find(userId);
        int[] wd = new int[2];
        if (ui.getBeforeLevel() <= 2) {
            wd[0] = 2;
            wd[1] = 2;
        } else if (ui.getBeforeLevel() <= 4) {
            wd[0] = 2;
            wd[1] = 5;
        } else {
            wd[0] = 5;
            wd[1] = 5;
        }
        System.out.println(wd[0] + "/" + wd[1]);
        CandidateTestWords ctw1 = candidateTestWordsDao.find(userId, wd[0]);
        if (ctw1 != null)
            fourGroup[2][0] = ctw1.getWord();
        else fourGroup[2][0] = "extraword1";
        CandidateTestWords ctw2 = candidateTestWordsDao.find(userId, wd[1]);
        if (ctw2 != null)
            fourGroup[3][0] = ctw2.getWord();
        else fourGroup[3][0] = "extraword2";
        */
        //写入用户测试表中
        for (i = 0; i < 4; i++)
            userTestDao.save(new UserTest(userId, (String) fourGroup[i][0], (int) fourGroup[i][1], (int) fourGroup[i][2], (int) fourGroup[i][3], 0, 0, 0, new Date(), null));
        List<UserTest> utlist = userTestDao.find(userId, 0);
        userTestDao.delete(utlist.get(utlist.size() - 4).getId());  //返回一道题目就删掉出题记录
        return utlist.get(0);
    }
}
