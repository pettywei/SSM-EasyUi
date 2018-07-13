package ssm.system.entity;
import java.io.Serializable;


/**
 * 
 * 后台角色权限表
 * 
 **/
@SuppressWarnings("serial")
public class Role implements Serializable {

	/****/
	private Long id;

	/**角色名**/
	private String name;

	/**权限ID**/
	private String menuIds;

	/**状态：0-启用；1-停用**/
	private Integer status;

	/**创建时间**/
	private java.util.Date createTime;

	/**修改时间**/
	private java.util.Date updateTime;



	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	public void setMenuIds(String menuIds){
		this.menuIds = menuIds;
	}

	public String getMenuIds(){
		return this.menuIds;
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
