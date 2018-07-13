package ssm.system.entity;
import java.io.Serializable;


/**
 * 
 * 
 * 
 **/
@SuppressWarnings("serial")
public class Menu implements Serializable {

	/****/
	private Long id;

	/****/
	private Long parentId;

	/****/
	private String name;

	/****/
	private String url;

	/****/
	private String icon;

	/****/
	private Integer sequence;

	/**状态：0-启用；1-停用**/
	private Integer status;

	/****/
	private java.util.Date createTime;

	/****/
	private java.util.Date updateTime;



	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}

	public void setParentId(Long parentId){
		this.parentId = parentId;
	}

	public Long getParentId(){
		return this.parentId;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return this.url;
	}

	public void setIcon(String icon){
		this.icon = icon;
	}

	public String getIcon(){
		return this.icon;
	}

	public void setSequence(Integer sequence){
		this.sequence = sequence;
	}

	public Integer getSequence(){
		return this.sequence;
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
