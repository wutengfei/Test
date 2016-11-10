package com.peale_rn_1.dao.testpreference;


import com.peale_rn_1.model.TestPreference;

/**
 * 
 * @author 作者：刘玉婷
 * @version 创建时间：2016年6月16日下午 15:47:00
 *
 */
public interface TestPreferenceDao {

	/**
	 * 查找指定用户指定测试特征指定取值的偏好
	 * @param userId
	 * @param feature
	 * @param featureValue
	 * @return
	 */
	public TestPreference find(String userId, String feature, int featureValue);

	/**
	 * 保存
	 * @param testPreference
     */
	public void save(TestPreference testPreference);

	/**
	 * 更新
	 * @param testPreference
     */
	public void update(TestPreference testPreference);
}
