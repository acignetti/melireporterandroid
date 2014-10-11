package com.groppius.melireport.entities.category;

/**
 * Created by julio on 11/10/14.
 */
public class Category {

    private long category_id;
    private String category_name;
    private String category_description;
    private long category_father;

    public long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(long category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_description() {
        return category_description;
    }

    public void setCategory_description(String category_description) {
        this.category_description = category_description;
    }

    public long getCategory_father() {
        return category_father;
    }

    public void setCategory_father(long category_father) {
        this.category_father = category_father;
    }
}
