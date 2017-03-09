package com.keye.flatbuffer_master.jsonmodel;

import static android.R.attr.id;
import static android.R.attr.name;

/**
 * Created by Administrator on 2017-03-09.
 */

public class JsonReport {
//    id:long;
//    name:string;
    private long id;
    private String name;

    public JsonReport(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
