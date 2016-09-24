package com.peale_rn_1.dao.userbehaviour;



import com.peale_rn_1.dao.base.DAO;
import com.peale_rn_1.model.UserBehaviour;

import java.util.List;

/**
 * 
 * @author 作者：刘玉婷
 * @version 创建时间：2016年8月21日下午 20:35:00
 *
 */
public interface UserBehaviourDao extends DAO<UserBehaviour> {

	/**
	 * 根据用户ID查询其行为数据
	 * @param userId
	 * @return 行为list
	 */
	public List<UserBehaviour> find(String userId); 

}
