package com.peale_rn_1.dao.userbehaviour;

import dao.base.DaoSupport;
import dao.userbehaviour.UserBehaviourDao;
import entity.UserBehaviour;

import java.util.List;


public class UserBehaviourDaoImpl extends DaoSupport<UserBehaviour> implements UserBehaviourDao {

	@Override
	public List<UserBehaviour> find(String userId) {
		// TODO Auto-generated method stub
		List<UserBehaviour> list = getHt().find("From UserBehaviour o where o.userId=?", userId);
		return list;
	}

}
