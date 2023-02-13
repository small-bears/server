package self.cases.teams.service;

import self.cases.teams.msg.PageData;

import self.cases.teams.entity.Activities;

/**
 * 业务层处理
 * 活动信息
 */
public interface ActivitiesService extends BaseService<Activities, String> {

	/**
	 * 分页查询活动信息信息
	 * @param pageIndex 当前页码
	 * @param pageSize 每页数据量
	 * @param activeName 活动名称
	 * @param teamName 团队名称
	 * @return
	 */	
	public PageData getPageAll(Long pageIndex, Long pageSize, String activeName, String teamName);


	/**
	 * 分页查询指定成员相关活动信息信息
	 * @param pageIndex 当前页码
	 * @param pageSize 每页数据量
	 * @param userId 指定成员ID
	 * @param activeName 活动名称
	 * @param teamName 团队名称
	 * @return
	 */
	public PageData getPageByUserId(Long pageIndex, Long pageSize, String userId, String activeName, String teamName);
}