package com.peale_rn_1.model;

import java.util.Date;

/**
* @author 刘玉婷
* @version 创建时间：2016年6月30日 下午 12:20:10
* 用户单词学习记录
*/

public class UserWord {
	 private int id;
	 private String userId;
	 private String word;
	 private int topicLevel;
	 private int wordLearn;
	 private int test;
	 private Date time;

	public UserWord() {
	}

	public UserWord(String userId, String word, int topicLevel, int wordLearn, int test, Date time) {

		this.userId = userId;
		this.word = word;
		this.topicLevel = topicLevel;
		this.wordLearn = wordLearn;
		this.test = test;
		this.time = time;
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

	public int getTopicLevel() {
		return topicLevel;
	}

	public void setTopicLevel(int topicLevel) {
		this.topicLevel = topicLevel;
	}

	public int getWordLearn() {
		return wordLearn;
	}

	public void setWordLearn(int wordLearn) {
		this.wordLearn = wordLearn;
	}

	public int getTest() {
		return test;
	}

	public void setTest(int test) {
		this.test = test;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
}
