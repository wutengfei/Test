package com.peale_rn_1.dao.usertest;



import com.peale_rn_1.dao.base.DAO;
import com.peale_rn_1.model.UserTest;

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
	 * @return
	 */
	public List<UserTest> find(String userId, int flag);
}
