package com.pomzwj.domain;


import com.deepoove.poi.data.TableRenderData;

import java.io.Serializable;

public class TableData implements Serializable {
    /**
     * 表结构
     */
    TableRenderData table;

    public TableRenderData getTable() {
        return table;
    }

    public void setTable(TableRenderData table) {
        this.table = table;
    }

}
