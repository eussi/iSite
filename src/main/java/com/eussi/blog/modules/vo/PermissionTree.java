package com.eussi.blog.modules.vo;


import com.eussi.blog.modules.po.Permission;

import java.util.LinkedList;
import java.util.List;

/**
 * @author - wangxm on 2019/1/30
 */
public class PermissionTree extends Permission {
    private List<PermissionTree> items;

    public List<PermissionTree> getItems() {
        return items;
    }

    public void setItems(List<PermissionTree> items) {
        this.items = items;
    }

    public void addItem(PermissionTree item){
        if(this.items == null){
            this.items = new LinkedList<>();
        }
        this.items.add(item);
    }
}
