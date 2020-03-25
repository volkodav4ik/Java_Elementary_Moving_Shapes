package com.volkodav4ik.save;

import java.util.List;

public class Save {

    private List<SaveShape> list;

    public Save(List<SaveShape> saveList) {
        this.list = saveList;
    }

    public List<SaveShape> getList() {
        return list;
    }
}
