package com.peale_rn_1.service.wordservice;

import com.facebook.react.bridge.WritableArray;
import com.peale_rn_1.model.Tb_word;

/**
 * Created by dell on 2016/11/10.
 */
public interface WordService {
    /**
     * 根据主题和年级查找单词
     *
     * @param proTopic 主题
     * @param grade    年级
     * @return 同主题和年级下的单词数组
     */
    public Tb_word[] find(String proTopic, String grade);

    /**
     * 根据传过来的单词查询出年级和主题
     * @param firstWord 测试四元组中的第一道题
     * @return 主题和年级组成的数组
     */
    public String[] find(String firstWord);

    /**
     * 根据单词查询其图片，音频，课文原句
     * @param word
     * @return 数组：单词的图片，音频，课文原句,干扰单词1，干扰单词图片1，干扰单词2，干扰单词图片2
     */
    public WritableArray search(String word);
}
