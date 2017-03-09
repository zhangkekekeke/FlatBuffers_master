package com.keye.flatbuffer_master;

import android.databinding.DataBindingUtil;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.jakewharton.rxbinding.view.RxView;
import com.keye.flatbuffer_master.databinding.ActivityMainBinding;
import com.keye.flatbuffer_master.flatmodel.Patient;
import com.keye.flatbuffer_master.flatmodel.PatientList;
import com.keye.flatbuffer_master.flatmodel.Report;
import com.keye.flatbuffer_master.jsonmodel.JsonPatient;
import com.keye.flatbuffer_master.jsonmodel.JsonPatientList;
import com.keye.flatbuffer_master.jsonmodel.JsonReport;
import com.keye.flatbuffer_master.utils.FlatBufferBuilder;
import com.keye.flatbuffer_master.utils.InitData;
import com.keye.flatbuffer_master.utils.Utils;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static com.keye.flatbuffer_master.flatmodel.Patient.createPatient;
import static com.keye.flatbuffer_master.flatmodel.Patient.createReportsVector;
import static com.keye.flatbuffer_master.flatmodel.PatientList.createPatientVector;
import static com.keye.flatbuffer_master.flatmodel.PatientList.endPatientList;
import static com.keye.flatbuffer_master.flatmodel.Report.createReport;

/**
 * Name:Keye
 * QQ:232026765
 */
public class MainActivity extends RxAppCompatActivity {
    private ActivityMainBinding binding;
    private InitData initData;
    private String TAG = "KEYE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        //业务类，初始化数据
        initData = new InitData();
        //初始化监听器
        initListener();

    }

    private void initListener() {
        RxView.clicks(binding.saveBtn)
                .throttleFirst(300, TimeUnit.MILLISECONDS)
                .doOnNext(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        binding.refreshLayout.setRefreshing(true);
                        binding.saveBtn.setEnabled(false);
                    }
                })
                .compose(this.<Void>bindToLifecycle())
                .observeOn(Schedulers.io())
                .flatMap(new Func1<Void, Observable<Boolean>>() {
                    @Override
                    public Observable<Boolean> call(Void aVoid) {
                        return initData.saveData();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        binding.refreshLayout.setRefreshing(false);
                        binding.saveBtn.setEnabled(true);
                    }
                });

        RxView.clicks(binding.flatBtn)
                .throttleFirst(300, TimeUnit.MILLISECONDS)
                .compose(this.<Void>bindToLifecycle())
                .doOnNext(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        binding.refreshLayout.setRefreshing(true);
                        binding.flatBtn.setEnabled(false);
                    }
                })
                .observeOn(Schedulers.io())
                .map(new Func1<Void, String>() {
                    @Override
                    public String call(Void aVoid) {
                        return loadFromFlatBuffer();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        binding.flatTv.setText(data);
                        binding.refreshLayout.setRefreshing(false);
                        binding.flatBtn.setEnabled(true);
                    }
                });

        RxView.clicks(binding.jsonBtn)
                .throttleFirst(300, TimeUnit.MILLISECONDS)
                .compose(this.<Void>bindToLifecycle())
                .doOnNext(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        binding.refreshLayout.setRefreshing(true);
                        binding.jsonBtn.setEnabled(false);
                    }
                })
                .observeOn(Schedulers.io())
                .map(new Func1<Void, String>() {
                    @Override
                    public String call(Void aVoid) {
                        return loadFromJson();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        binding.jsonTv.setText(data);
                        binding.refreshLayout.setRefreshing(false);
                        binding.jsonBtn.setEnabled(true);
                    }
                });
    }


    public String loadFromFlatBuffer() {
        byte[] buffer = Utils.readDataResource(Utils.flatFileName);
        String data;
        if (buffer != null) {
            long startTime = System.currentTimeMillis();
            ByteBuffer bb = ByteBuffer.wrap(buffer);
            PatientList patientList = PatientList.getRootAsPatientList(bb);
            long timeTaken = System.currentTimeMillis() - startTime;
            data = "loadFromFlat: " + timeTaken + "ms";
            Log.d(TAG, "最后一个的patient的report:" + patientList.patient(patientList.patientLength()-1).reports(patientList.patientLength()-1).name());
        } else {
            data = getString(R.string.notfoundFlat);
        }
        return data;
    }


    public String loadFromJson() {
        byte[] buffer = Utils.readDataResource(Utils.jsonFileName);
        String data;
        if (buffer != null) {
            String jsonText = new String(buffer);
            long startTime = System.currentTimeMillis();
            JsonPatientList jsonPatientList = new Gson().fromJson(jsonText, JsonPatientList.class);
            long timeTaken = System.currentTimeMillis() - startTime;
            data = "loadFromJson: " + timeTaken + "ms";
        } else {
            data = getString(R.string.notfoundJson);
        }
        return data;
    }


}
