package self.cases.teams.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 数据实体类
 * 社团类型
 */
@TableName(value = "team_types")
public class TeamTypes implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 记录ID
	 */
	@TableId(value = "id")
	private String id;

		/**
	 * 类型名称
	 */
	@TableField(value = "name")
	private String name;

		/**
	 * 创建时间
	 */
	@TableField(value = "create_time")
	private String createTime;

	
	public String getId(){

		return id;
	}

	public void setId(String id){

		this.id = id;
	}

	
	public String getName(){

		return name;
	}

	public void setName(String name){

		this.name = name;
	}

	
	public String getCreateTime(){

		return createTime;
	}

	public void setCreateTime(String createTime){

		this.createTime = createTime;
	}

	
	@Override
	public String toString() {

		return "TeamTypes [id=" + id 
				+ ", name=" + name
				+ ", createTime=" + createTime
				+ "]";
	}

}