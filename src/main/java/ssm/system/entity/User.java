package ssm.system.entity;
import java.io.Serializable;


/**
 * 
 * 后台用户表
 * 
 **/
@SuppressWarnings("serial")
public class User implements Serializable {

	/****/
	private Long id;

	/**帐号**/
	private String loginName;

	/**密码**/
	private String password;

	/**正式名称**/
	private String realName;

	/**角色ID**/
	private String roleIds;

	/**状态：0-启用；1-停用**/
	private Integer status;

	/**创建时间**/
	private java.util.Date createTime;

	/**用户密码修改时间**/
	private java.util.Date updateTime;



	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}

	public void setLoginName(String loginName){
		this.loginName = loginName;
	}

	public String getLoginName(){
		return this.loginName;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return this.password;
	}

	public void setRealName(String realName){
		this.realName = realName;
	}

	public String getRealName(){
		return this.realName;
	}

	public void setRoleIds(String roleIds){
		this.roleIds = roleIds;
	}

	public String getRoleIds(){
		return this.roleIds;
	}

	public void setStatus(Integer status){
		this.status = status;
	}

	public Integer getStatus(){
		return this.status;
	}

	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}

	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}

	public java.util.Date getUpdateTime(){
		return this.updateTime;
	}

}
