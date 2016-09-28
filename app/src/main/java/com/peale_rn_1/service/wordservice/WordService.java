package com.peale_rn_1.service.wordservice;

import com.peale_rn_1.MainApplication;
import com.peale_rn_1.dao.WordDAO;
import com.peale_rn_1.model.Tb_word;

import java.util.List;

/**
 * Created by dell on 2016/9/28.
 */
public class WordService {
    //查找  根据主题和年级查找单词
    public Tb_word[] find(String proTopic, String grade) {
        WordDAO wordDAO=new WordDAO(MainApplication.getContext());
        return wordDAO.find(proTopic, grade);
    }

    //根据传过来的单词查询出年级和主题
    public String[] find(String firstWord){
        WordDAO wordDAO=new WordDAO(MainApplication.getContext());
        return wordDAO.find(firstWord);
    }
}
