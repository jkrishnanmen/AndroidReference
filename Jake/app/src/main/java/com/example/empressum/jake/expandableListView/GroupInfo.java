package com.example.empressum.jake.expandableListView;

import java.util.ArrayList;

/**
 * Created by empressum on 17/8/16.
 */
public class GroupInfo {
    private String name;
    private ArrayList<ChildInfo> list= new ArrayList<ChildInfo>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ChildInfo> getProductList() {
        return list;
    }

    public void setProductList(ArrayList<ChildInfo> productlist) {
        this.list = productlist;
    }
}
