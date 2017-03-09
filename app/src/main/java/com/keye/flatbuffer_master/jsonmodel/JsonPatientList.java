package com.keye.flatbuffer_master.jsonmodel;

import com.keye.flatbuffer_master.flatmodel.Patient;

/**
 * Created by Administrator on 2017-03-09.
 */

public class JsonPatientList {
    private JsonPatient[] patients;

    public JsonPatientList(JsonPatient[] patients) {
        this.patients = patients;
    }

    public JsonPatient[] getPatients() {
        return patients;

    }

    public void setPatients(JsonPatient[] patients) {
        this.patients = patients;
    }
}
