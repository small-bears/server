package self.cases.teams.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 数据实体类
 * 通知记录
 */
@TableName(value = "notices")
public class Notices implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 记录ID
	 */
	@TableId(value = "id")
	private String id;

		/**
	 * 通知标题
	 */
	@TableField(value = "title")
	private String title;

		/**
	 * 通知详情
	 */
	@TableField(value = "detail")
	private String detail;

		/**
	 * 发布时间
	 */
	@TableField(value = "create_time")
	private String createTime;

		/**
	 * 发布社团
	 */
	@TableField(value = "team_id")
	private String teamId;

	
	public String getId(){

		return id;
	}

	public void setId(String id){

		this.id = id;
	}

	
	public String getTitle(){

		return title;
	}

	public void setTitle(String title){

		this.title = title;
	}

	
	public String getDetail(){

		return detail;
	}

	public void setDetail(String detail){

		this.detail = detail;
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

	
	@Override
	public String toString() {

		return "Notices [id=" + id 
				+ ", title=" + title
				+ ", detail=" + detail
				+ ", createTime=" + createTime
				+ ", teamId=" + teamId
				+ "]";
	}

}