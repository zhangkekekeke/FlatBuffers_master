// automatically generated, do not modify

package com.keye.flatbuffer_master.flatmodel;

import com.keye.flatbuffer_master.utils.FlatBufferBuilder;
import com.keye.flatbuffer_master.utils.Table;

import java.nio.*;
import java.lang.*;
import java.util.*;

public class PatientList extends Table {
  public static PatientList getRootAsPatientList(ByteBuffer _bb) { return getRootAsPatientList(_bb, new PatientList()); }
  public static PatientList getRootAsPatientList(ByteBuffer _bb, PatientList obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__init(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public PatientList __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public Patient patient(int j) { return patient(new Patient(), j); }
  public Patient patient(Patient obj, int j) { int o = __offset(4); return o != 0 ? obj.__init(__indirect(__vector(o) + j * 4), bb) : null; }
  public int patientLength() { int o = __offset(4); return o != 0 ? __vector_len(o) : 0; }

  public static int createPatientList(FlatBufferBuilder builder,
      int patient) {
    builder.startObject(1);
    PatientList.addPatient(builder, patient);
    return PatientList.endPatientList(builder);
  }

  public static void startPatientList(FlatBufferBuilder builder) { builder.startObject(1); }
  public static void addPatient(FlatBufferBuilder builder, int patientOffset) { builder.addOffset(0, patientOffset, 0); }
  public static int createPatientVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addOffset(data[i]); return builder.endVector(); }
  public static void startPatientVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static int endPatientList(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
  public static void finishPatientListBuffer(FlatBufferBuilder builder, int offset) { builder.finish(offset); }
};

