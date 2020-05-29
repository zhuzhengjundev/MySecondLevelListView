package com.example.zzj;

import java.util.List;

/**
 * --------------------------------------------
 * auther :  Lvfq
 * 2016/11/26 11:56
 * description ：
 * -------------------------------------------
 **/
public class Group {

    private String groupid;
    private String groupName;
    private String groupAllNum;
    private List<Items> items;

    public Group() {

    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupAllNum() {
        return groupAllNum;
    }

    public void setGroupAllNum(String groupAllNum) {
        this.groupAllNum = groupAllNum;
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }
}
