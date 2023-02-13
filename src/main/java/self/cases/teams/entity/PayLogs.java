package self.cases.teams.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 数据实体类
 * 缴费记录
 */
@TableName(value = "pay_logs")
public class PayLogs implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 记录ID
	 */
	@TableId(value = "id")
	private String id;

		/**
	 * 缴费时间
	 */
	@TableField(value = "create_time")
	private String createTime;

		/**
	 * 缴纳费用
	 */
	@TableField(value = "total")
	private Double total;

		/**
	 * 收费社团
	 */
	@TableField(value = "team_id")
	private String teamId;

		/**
	 * 缴费用户
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

	
	public Double getTotal(){

		return total;
	}

	public void setTotal(Double total){

		this.total = total;
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

		return "PayLogs [id=" + id 
				+ ", createTime=" + createTime
				+ ", total=" + total
				+ ", teamId=" + teamId
				+ ", userId=" + userId
				+ "]";
	}

}