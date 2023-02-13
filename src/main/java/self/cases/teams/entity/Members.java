package self.cases.teams.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 数据实体类
 * 成员信息
 */
@TableName(value = "members")
public class Members implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 记录ID
	 */
	@TableId(value = "id")
	private String id;

		/**
	 * 入团时间
	 */
	@TableField(value = "create_time")
	private String createTime;

		/**
	 * 加入社团
	 */
	@TableField(value = "team_id")
	private String teamId;

		/**
	 * 申请用户
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

	
	public String getTeamId(){

		return teamId;
	}

	public void setTeamId(String teamId){

		this.teamId = teamId;
	}

	
	public String getUserId(){

		return userId;
	}

	public void setUserId(String userId){

		this.userId = userId;
	}

	
	@Override
	public String toString() {

		return "Members [id=" + id 
				+ ", createTime=" + createTime
				+ ", teamId=" + teamId
				+ ", userId=" + userId
				+ "]";
	}

}