/*
 *    Copyright (C) 2016 Amit Shekhar
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.keye.flatbuffer_master.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by amitshekhar on 17/04/16.
 */
public class Utils {
    public final static String flatFileName = "PatientList.bin";
    public final static String jsonFileName = "JsonPatientList.txt";
    public final static int FLAT_TYPE = 0;
    public final static int JSON_TYPE = 1;

    public static byte[] readDataResource(String fileName) {
        FileInputStream stream = null;
        byte[] buffer = null;
        File sdcard = Environment.getExternalStorageDirectory();
        File file = new File(sdcard, fileName);
        if (file.exists()) {
            try {
                stream = new FileInputStream(file);
                buffer = new byte[stream.available()];
                while (stream.read(buffer) != -1) ;
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (stream != null) {
                    try {
                        stream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return buffer;
    }

    /**
     * ====================保存数据到文件=============================================
     **/
    public static void saveToFile(Object builder, int type) {
        ByteBuffer data = null;
        File sdcard = Environment.getExternalStorageDirectory();
        //保存路径.
        File file = null;
        if (type == FLAT_TYPE) {
            file = new File(sdcard, flatFileName);
            data = ((FlatBufferBuilder) builder).dataBuffer();
        } else if (type == JSON_TYPE) {
            file = new File(sdcard, jsonFileName);
            data = ByteBuffer.wrap(String.valueOf(builder).getBytes());
        }
        if (file.exists()) {
            file.delete();
        }
        FileOutputStream out = null;
        FileChannel channel = null;

        try {
            out = new FileOutputStream(file);
            channel = out.getChannel();
            while (data.hasRemaining()) {
                channel.write(data);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (channel != null) {
                    channel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
