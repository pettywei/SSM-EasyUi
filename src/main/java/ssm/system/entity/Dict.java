package ssm.system.entity;
import java.io.Serializable;


/**
 * 
 * 
 * 
 **/
@SuppressWarnings("serial")
public class Dict implements Serializable {

	/**字典主键 无逻辑意义**/
	private Long id;

	/**父id**/
	private Long parentId;

	/**字典代码**/
	private String code;

	/**字典名称**/
	private String name;

	/**排序**/
	private Integer sequence;

	/**状态：0-启用；1-停用**/
	private Integer status;

	/**创建时间**/
	private java.util.Date createTime;



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

	public void setCode(String code){
		this.code = code;
	}

	public String getCode(){
		return this.code;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
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

}
