package com.peale_rn_1.dao.testpreference;


import com.peale_rn_1.dao.base.DaoSupport;
import com.peale_rn_1.model.TestPreference;

import java.util.List;


public class TestPreferenceDaoImpl extends DaoSupport<TestPreference> implements TestPreferenceDao {

	@Override
	public TestPreference find(String userId, String feature, int featureValue) {
		// TODO Auto-generated method stub
		double[] tpFeedback = new double[3];

        List<TestPreference> tpList = getHt().find("From TestPreference o where o.userId=? and o.feature=? " +
				"and o.featureValue=?", userId,feature,featureValue);
        if(tpList.size() > 0 && tpList != null)
        	{
            	System.out.println("find!");
        	    return tpList.get(0);
        	}
		return null;
	}

	
}
