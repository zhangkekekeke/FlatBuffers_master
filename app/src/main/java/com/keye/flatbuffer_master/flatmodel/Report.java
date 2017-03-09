// automatically generated, do not modify

package com.keye.flatbuffer_master.flatmodel;

import com.keye.flatbuffer_master.utils.FlatBufferBuilder;
import com.keye.flatbuffer_master.utils.Table;

import java.nio.*;
import java.lang.*;
import java.util.*;

public class Report extends Table {
  public static Report getRootAsReport(ByteBuffer _bb) { return getRootAsReport(_bb, new Report()); }
  public static Report getRootAsReport(ByteBuffer _bb, Report obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__init(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public Report __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public long id() { int o = __offset(4); return o != 0 ? bb.getLong(o + bb_pos) : 0; }
  public String name() { int o = __offset(6); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer nameAsByteBuffer() { return __vector_as_bytebuffer(6, 1); }

  public static int createReport(FlatBufferBuilder builder,
      long id,
      int name) {
    builder.startObject(2);
    Report.addId(builder, id);
    Report.addName(builder, name);
    return Report.endReport(builder);
  }

  public static void startReport(FlatBufferBuilder builder) { builder.startObject(2); }
  public static void addId(FlatBufferBuilder builder, long id) { builder.addLong(0, id, 0); }
  public static void addName(FlatBufferBuilder builder, int nameOffset) { builder.addOffset(1, nameOffset, 0); }
  public static int endReport(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
};

