package self.cases.teams.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 数据实体类
 * 社团信息
 */
@TableName(value = "teams")
public class Teams implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 记录ID
	 */
	@TableId(value = "id")
	private String id;

		/**
	 * 社团名称
	 */
	@TableField(value = "name")
	private String name;

		/**
	 * 建立时间
	 */
	@TableField(value = "create_time")
	private String createTime;

		/**
	 * 社团人数
	 */
	@TableField(value = "total")
	private Integer total;

		/**
	 * 社团团长
	 */
	@TableField(value = "manager")
	private String manager;

		/**
	 * 社团编号
	 */
	@TableField(value = "type_id")
	private String typeId;

	
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

	
	public Integer getTotal(){

		return total;
	}

	public void setTotal(Integer total){

		this.total = total;
	}

	
	public String getManager(){

		return manager;
	}

	public void setManager(String manager){

		this.manager = manager;
	}

	
	public String getTypeId(){

		return typeId;
	}

	public void setTypeId(String typeId){

		this.typeId = typeId;
	}

	
	@Override
	public String toString() {

		return "Teams [id=" + id 
				+ ", name=" + name
				+ ", createTime=" + createTime
				+ ", total=" + total
				+ ", manager=" + manager
				+ ", typeId=" + typeId
				+ "]";
	}

}