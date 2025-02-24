package com.peale_rn_1.service.testpreference;


import java.text.ParseException;

/**用户测试偏好
 * @author 刘玉婷
 * @version 创建时间：2016年6月20日 下午17:04:10
 */
public interface TestPreferenceService {

	/**
	 * 根据用户ID去更新用户对各测试特征的偏好
	 * @param userId
	 */
	public void updateTestPreference(String userId) throws ParseException;
	
	/**
	 * 添加指定用户的测试偏好数据的记录（默认偏好值）
	 * @param userId
	 */
	public void addTestPreference(String userId);
	
	/**
	 * 计算指定用户对指定测试类型<题型、考察方面、难度>的擅长度
	 * @param userId
	 * @param testType 题型
	 * @param testAspect 考察方面
	 * @param testDifficulty 难度
	 * @return 擅长度
	 */
	public double recommendByPreference(String userId, int testType, int testAspect, int testDifficulty);
   
	
}
