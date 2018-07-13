package ssm.system.util;

import java.util.ArrayList;
import java.util.List;

import ssm.system.entity.Menu;
import ssm.system.entity.Tree;


public class TreeUtils {

    /**
     * 获取树节点
     * @param treeDataList
     * @return
     */
    public final static List<Tree> formatNode(List<Tree> treeDataList) {
        List<Tree> newTreeDataList = new ArrayList<Tree>();
        for (Tree tree : treeDataList) {
            if(tree.getParentId() == null || "0".equals(tree.getParentId().toString())) {
                //获取父节点下的子节点
                tree.setChildren(getChildrenNode(tree.getId(),treeDataList));
                newTreeDataList.add(tree);
            }
        }
        return newTreeDataList;
    }
    
    /**
     * 递归获取子节点
     * @param parentId
     * @param treeDataList
     * @return
     */
    private final static List<Tree> getChildrenNode(Object parentId , List<Tree> treeDataList) {
        List<Tree> newTreeDataList = new ArrayList<Tree>();
        for (Tree tree : treeDataList) {
            if(tree.getParentId() == null || "0".equals(tree.getParentId().toString()))  continue;
            //这是一个子节点
            if(tree.getParentId().equals(parentId)){
                //递归获取子节点下的子节点
                tree.setChildren(getChildrenNode(tree.getId() , treeDataList));
                newTreeDataList.add(tree);
            }
        }
        return newTreeDataList;
    }
    
    
    /**
     * 格式化菜单树
     * @param menus
     * @return
     */
    public final static List<Tree> formatMenuTree(List<Menu> menus){
        List<Tree> treeList = new ArrayList<Tree>();
        for(Menu menu : menus){
            Tree tree = new Tree();
            tree.setId(menu.getId());
            tree.setText(menu.getName());
            tree.setIconCls(menu.getIcon());
            tree.setParentId(menu.getParentId());
            tree.setUrl(menu.getUrl());
            // open,closed
            tree.setState("open");
            // attributes 用于状态
            tree.setAttributes(menu.getStatus());
            treeList.add(tree);
        }
        
        return formatNode(treeList);
    }
    
}
