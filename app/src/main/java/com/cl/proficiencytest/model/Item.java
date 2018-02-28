package com.cl.proficiencytest.model;

import java.util.List;

/**
 * Created by tymon on 28/02/2018.
 */

public class Item {
    private String title;
    private List<Row> rows;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }
}
