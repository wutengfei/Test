package com.peale_rn_1.dao.usertest;


import com.peale_rn_1.dao.base.DaoSupport;
import com.peale_rn_1.model.UserTest;

import java.util.List;

public class UserTestDaoImpl implements UserTestDao {

	@Override
	public List<UserTest> find(String userId, int flag) {
		// TODO Auto-generated method stub
		if(flag == 0)
		{
			List<UserTest> list = getHt().find("From UserTest o where o.userId=? and o.totalTimes=0", userId);
			return list;
		}
		else
		{
			List<UserTest> list = getHt().find("From UserTest o where o.userId=? and o.rightTimes=1", userId);
			return list;
		}
	}


}
