package com.keye.flatbuffer_master.jsonmodel;

import com.keye.flatbuffer_master.flatmodel.Report;

import static android.R.attr.id;

/**
 * Created by Administrator on 2017-03-09.
 */

public class JsonPatient {
//    id:string;
//    index:long;
//    gender:string;
//    email:string;
//    reports:[Report];
//    company:string;

    private String id;
    private long index;
    private String gender;

    public JsonPatient(String id, long index, String gender, String email, JsonReport[] jsonReports, String company) {
        this.id = id;
        this.index = index;
        this.gender = gender;
        this.email = email;
        this.jsonReports = jsonReports;
        this.company = company;
    }

    private String email;
    private JsonReport[] jsonReports;
    private String company;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getIndex() {
        return index;
    }

    public void setIndex(long index) {
        this.index = index;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public JsonReport[] getJsonReports() {
        return jsonReports;
    }

    public void setJsonReports(JsonReport[] jsonReports) {
        this.jsonReports = jsonReports;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
