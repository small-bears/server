package self.cases.teams.service;

import self.cases.teams.msg.PageData;

import self.cases.teams.entity.Notices;

import java.util.List;
import java.util.Map;

/**
 * 业务层处理
 * 通知记录
 */
public interface NoticesService extends BaseService<Notices, String> {

	/**
	 * 获取系统发布的通知信息
	 * @return
	 */
	public List<Notices> getSysNotices();

	/**
	 * 获取社团管理员相关的通知信息
	 * @param manId 社团管理员ID
	 * @return
	 */
	public List<Notices> getManNotices(String manId);

	/**
	 * 获取社团成员相关通知信息
	 * @param memId 社团成员ID
	 * @return
	 */
	public List<Notices> getMemNotices(String memId);

	/**
	 * 分页查询通知记录信息
	 * @param pageIndex 当前页码
	 * @param pageSize 每页数据量
	 * @param title 通知标题
	 * @param teamName 社团名称
	 * @return
	 */	
	public PageData getPageAll(Long pageIndex, Long pageSize, String title, String teamName);

	/**
	 * 分页查询指定用户相关通知记录信息
	 * @param pageIndex 当前页码
	 * @param pageSize 每页数据量
	 * @param userId 用户ID
	 * @param title 通知标题
	 * @param teamName 社团名称
	 * @return
	 */
	public PageData getPageById(Long pageIndex, Long pageSize, String userId, String title, String teamName);
}