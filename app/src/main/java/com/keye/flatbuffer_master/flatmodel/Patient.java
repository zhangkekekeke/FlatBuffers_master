// automatically generated, do not modify

package com.keye.flatbuffer_master.flatmodel;

import com.keye.flatbuffer_master.utils.FlatBufferBuilder;
import com.keye.flatbuffer_master.utils.Table;

import java.nio.*;
import java.lang.*;

public class Patient extends Table {
  public static Patient getRootAsPatient(ByteBuffer _bb) { return getRootAsPatient(_bb, new Patient()); }
  public static Patient getRootAsPatient(ByteBuffer _bb, Patient obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__init(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public Patient __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public String id() { int o = __offset(4); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer idAsByteBuffer() { return __vector_as_bytebuffer(4, 1); }
  public long index() { int o = __offset(6); return o != 0 ? bb.getLong(o + bb_pos) : 0; }
  public String gender() { int o = __offset(8); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer genderAsByteBuffer() { return __vector_as_bytebuffer(8, 1); }
  public String email() { int o = __offset(10); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer emailAsByteBuffer() { return __vector_as_bytebuffer(10, 1); }
  public Report reports(int j) { return reports(new Report(), j); }
  public Report reports(Report obj, int j) { int o = __offset(12); return o != 0 ? obj.__init(__indirect(__vector(o) + j * 4), bb) : null; }
  public int reportsLength() { int o = __offset(12); return o != 0 ? __vector_len(o) : 0; }
  public String company() { int o = __offset(14); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer companyAsByteBuffer() { return __vector_as_bytebuffer(14, 1); }

  public static int createPatient(FlatBufferBuilder builder,
      int id,
      long index,
      int gender,
      int email,
      int reports,
      int company) {
    builder.startObject(6);
    Patient.addIndex(builder, index);
    Patient.addCompany(builder, company);
    Patient.addReports(builder, reports);
    Patient.addEmail(builder, email);
    Patient.addGender(builder, gender);
    Patient.addId(builder, id);
    return Patient.endPatient(builder);
  }

  public static void startPatient(FlatBufferBuilder builder) { builder.startObject(6); }
  public static void addId(FlatBufferBuilder builder, int idOffset) { builder.addOffset(0, idOffset, 0); }
  public static void addIndex(FlatBufferBuilder builder, long index) { builder.addLong(1, index, 0); }
  public static void addGender(FlatBufferBuilder builder, int genderOffset) { builder.addOffset(2, genderOffset, 0); }
  public static void addEmail(FlatBufferBuilder builder, int emailOffset) { builder.addOffset(3, emailOffset, 0); }
  public static void addReports(FlatBufferBuilder builder, int reportsOffset) { builder.addOffset(4, reportsOffset, 0); }
  public static int createReportsVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addOffset(data[i]); return builder.endVector(); }
  public static void startReportsVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static void addCompany(FlatBufferBuilder builder, int companyOffset) { builder.addOffset(5, companyOffset, 0); }
  public static int endPatient(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
};

