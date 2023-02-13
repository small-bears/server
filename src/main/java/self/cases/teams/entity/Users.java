package self.cases.teams.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 数据实体类
 * 系统用户
 */
@TableName(value = "users")
public class Users implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 记录ID
	 */
	@TableId(value = "id")
	private String id;

		/**
	 * 用户账号
	 */
	@TableField(value = "user_name")
	private String userName;

		/**
	 * 用户密码
	 */
	@TableField(value = "pass_word")
	private String passWord;

		/**
	 * 用户姓名
	 */
	@TableField(value = "name")
	private String name;

		/**
	 * 用户性别
	 */
	@TableField(value = "gender")
	private String gender;

		/**
	 * 用户年龄
	 */
	@TableField(value = "age")
	private Integer age;

		/**
	 * 联系电话
	 */
	@TableField(value = "phone")
	private String phone;

		/**
	 * 联系地址
	 */
	@TableField(value = "address")
	private String address;

		/**
	 * 信息状态
	 */
	@TableField(value = "status")
	private Integer status;

		/**
	 * 添加时间
	 */
	@TableField(value = "create_time")
	private String createTime;

		/**
	 * 用户身份
	 */
	@TableField(value = "type")
	private Integer type;

	
	public String getId(){

		return id;
	}

	public void setId(String id){

		this.id = id;
	}

	
	public String getUserName(){

		return userName;
	}

	public void setUserName(String userName){

		this.userName = userName;
	}

	
	public String getPassWord(){

		return passWord;
	}

	public void setPassWord(String passWord){

		this.passWord = passWord;
	}

	
	public String getName(){

		return name;
	}

	public void setName(String name){

		this.name = name;
	}

	
	public String getGender(){

		return gender;
	}

	public void setGender(String gender){

		this.gender = gender;
	}

	
	public Integer getAge(){

		return age;
	}

	public void setAge(Integer age){

		this.age = age;
	}

	
	public String getPhone(){

		return phone;
	}

	public void setPhone(String phone){

		this.phone = phone;
	}

	
	public String getAddress(){

		return address;
	}

	public void setAddress(String address){

		this.address = address;
	}

	
	public Integer getStatus(){

		return status;
	}

	public void setStatus(Integer status){

		this.status = status;
	}

	
	public String getCreateTime(){

		return createTime;
	}

	public void setCreateTime(String createTime){

		this.createTime = createTime;
	}

	
	public Integer getType(){

		return type;
	}

	public void setType(Integer type){

		this.type = type;
	}

	
	@Override
	public String toString() {

		return "Users [id=" + id 
				+ ", userName=" + userName
				+ ", passWord=" + passWord
				+ ", name=" + name
				+ ", gender=" + gender
				+ ", age=" + age
				+ ", phone=" + phone
				+ ", address=" + address
				+ ", status=" + status
				+ ", createTime=" + createTime
				+ ", type=" + type
				+ "]";
	}

}