// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: datatrans.proto

package examples.datatrans;

public final class DataTransProto {
  private DataTransProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_datatrans_DataTransRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_datatrans_DataTransRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_datatrans_Data_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_datatrans_Data_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\017datatrans.proto\022\tdatatrans\"\037\n\020DataTran" +
      "sRequest\022\013\n\003url\030\001 \001(\t\"\024\n\004Data\022\014\n\004byte\030\001 " +
      "\001(\0142M\n\tDataTrans\022@\n\014Transmission\022\033.datat" +
      "rans.DataTransRequest\032\017.datatrans.Data\"\000" +
      "0\001B&\n\022examples.datatransB\016DataTransProto" +
      "P\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_datatrans_DataTransRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_datatrans_DataTransRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_datatrans_DataTransRequest_descriptor,
        new java.lang.String[] { "Url", });
    internal_static_datatrans_Data_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_datatrans_Data_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_datatrans_Data_descriptor,
        new java.lang.String[] { "Byte", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
