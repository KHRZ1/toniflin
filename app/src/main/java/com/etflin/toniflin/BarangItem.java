package com.etflin.toniflin;

public class BarangItem {
    public String itemName, itemTf, itemAmmount, itemStock, itemCode, itemModal;
    public boolean itemLock;

    public BarangItem(String itemName, String itemTf, String itemAmmount, boolean itemLock){
        this.itemName = itemName;
        this.itemTf = itemTf;
        this.itemAmmount = itemAmmount;
        this.itemLock = itemLock;
    }

    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemTf() {
        return itemTf;
    }
    public void setItemTf(String itemTf) {
        this.itemTf = itemTf;
    }

    public String getItemAmmount() {
        return itemAmmount;
    }
    public void setItemAmmount(String itemAmmount) {
        this.itemAmmount = itemAmmount;
    }

    public boolean getItemLock() {
        return itemLock;
    }
    public void setItemLock(boolean itemLock) {
        this.itemLock = itemLock;
    }

    public String getItemCode() {
        return itemCode;
    }
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemModal() {
        return itemModal;
    }
    public void setItemModal(String itemModal) {
        this.itemModal = itemModal;
    }
}
