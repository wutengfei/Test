package com.peale_rn_1.dao.userword;


import com.peale_rn_1.dao.base.DaoSupport;
import com.peale_rn_1.model.UserWord;

import java.util.List;


public class UserWordDaoImpl extends DaoSupport<UserWord> implements UserWordDao {

	@Override
	public List<UserWord> find(String userId, String word) {
		// TODO Auto-generated method stub
		List<UserWord> list = getHt().find("From UserWord o where o.userId=? and o.word=?", userId,word);
		return list;
	}

	@Override
	public List<UserWord> findAll(String userId, String word, int topiclevel) {
		// TODO Auto-generated method stub
		List<UserWord> list = getHt().find("From UserWord o where o.userId=? and o.word=? and o.topicLevel=?",userId,word,topiclevel);
		return list;
	}

	@Override
	public List<UserWord> findlearncount(String word, int topiclevel) {
		// TODO Auto-generated method stub
		List<UserWord> list = getHt().find("From UserWord o where o.word=? and o.topicLevel=?",word,topiclevel);
		return list;
	}

	
}
