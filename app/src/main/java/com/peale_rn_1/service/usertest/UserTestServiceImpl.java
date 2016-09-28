package com.peale_rn_1.service.usertest;


import com.peale_rn_1.MainApplication;
import com.peale_rn_1.dao.candidatetest.CandidateTestDaoImpl;
import com.peale_rn_1.dao.candidatetestwords.CandidateTestWordsDaoImpl;
import com.peale_rn_1.dao.usertest.UserTestDaoImpl;
import com.peale_rn_1.model.CandidateTest;
import com.peale_rn_1.model.CandidateTestWords;
import com.peale_rn_1.model.Tb_word;
import com.peale_rn_1.model.UserTest;
import com.peale_rn_1.service.candidatetest.CandidateTestServiceImpl;
import com.peale_rn_1.service.testpreference.TestPreferenceServiceImpl;
import com.peale_rn_1.service.wordservice.WordService;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Random;


public class UserTestServiceImpl implements UserTestService {
    public UserTestServiceImpl() {
    }

    UserTestDaoImpl userTestDao = new UserTestDaoImpl(MainApplication.getContext());
    CandidateTestWordsDaoImpl candidateTestWordsDao = new CandidateTestWordsDaoImpl(MainApplication.getContext());
    CandidateTestDaoImpl candidateTestDao = new CandidateTestDaoImpl(MainApplication.getContext());
    CandidateTestServiceImpl candidateTestService = new CandidateTestServiceImpl();
    TestPreferenceServiceImpl testPreferenceService = new TestPreferenceServiceImpl();

    @Override
    public void addUserTest1(String userId, String word, int testType, int testAspect, int testDifficulty,
                             int rightTimes, int wrongTimes, int totalTimes, Date startTime, Date endTime) {
        UserTest userTest = new UserTest(userId, word, testType, testAspect, testDifficulty, rightTimes, wrongTimes,
                totalTimes, startTime, endTime);
        userTestDao.save(userTest);
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
    public UserTest TestFourGroup(String userId, String[] words, int index) throws ParseException {
        //如果是获取第2，3,4道题目，则直接去用户测试表中获取
        for (int i = 0; i < 5; i++)
            System.out.println("要考的单词：" + words[i]);

        if (index > 1) {//第2，3，4道题
            List<UserTest> list = userTestDao.find(userId, 0);
            if (list != null && list.size() >= 5 - index) {
                userTestDao.delete(list.get(0).getId());  //拿走一道题目就删掉出题记录
                return list.get(list.size() - (5 - index));
            } else return null;
        } else {//第1道题
            List<CandidateTest> ctlist = candidateTestDao.getCandidate(userId);
            //初始化难度为2的备选测试类型
            if (ctlist == null || ctlist.size() < 5) {
                candidateTestService.AddBatchCandidateTest(userId);
                ctlist = candidateTestDao.getCandidate(userId);
            }
            double[] Recommend = new double[5];   //5个备选测试类型的擅长度
            int max = 0;
            int min = 0;
            int i = 0;
            for (i = 0; i < 5; i++) {
                CandidateTest ct = ctlist.get(i);
                Recommend[i] = testPreferenceService.recommendByPreference(ct.getUserId(), ct.getTestType(), ct.getTestAspect(),
                        ct.getTestDifficulty());
                System.out.println(ct.getTestType() + "/" + ct.getTestAspect() + "/" + ct.getTestDifficulty() + "的推荐度为：" +
                        "" + Recommend[i]);
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

//从候选测试单词表中取出两个单词，若没有就从当前day中随机取两个，并保证主题和年级要和前面的两个单词一致
            CandidateTestWords[] candidateTestWords = candidateTestWordsDao.find(userId);
            WordService wordService=new WordService();
            CandidateTestWords nextTwoWords[] = new CandidateTestWords[2];
            String nextTwoWord[]=new String[2];
            String firstWord=fourGroup[0][0]+"";
            String[] topicAndGrade = wordService.find(firstWord);////根据传过来的单词查询出年级和主题
            Tb_word[] wordOfDay=wordService.find(topicAndGrade[0],topicAndGrade[1]);//根据主题和年级查找与单词同主题和年级下的单词

            //得到候选测试单词数组中的两个单词
            if (candidateTestWords == null) {//在day中取两个单词
                for (int k = 0; k < 2; k++) {
                    int wordOfDayCount = new Random().nextInt(wordOfDay.length);//产生一个0-wordOfDay之间的随机数
                    nextTwoWord[k] = wordOfDay[wordOfDayCount].getName();
                }
                for (i = 2; i < 4; i++) {//存入后两个单词
                    fourGroup[i][0] = nextTwoWord[i];
                }
            } else if (candidateTestWords.length == 1) {
                fourGroup[2][0]= candidateTestWords[0].getWord();//把第三道题的单词存入fourGroup
                int wordOfDayCount = new Random().nextInt(wordOfDay.length);
                nextTwoWord[0] = wordOfDay[wordOfDayCount].getName();
                fourGroup[3][0] = nextTwoWord[0];//第四道在day中取
            } else if (candidateTestWords.length >= 2) {//在候选测试单词表中取两个单词
                for (int k = 0; k < 2; k++) {
                    int candidateTestWordCount = new Random().nextInt(candidateTestWords.length);
                    nextTwoWords[k] = candidateTestWords[candidateTestWordCount];
                }
                for (i = 2; i < 4; i++) {//存入后两个单词
                    fourGroup[i][0] = nextTwoWords[i].getWord();
                }
            }

            //写入用户测试表中
            for (i = 0; i < 4; i++) {
                UserTest userTest = new UserTest(userId, (String) fourGroup[i][0], (int) fourGroup[i][1],
                        (int) fourGroup[i][2], (int) fourGroup[i][3], 0, 0, 0, new Date(), null);
                userTestDao.save(userTest);
            }
            List<UserTest> utlist = userTestDao.find(userId, 0);
            userTestDao.delete(utlist.get(utlist.size() - 4).getId());  //返回一道题目就删掉出题记录。
            return utlist.get(0);//上面只删除数据库中的一条，但表中仍是size为4.
        }
    }
}
