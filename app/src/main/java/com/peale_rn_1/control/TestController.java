/*
package com.peale_rn_1.control;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

*/
/**
 * Created by dell on 2016/9/28.
 *//*

public class TestController {
    public ModelAndView listTestWordattribute(int num) {
        System.out.println("listTestWordattribute");
        ModelAndView mv = new ModelAndView("share/json");
        MyStatus status = new MyStatus();
        // 总的json对象
        JSONObject jsonObject = new JSONObject();
        JSONArray usersArray = new JSONArray();
        JSONObject picJson = new JSONObject();
        try {
			*/
/*
			 * 1、根据前台传回数据，获得此时的学习场景，一共五个关卡（1，2，3，4，5） 2、根据前台传回数据，获得此时登录人的学号。
			 *//*

            // System.out.println("try..........."+num);
            TestFourGroup tests = new TestFourGroupImpl();
            String[] knowledges = new String[5];
            knowledges[0] = "boat";
            knowledges[1] = "horse";
            knowledges[2] = "fly";
            knowledges[3] = "sleep";
            knowledges[4] = "family";
            LearnTest lt = new LearnTest();
            System.out.println("进入方法前。。。。。。。。。");
            lt = tests.TestFourGroup("2141003034", knowledges, num);
            System.out.println("生成的测试四元组为：" + lt.getTestKnowledgeId() + "/" + lt.getTestType() + "/"
                    + lt.getTestAspect() + "/" + lt.getTestDifficulty());
            picJson.put("testKnowledgeId", lt.getTestKnowledgeId());
            picJson.put("testType", lt.getTestType());
            picJson.put("testAspect", lt.getTestAspect());
            picJson.put("difficulty", lt.getTestDifficulty());
            usersArray.add(picJson);
        } catch (Exception e) {
            status.setMessage("未知异常");
            status.setStatus(StatusConstant.UNKONWN_EXECPTION);
        } finally {
            // -------------------返回视图
            JsonTool.putJsonObject(jsonObject, usersArray, status);
            mv.addObject("json", jsonObject.toString());
            return mv;
        }
    }


    // 根据单词查询，展示要显示图片信息内容
    public ModelAndView listWordResource(String content) {
        ModelAndView mv = new ModelAndView("share/json");
        String propertyText = null;
        String temppropertyText = null;
        MyStatus status = new MyStatus();
        // 总的json对象
        JSONObject jsonObject = new JSONObject();
        JSONArray usersArray = new JSONArray();
        try {
            do {
                //查询正确单词的图片资源
                List<Object> wordpic = new ArrayList<Object>();
                wordpic.add(content);
                wordpic.add(1);
                List<WordResource> wordpictrue = wordResourceService.getAllData("o.name=? and o.type=?", wordpic.toArray());
                //查询正确单词的音频资源
                List<Object> wordMP3 = new ArrayList<Object>();
                wordMP3.add(content);
                wordMP3.add(3);
                List<WordResource> wordAudio = wordResourceService.getAllData("o.name=? and o.type=?", wordMP3.toArray());
                if (wordpictrue != null||wordAudio!=null) {
                    // 随机查询两个图片对象
                    System.out.println("wordpictrue" + wordpictrue);
                    System.out.println("wordAudio" + wordAudio);
                    WordResource wordpicwrong1 = wordResourceService.findByContent();
                    WordResource wordpicwrong2 = wordResourceService.findByContent();
                    if (wordpicwrong1 != null && wordpicwrong2 != null) {
                        // 获取单词、音频图片的保存路径
                        pictruepath = wordpictrue.get(0).getSavepath();
                        audtruepath=wordAudio.get(0).getSavepath();
                        picwrongpath1 = wordpicwrong1.getSavepath();
                        picwrongpath2 = wordpicwrong2.getSavepath();
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            } while (pictruepath.equals(picwrongpath1) || picwrongpath1.equals(picwrongpath2)
                    || pictruepath.equals(picwrongpath2));
            // 根据单词资源的保存路径来查询单词
            WordResource wordpicwrongpath1 = wordResourceService.find("o.savepath = ?", picwrongpath1);
            WordResource wordpicwrongpath2 = wordResourceService.find("o.savepath = ?", picwrongpath2);
            // 根据单词资源表中的资源路径来查询单词content属性
            if (wordpicwrongpath1 != null && wordpicwrongpath2 != null) {
                picwrongcontent1 = wordpicwrongpath1.getName();
                picwrongcontent2 = wordpicwrongpath2.getName();
                System.out.println("原来的单词" + content);
                System.out.println("第一个错误图片对应的单词" + picwrongcontent1);
                System.out.println("第二个错误图片对应的单词" + picwrongcontent2);
            }
            Iword iwordProperty = sentenceSim.findWordProperty(content);
            if (null != iwordProperty) {
                propertyText = iwordProperty.getPropertyText();
                temppropertyText = propertyText.substring(propertyText.indexOf(".") + 1, propertyText.length());
                System.out.println(propertyText.indexOf("."));
                System.out.println(propertyText.contains("2"));
                System.out.println(propertyText.substring(propertyText.indexOf(".") + 1, propertyText.length()));
            }
            JSONObject picJson = new JSONObject();
            // 单词内容存放在容器
            picJson.put("content", content);
            picJson.put("propertyText", temppropertyText);// 课文原句
            System.out.println("课文原句" + temppropertyText);
            picJson.put("wordpicture", pictruepath);
            picJson.put("audtruepath", audtruepath);
            picJson.put("picwrongcontent1", picwrongcontent1);
            picJson.put("wordpicture1", picwrongpath1);
            picJson.put("picwrongcontent2", picwrongcontent2);
            picJson.put("wordpicture2", picwrongpath2);
            usersArray.add(picJson);
        } catch (Exception e) {
            status.setMessage("未知异常");
            status.setStatus(StatusConstant.UNKONWN_EXECPTION);
        } finally {
            // -------------------返回视图
            JsonTool.putJsonObject(jsonObject, usersArray, status);
            mv.addObject("json", jsonObject.toString());
            return mv;
        }
    }

    public ModelAndView saveMiddleTest(String UserId, String userName, String word, int TestType, int TestAspect,
                                       int TestDifficulty, int rightTimes, int wrongTimes, int totalTimes, Date startTime, Date endTime,
                                       int tempcoin) {
        System.out.println("进入saveMiddleTest");
        ModelAndView mv = new ModelAndView("share/json");
        MyStatus status = new MyStatus();
        // 总的Json对象
        JSONObject jsonObject = new JSONObject();
        JSONArray usersArray = new JSONArray();
        JSONObject MiddleTest = new JSONObject();
        try {
            User user = (User) userService.findByName(userName);
            int coin = user.getAllCoins();
            System.out.println(user.getPassword());
            System.out.println(user.getUserId());
            coin = coin + tempcoin;
            user.setAllCoins(coin);
            System.out.println("金币数" + user.getAllCoins());
            userService.SaveCoinAndScene(user);
            usersArray.add(MiddleTest);
            System.out.println(userService);
            // 获取当前日期
            java.util.Date d = new java.util.Date();
            // long dtime = d.getTime();
            // Date date = new Date(dtime);
            UpdateLearnAction ula = new UpdateLearnActionImpl();
            // ula.UpdateTest(UserId, word, TestType, TestAspect,
            // TestDifficulty, rightTimes, wrongTimes, totalTimes, startTime,
            // endTime);
            MiddleTest.put("UserId", UserId);
            // MiddleTest.put("TestKnowledgeId", TestKnowledgeId);
            MiddleTest.put("TestType", TestType);
            MiddleTest.put("TestAspect", TestAspect);
            MiddleTest.put("TestDifficulty", TestDifficulty);
            MiddleTest.put("coin", user.getAllCoins());
            usersArray.add(MiddleTest);
        } catch (Exception e) {
            status.setMessage("未知异常");
            status.setStatus(StatusConstant.UNKONWN_EXECPTION);
        } finally {
            // -------------------返回视图
            JsonTool.putJsonObject(jsonObject, usersArray, status);
            mv.addObject("json", jsonObject.toString());
            return mv;
        }
    }
}
*/
