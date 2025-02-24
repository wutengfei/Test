package com.peale_rn_1.dao.userword;


import com.peale_rn_1.model.UserWord;

import java.text.ParseException;
import java.util.List;


/**
 * 
 * @author 作者：刘玉婷
 * @version 创建时间：2016年6月30日下午 12:25:00
 * 
 */
public interface UserWordDao {

	/**
	 * 查询指定用户和指定单词的学习记录
	 * @param userId
	 * @param word
	 * @return 
	 */
	public List<UserWord> find(String userId, String word) throws ParseException;
	
	/**
	 * 查询指定用户和指定级别的单词的学习记录
	 * @param userId
	 * @param word
	 * @param topiclevel
	 * @return 
	 */
	public List<UserWord> findAll(String userId, String word, int topiclevel) throws ParseException;
	
	/**
	 * 查询指定级别的单词的被学习记录
	 * @param word
	 * @param topiclevel
	 * @return
	 */
	public List<UserWord> findlearncount(String word, int topiclevel) throws ParseException;
	
}
