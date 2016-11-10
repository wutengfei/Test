package com.peale_rn_1.dao.candidatetestwords;


import com.peale_rn_1.model.CandidateTestWords;

/**
 * 
 * @author 作者：刘玉婷
 * @version 创建时间：2016年6月16日下午 15:59:00
 *
 */
public interface CandidateTestWordsDao  {
	/**
	 *
	 * @param userId
	 * @return
     */
	public CandidateTestWords[] find(String userId);
	/**
	 * 获取指定用户指定难度的备考内容 并移除表中
	 * @param userId
	 * @param difficulty
	 * @return
	 */
	public CandidateTestWords find(String userId, int difficulty);
	
	/**
	 * 查询用户备考单词表中指定用户和指定单词的记录
	 * @param userId
	 * @param word
	 * @return
	 */
	public CandidateTestWords find(String userId, String word);

	/**
	 * 保存候选测试单词
	 * @param candidateTestWords
     */
	public void save(CandidateTestWords candidateTestWords);
}
