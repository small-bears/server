package self.cases.teams.service;

import self.cases.teams.msg.PageData;

import self.cases.teams.entity.PayLogs;

/**
 * 业务层处理
 * 缴费记录
 */
public interface PayLogsService extends BaseService<PayLogs, String> {

	/**
	 * 团队管理员分页查询缴费记录信息
	 * @param pageIndex 当前页码
	 * @param pageSize 每页数据量
	 * @param userId 用户编号
	 * @param teamName 团队名称
	 * @param userName 用户姓名
	 * @return
	 */
	public PageData getManPageInfo(Long pageIndex, Long pageSize, String userId, String teamName, String userName);

	/**
	 * 分页查询缴费记录信息
	 * @param pageIndex 当前页码
	 * @param pageSize 每页数据量
	 * @param userId 用户编号
	 * @param teamName 团队名称
	 * @param userName 用户姓名
	 * @return
	 */
	public PageData getPageInfo(Long pageIndex, Long pageSize, String userId, String teamName, String userName);
}