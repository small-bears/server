package self.cases.teams.service;

import self.cases.teams.msg.PageData;

import self.cases.teams.entity.Teams;

import java.util.List;

/**
 * 业务层处理
 * 社团信息
 */
public interface TeamsService extends BaseService<Teams, String> {

	/**
	 * 获取全部的社团信息列表
	 * @return
	 */
	public List<Teams> getAll();

	/**
	 * 获取指定管理人员相关的社团列表
	 * @param manId 指定管理员编号
	 * @return
	 */
	public List<Teams> getListByManId(String manId);

	/**
	 * 分页查询社团信息信息
	 * @param pageIndex 当前页码
	 * @param pageSize 每页数据量
	 * @param teams 模糊查询条件
	 * @return
	 */	
	public PageData getPageInfo(Long pageIndex, Long pageSize, Teams teams);
}