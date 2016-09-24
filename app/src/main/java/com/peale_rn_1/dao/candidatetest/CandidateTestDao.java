package com.peale_rn_1.dao.candidatetest;



import com.peale_rn_1.dao.base.DAO;
import com.peale_rn_1.model.CandidateTest;

import java.util.List;


/**
 * 
 * @author 作者：刘玉婷
 * @version 创建时间：2016年6月16日下午 15:55:00
 * 
 */
public interface CandidateTestDao  {
	/**
	 * 获取当前用户为备选状态的测试类型
	 * @param userId
	 * @return
	 */
	public List<CandidateTest> getCandidate(String userId);
	
	/**
	 * 获取当前用户指定测试类型的对象
	 * @param userId
	 * @param testType
	 * @param testAspect
	 * @param testDifficulty
	 * @return
	 */
	public CandidateTest find(String userId, int testType, int testAspect, int testDifficulty);
}
