package self.cases.teams.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 数据实体类
 * 报名记录
 */
@TableName(value = "active_logs")
public class ActiveLogs implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 记录ID
	 */
	@TableId(value = "id")
	private String id;

		/**
	 * 报名时间
	 */
	@TableField(value = "create_time")
	private String createTime;

		/**
	 * 活动编号
	 */
	@TableField(value = "active_id")
	private String activeId;

		/**
	 * 报名用户
	 */
	@TableField(value = "user_id")
	private String userId;

	
	public String getId(){

		return id;
	}

	public void setId(String id){

		this.id = id;
	}

	
	public String getCreateTime(){

		return createTime;
	}

	public void setCreateTime(String createTime){

		this.createTime = createTime;
	}

	
	public String getActiveId(){

		return activeId;
	}

	public void setActiveId(String activeId){

		this.activeId = activeId;
	}

	
	public String getUserId(){

		return userId;
	}

	public void setUserId(String userId){

		this.userId = userId;
	}

	
	@Override
	public String toString() {

		return "ActiveLogs [id=" + id 
				+ ", createTime=" + createTime
				+ ", activeId=" + activeId
				+ ", userId=" + userId
				+ "]";
	}

}