package ssm.system.entity;

import java.util.List;

public class Tree  {

    private Object id;
    private String text;
    // open,closed
    private String state = "open";
    private List<Tree> children;
    private String iconCls;
    private Object parentId;
    
    private String url;
    
    private Object attributes;  // 自定义属性
    
    
    public Object getId() {
        return id;
    }
    public void setId(Object id) {
        this.id = id;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public List<Tree> getChildren() {
        return children;
    }
    public void setChildren(List<Tree> children) {
        this.children = children;
    }
    public String getIconCls() {
        return iconCls;
    }
    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }
    public Object getParentId() {
        return parentId;
    }
    public void setParentId(Object parentId) {
        this.parentId = parentId;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public Object getAttributes() {
        return attributes;
    }
    public void setAttributes(Object attributes) {
        this.attributes = attributes;
    }
    
}
