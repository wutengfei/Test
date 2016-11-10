package com.peale_rn_1.service.wordservice;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableArray;
import com.peale_rn_1.MainApplication;
import com.peale_rn_1.dao.WordDAO;
import com.peale_rn_1.model.Tb_word;

import java.util.List;

/**
 * Created by dell on 2016/9/28.
 */
public class WordServiceImpl {
    //查找    根据主题和年级查找单词
    public Tb_word[] find(String proTopic, String grade) {
        WordDAO wordDAO = new WordDAO(MainApplication.getContext());
        Tb_word[] words = wordDAO.find(proTopic, grade);
        return words;

    }

    //根据传过来的单词查询出年级和主题
    public String[] find(String firstWord) {
        WordDAO wordDAO = new WordDAO(MainApplication.getContext());
        return wordDAO.find(firstWord);
    }

    //根据单词查询其图片，音频，课文原句
    public WritableArray search(String word) {
        WritableArray writableArray = Arguments.createArray();
        WordDAO wordDAO = new WordDAO(MainApplication.getContext());
        String[] oneWord = wordDAO.search(word);
        String[] twoWords = wordDAO.search();
        String pathPrefix = wordDAO.searchPath();
//        System.out.println("单词的图片，音频，课文原句：---------" + pathPrefix + "/" + oneWord[0] + "----"
//                + pathPrefix + "/" + oneWord[1] + "-----------" + oneWord[2]);
//        System.out.println("两个干扰单词及其图片：--" + twoWords[0] + "----" + pathPrefix + "/" + twoWords[1] + "-----"
//                + twoWords[2] + "---" + pathPrefix + "/" + twoWords[3]);

//单词的图片，音频，课文原句,干扰单词1，干扰单词图片1，干扰单词2，干扰单词图片2
        String[] allWords = new String[7];
        allWords[0] = pathPrefix + "/" + oneWord[0];
        allWords[1] = pathPrefix + "/" + oneWord[1];
        allWords[2] = oneWord[2];
        allWords[3] = twoWords[0];
        allWords[4] = pathPrefix + "/" + twoWords[1];
        allWords[5] = twoWords[2];
        allWords[6] = pathPrefix + "/" + twoWords[3];
        for (int i = 0; i < 7; i++) {
            writableArray.pushString(allWords[i]);
        }
        return writableArray;
    }
}
