package com.peale_rn_1.service.wordservice;

import com.peale_rn_1.MainApplication;
import com.peale_rn_1.dao.WordDAO;
import com.peale_rn_1.model.Tb_word;

import java.util.List;

/**
 * Created by dell on 2016/9/28.
 */
public class WordService {
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
    public String[] search(String word) {
        WordDAO wordDAO = new WordDAO(MainApplication.getContext());
        String[] oneWord = wordDAO.search(word);
        String[] twoWords = wordDAO.search();

        System.out.println("单词的图片，音频，课文原句：---------" + oneWord[0] + "----" + oneWord[1] + "-----------" + oneWord[2]);
        System.out.println("两个干扰单词及其图片：--" + twoWords[0] + "----" + twoWords[1] + "-----" + twoWords[2] + "---" + twoWords[3]);

        String[] allWords = new String[7];
        allWords[0] = oneWord[0];
        allWords[1] = oneWord[1];
        allWords[2] = oneWord[2];
        allWords[3] = twoWords[0];
        allWords[4] = twoWords[1];
        allWords[5] = twoWords[2];
        allWords[6] = twoWords[3];
        return allWords;
    }
}
