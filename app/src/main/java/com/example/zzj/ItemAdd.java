package com.example.zzj;

public class ItemAdd {

    private boolean isCheck;
    private String itemid;
    private String itemName;
    private String itemAllNum;

    public ItemAdd(boolean isCheck, String itemid, String itemName, String itemAllNum) {
        this.isCheck = isCheck;
        this.itemid = itemid;
        this.itemName = itemName;
        this.itemAllNum = itemAllNum;
    }

    public boolean getCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemAllNum() {
        return itemAllNum;
    }

    public void setItemAllNum(String itemAllNum) {
        this.itemAllNum = itemAllNum;
    }
}
