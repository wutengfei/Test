package com.peale_rn_1.dao.candidatetestwords;


import com.peale_rn_1.dao.base.DaoSupport;
import com.peale_rn_1.model.CandidateTestWords;

import java.util.List;


public class CandidateTestWordsDaoImpl extends DaoSupport<CandidateTestWords> implements CandidateTestWordsDao {

	@Override
	public CandidateTestWords find(String userId, int difficulty) {
		// TODO Auto-generated method stub
		List<CandidateTestWords> list = getHt().find("From CandidateTestWords o where o.userId=? and o.difficulty=?", userId,difficulty);
		if(list != null && list.size() > 0)
		{
			this.delete(list.get(0).getId());
			return list.get(0);
		}
		return null;
	}

	@Override
	public CandidateTestWords find(String userId, String word) {
		// TODO Auto-generated method stub
		List<CandidateTestWords> list = getHt().find("From CandidateTestWords o where o.userId=? and o.word=?", userId,word);
		if(list != null && list.size() > 0) {
			
			System.out.println("存在！");
			System.out.println(list.get(0).getUserId() + "/" + list.get(0).getWord());
			return list.get(0);
		}
	    return null;
	}

}
