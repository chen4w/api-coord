// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: apicoord.proto

package examples.apicoord;

public interface Req_ri_reOrBuilder extends
    // @@protoc_insertion_point(interface_extends:apicoord.Req_ri_re)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *请求头
   * </pre>
   *
   * <code>.apicoord.Header bh = 1;</code>
   * @return Whether the bh field is set.
   */
  boolean hasBh();
  /**
   * <pre>
   *请求头
   * </pre>
   *
   * <code>.apicoord.Header bh = 1;</code>
   * @return The bh.
   */
  examples.apicoord.Header getBh();
  /**
   * <pre>
   *请求头
   * </pre>
   *
   * <code>.apicoord.Header bh = 1;</code>
   */
  examples.apicoord.HeaderOrBuilder getBhOrBuilder();

  /**
   * <pre>
   *请求的目标账户集合
   * </pre>
   *
   * <code>repeated string accounts = 2;</code>
   * @return A list containing the accounts.
   */
  java.util.List<java.lang.String>
      getAccountsList();
  /**
   * <pre>
   *请求的目标账户集合
   * </pre>
   *
   * <code>repeated string accounts = 2;</code>
   * @return The count of accounts.
   */
  int getAccountsCount();
  /**
   * <pre>
   *请求的目标账户集合
   * </pre>
   *
   * <code>repeated string accounts = 2;</code>
   * @param index The index of the element to return.
   * @return The accounts at the given index.
   */
  java.lang.String getAccounts(int index);
  /**
   * <pre>
   *请求的目标账户集合
   * </pre>
   *
   * <code>repeated string accounts = 2;</code>
   * @param index The index of the value to return.
   * @return The bytes of the accounts at the given index.
   */
  com.google.protobuf.ByteString
      getAccountsBytes(int index);
}
