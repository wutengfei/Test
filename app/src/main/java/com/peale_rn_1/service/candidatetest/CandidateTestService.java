package com.peale_rn_1.service.candidatetest;

/**
 * @author 刘玉婷
 * @version 创建时间：2016年6月16日晚上20:13:00
 */
public interface CandidateTestService {


	public int GetCandidateDCount(String userId, int difficulty);

	/**
	 * 新增一条用户测试类型信息
	 * @param userId
	 * @param testType
	 * @param testAspect
	 * @param testDifficulty
	 * @param candidate
	 */
	public void AddCandidateTest(String userId, int testType, int testAspect, int testDifficulty, int candidate);

	/**
	 * 初始化用户备选测试类型
	 * @param userId
	 */
	public void AddBatchCandidateTest(String userId);
	/**
	 * 指定测试类型信息更改：一次通过/两次通过/三次及三次以上通过的数量加1
	 * @param userId
	 * @param testType
	 * @param testAspect
	 * @param testDifficulty
	 * @param passtime 1：一次通过，2:两次通过，3：三次及三次以上通过
	 */
	public void UpdateCandidateTestCount(String userId, int testType, int testAspect, int testDifficulty, int passtime);
    
	
	public void UpdateCandidateStatus(String userId, int testType, int testAspect, int testDifficulty);
	

}
