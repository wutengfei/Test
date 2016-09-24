package com.peale_rn_1.service.userbehaviour;

import com.peale_rn_1.dao.userbehaviour.UserBehaviourDao;
import com.peale_rn_1.model.UserBehaviour;
import java.util.Date;
import java.util.List;

public class UserBehaviourServiceImpl implements UserBehaviourService {

	private UserBehaviourDao userBehaviourDao;
	
	@Override
	public void addUserBehaviour(String userId, String doWhere, String doWhat, Date doWhen) {
		// TODO Auto-generated method stub
     UserBehaviour ub = new UserBehaviour(userId,doWhere,doWhat,doWhen);
     if(ub != null)
    	 userBehaviourDao.save(ub);
	}

	public UserBehaviourDao getUserBehaviourDao() {
		return userBehaviourDao;
	}

	public void setUserBehaviourDao(UserBehaviourDao userBehaviourDao) {
		this.userBehaviourDao = userBehaviourDao;
	}

	@Override
	public List<UserBehaviour> find(String userId) {
		// TODO Auto-generated method stub
		return userBehaviourDao.find(userId);
	}

}
