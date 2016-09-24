package com.peale_rn_1.model;


/**
 * @author 刘玉婷
 * @version 创建时间：2016年6月16日 下午 15:40:52
 *          用户的备选测试单词信息
 */
public class CandidateTestWords {
    private int id;
    private String userId;
    private String word;
    private int difficulty;

    public CandidateTestWords() {
        super();
        // TODO Auto-generated constructor stub
    }

    public CandidateTestWords(String userId, String word, int difficulty) {
        super();
        this.userId = userId;
        this.word = word;
        this.difficulty = difficulty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}
