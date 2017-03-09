package com.keye.flatbuffer_master.utils;

import android.view.View;

import com.google.gson.Gson;
import com.keye.flatbuffer_master.flatmodel.Patient;
import com.keye.flatbuffer_master.flatmodel.PatientList;
import com.keye.flatbuffer_master.flatmodel.Report;
import com.keye.flatbuffer_master.jsonmodel.JsonPatient;
import com.keye.flatbuffer_master.jsonmodel.JsonPatientList;
import com.keye.flatbuffer_master.jsonmodel.JsonReport;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.observables.AsyncOnSubscribe;
import rx.schedulers.Schedulers;

/**
 * 生成模拟数据，保存到SD卡中
 * Created by Administrator on 2017-03-09.
 */

public class InitData {

    public Observable<Boolean> saveData() {
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                flatSerializeToFile();
                jsonSerializeToFile();
                subscriber.onNext(true);
            }
        });

    }

    /**
     * =======================flatBuffer序列化========================
     **/
    private void flatSerializeToFile() {
                FlatBufferBuilder builder = new FlatBufferBuilder();
                //reports
                int[] reports = new int[200];
                for (int i = 0; i < 200; i++) {
                    int name = builder.createString("康复报告" + i);
                    int report = Report.createReport(builder, 10001L, name);
                    reports[i] = report;
                }

                //patients
                int[] patients = new int[200];
                for (int i = 0; i < 200; i++) {
                    int reportList = Patient.createReportsVector(builder, reports);
                    int id = builder.createString("10001");
                    int gender = builder.createString("女");
                    int email = builder.createString("232026765@qq.com");
                    int company = builder.createString("无");
                    int patient = Patient.createPatient(builder, id, 1200L + i, gender, email, reportList, company);
                    patients[i] = patient;
                }

                int patientOffset = PatientList.createPatientVector(builder, patients);
                //PatientList
                PatientList.startPatientList(builder);
                PatientList.addPatient(builder, patientOffset);
                int rootPatientList = PatientList.endPatientList(builder);

                PatientList.finishPatientListBuffer(builder, rootPatientList);

                //saveToFile
                Utils.saveToFile(builder, Utils.FLAT_TYPE);
    }

    /**
     * =======================Json序列化========================
     **/
    private void jsonSerializeToFile() {
                //JsonReport
                JsonReport[] jsonReports = new JsonReport[200];
                for (int i = 0; i < 200; i++) {
                    jsonReports[i] = new JsonReport(10001L, "康复报告" + i);
                }

                //patients
                JsonPatient[] jsonPatients = new JsonPatient[200];
                for (int i = 0; i < 200; i++) {
                    jsonPatients[i] = new JsonPatient("10001", 1200L + i, "女", "232026765@qq.com", jsonReports, "无");
                }

                //JsonPatientList
                JsonPatientList jsonPatientList = new JsonPatientList(jsonPatients);
                Gson gson = new Gson();
                String jsonData = gson.toJson(jsonPatientList);

                //saveToFile
                Utils.saveToFile(jsonData, Utils.JSON_TYPE);
    }
}
