package com.example.kumarGroup.Dashboard;

public class HomeItemss {
    String item_name;
    String  item_name2;
    int item_img;
    int  id;
    int flag;


    public HomeItemss(String item_name, String item_name2, int item_img, int id, int flag) {
        this.item_name = item_name;
        this.item_name2 = item_name2;
        this.item_img = item_img;
        this.id = id;
        this.flag = flag;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_name2() {
        return item_name2;
    }

    public void setItem_name2(String item_name2) {
        this.item_name2 = item_name2;
    }

    public int getItem_img() {
        return item_img;
    }

    public void setItem_img(int item_img) {
        this.item_img = item_img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "HomeItemss{" +
                "item_name='" + item_name + '\'' +
                ", item_name2='" + item_name2 + '\'' +
                ", item_img=" + item_img +
                ", id=" + id +
                ", flag=" + flag +
                '}';
    }
}
