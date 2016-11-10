package com.peale_rn_1.dao.usertest;

import com.peale_rn_1.model.UserTest;

import java.text.ParseException;
import java.util.List;


/**
 * 
 * @author 作者：刘玉婷
 * @version 创建时间：2016年6月16日下午 15:52:00
 *
 */
public interface UserTestDao  {
	/**
	 * 查询指定用户的测试数据
	 * @param userId
	 * @param flag 1:测试过的数据 0：还未测试的数据
	 * @return UserTest数组
	 */
	public List<UserTest> find(String userId, int flag) throws ParseException;

	/**
	 * 查找user_test中指定userId和word的数据
	 * @param userId
	 * @param word
	 * @return UserTest数组
	 * @throws ParseException
     */
	public List<UserTest> find(String userId, String word)throws ParseException;

	/**
	 * 保存：插入一条userTest数据
	 * @param userTest
     */
	public void save(UserTest userTest);

	/**
	 * 更新
	 * @param userTest
     */
	public void update(UserTest userTest);

	/**
	 * 删除
	 * @param id
     */
	public void delete(int id);
}
