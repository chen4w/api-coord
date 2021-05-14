// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: apicoord.proto

package examples.apicoord;

/**
 * Protobuf type {@code apicoord.ResultSignature2}
 */
public final class ResultSignature2 extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:apicoord.ResultSignature2)
    ResultSignature2OrBuilder {
private static final long serialVersionUID = 0L;
  // Use ResultSignature2.newBuilder() to construct.
  private ResultSignature2(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ResultSignature2() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new ResultSignature2();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ResultSignature2(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            examples.apicoord.Result.Builder subBuilder = null;
            if (result_ != null) {
              subBuilder = result_.toBuilder();
            }
            result_ = input.readMessage(examples.apicoord.Result.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(result_);
              result_ = subBuilder.buildPartial();
            }

            break;
          }
          case 18: {
            examples.apicoord.Signature.Builder subBuilder = null;
            if (signature_ != null) {
              subBuilder = signature_.toBuilder();
            }
            signature_ = input.readMessage(examples.apicoord.Signature.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(signature_);
              signature_ = subBuilder.buildPartial();
            }

            break;
          }
          case 26: {
            examples.apicoord.Signature.Builder subBuilder = null;
            if (signature2_ != null) {
              subBuilder = signature2_.toBuilder();
            }
            signature2_ = input.readMessage(examples.apicoord.Signature.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(signature2_);
              signature2_ = subBuilder.buildPartial();
            }

            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return examples.apicoord.ApiCoordProto.internal_static_apicoord_ResultSignature2_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return examples.apicoord.ApiCoordProto.internal_static_apicoord_ResultSignature2_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            examples.apicoord.ResultSignature2.class, examples.apicoord.ResultSignature2.Builder.class);
  }

  public static final int RESULT_FIELD_NUMBER = 1;
  private examples.apicoord.Result result_;
  /**
   * <code>.apicoord.Result result = 1;</code>
   * @return Whether the result field is set.
   */
  @java.lang.Override
  public boolean hasResult() {
    return result_ != null;
  }
  /**
   * <code>.apicoord.Result result = 1;</code>
   * @return The result.
   */
  @java.lang.Override
  public examples.apicoord.Result getResult() {
    return result_ == null ? examples.apicoord.Result.getDefaultInstance() : result_;
  }
  /**
   * <code>.apicoord.Result result = 1;</code>
   */
  @java.lang.Override
  public examples.apicoord.ResultOrBuilder getResultOrBuilder() {
    return getResult();
  }

  public static final int SIGNATURE_FIELD_NUMBER = 2;
  private examples.apicoord.Signature signature_;
  /**
   * <code>.apicoord.Signature signature = 2;</code>
   * @return Whether the signature field is set.
   */
  @java.lang.Override
  public boolean hasSignature() {
    return signature_ != null;
  }
  /**
   * <code>.apicoord.Signature signature = 2;</code>
   * @return The signature.
   */
  @java.lang.Override
  public examples.apicoord.Signature getSignature() {
    return signature_ == null ? examples.apicoord.Signature.getDefaultInstance() : signature_;
  }
  /**
   * <code>.apicoord.Signature signature = 2;</code>
   */
  @java.lang.Override
  public examples.apicoord.SignatureOrBuilder getSignatureOrBuilder() {
    return getSignature();
  }

  public static final int SIGNATURE2_FIELD_NUMBER = 3;
  private examples.apicoord.Signature signature2_;
  /**
   * <code>.apicoord.Signature signature2 = 3;</code>
   * @return Whether the signature2 field is set.
   */
  @java.lang.Override
  public boolean hasSignature2() {
    return signature2_ != null;
  }
  /**
   * <code>.apicoord.Signature signature2 = 3;</code>
   * @return The signature2.
   */
  @java.lang.Override
  public examples.apicoord.Signature getSignature2() {
    return signature2_ == null ? examples.apicoord.Signature.getDefaultInstance() : signature2_;
  }
  /**
   * <code>.apicoord.Signature signature2 = 3;</code>
   */
  @java.lang.Override
  public examples.apicoord.SignatureOrBuilder getSignature2OrBuilder() {
    return getSignature2();
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (result_ != null) {
      output.writeMessage(1, getResult());
    }
    if (signature_ != null) {
      output.writeMessage(2, getSignature());
    }
    if (signature2_ != null) {
      output.writeMessage(3, getSignature2());
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (result_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getResult());
    }
    if (signature_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getSignature());
    }
    if (signature2_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(3, getSignature2());
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof examples.apicoord.ResultSignature2)) {
      return super.equals(obj);
    }
    examples.apicoord.ResultSignature2 other = (examples.apicoord.ResultSignature2) obj;

    if (hasResult() != other.hasResult()) return false;
    if (hasResult()) {
      if (!getResult()
          .equals(other.getResult())) return false;
    }
    if (hasSignature() != other.hasSignature()) return false;
    if (hasSignature()) {
      if (!getSignature()
          .equals(other.getSignature())) return false;
    }
    if (hasSignature2() != other.hasSignature2()) return false;
    if (hasSignature2()) {
      if (!getSignature2()
          .equals(other.getSignature2())) return false;
    }
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (hasResult()) {
      hash = (37 * hash) + RESULT_FIELD_NUMBER;
      hash = (53 * hash) + getResult().hashCode();
    }
    if (hasSignature()) {
      hash = (37 * hash) + SIGNATURE_FIELD_NUMBER;
      hash = (53 * hash) + getSignature().hashCode();
    }
    if (hasSignature2()) {
      hash = (37 * hash) + SIGNATURE2_FIELD_NUMBER;
      hash = (53 * hash) + getSignature2().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static examples.apicoord.ResultSignature2 parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static examples.apicoord.ResultSignature2 parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static examples.apicoord.ResultSignature2 parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static examples.apicoord.ResultSignature2 parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static examples.apicoord.ResultSignature2 parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static examples.apicoord.ResultSignature2 parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static examples.apicoord.ResultSignature2 parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static examples.apicoord.ResultSignature2 parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static examples.apicoord.ResultSignature2 parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static examples.apicoord.ResultSignature2 parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static examples.apicoord.ResultSignature2 parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static examples.apicoord.ResultSignature2 parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(examples.apicoord.ResultSignature2 prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code apicoord.ResultSignature2}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:apicoord.ResultSignature2)
      examples.apicoord.ResultSignature2OrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return examples.apicoord.ApiCoordProto.internal_static_apicoord_ResultSignature2_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return examples.apicoord.ApiCoordProto.internal_static_apicoord_ResultSignature2_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              examples.apicoord.ResultSignature2.class, examples.apicoord.ResultSignature2.Builder.class);
    }

    // Construct using examples.apicoord.ResultSignature2.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      if (resultBuilder_ == null) {
        result_ = null;
      } else {
        result_ = null;
        resultBuilder_ = null;
      }
      if (signatureBuilder_ == null) {
        signature_ = null;
      } else {
        signature_ = null;
        signatureBuilder_ = null;
      }
      if (signature2Builder_ == null) {
        signature2_ = null;
      } else {
        signature2_ = null;
        signature2Builder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return examples.apicoord.ApiCoordProto.internal_static_apicoord_ResultSignature2_descriptor;
    }

    @java.lang.Override
    public examples.apicoord.ResultSignature2 getDefaultInstanceForType() {
      return examples.apicoord.ResultSignature2.getDefaultInstance();
    }

    @java.lang.Override
    public examples.apicoord.ResultSignature2 build() {
      examples.apicoord.ResultSignature2 result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public examples.apicoord.ResultSignature2 buildPartial() {
      examples.apicoord.ResultSignature2 result = new examples.apicoord.ResultSignature2(this);
      if (resultBuilder_ == null) {
        result.result_ = result_;
      } else {
        result.result_ = resultBuilder_.build();
      }
      if (signatureBuilder_ == null) {
        result.signature_ = signature_;
      } else {
        result.signature_ = signatureBuilder_.build();
      }
      if (signature2Builder_ == null) {
        result.signature2_ = signature2_;
      } else {
        result.signature2_ = signature2Builder_.build();
      }
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof examples.apicoord.ResultSignature2) {
        return mergeFrom((examples.apicoord.ResultSignature2)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(examples.apicoord.ResultSignature2 other) {
      if (other == examples.apicoord.ResultSignature2.getDefaultInstance()) return this;
      if (other.hasResult()) {
        mergeResult(other.getResult());
      }
      if (other.hasSignature()) {
        mergeSignature(other.getSignature());
      }
      if (other.hasSignature2()) {
        mergeSignature2(other.getSignature2());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      examples.apicoord.ResultSignature2 parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (examples.apicoord.ResultSignature2) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private examples.apicoord.Result result_;
    private com.google.protobuf.SingleFieldBuilderV3<
        examples.apicoord.Result, examples.apicoord.Result.Builder, examples.apicoord.ResultOrBuilder> resultBuilder_;
    /**
     * <code>.apicoord.Result result = 1;</code>
     * @return Whether the result field is set.
     */
    public boolean hasResult() {
      return resultBuilder_ != null || result_ != null;
    }
    /**
     * <code>.apicoord.Result result = 1;</code>
     * @return The result.
     */
    public examples.apicoord.Result getResult() {
      if (resultBuilder_ == null) {
        return result_ == null ? examples.apicoord.Result.getDefaultInstance() : result_;
      } else {
        return resultBuilder_.getMessage();
      }
    }
    /**
     * <code>.apicoord.Result result = 1;</code>
     */
    public Builder setResult(examples.apicoord.Result value) {
      if (resultBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        result_ = value;
        onChanged();
      } else {
        resultBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.apicoord.Result result = 1;</code>
     */
    public Builder setResult(
        examples.apicoord.Result.Builder builderForValue) {
      if (resultBuilder_ == null) {
        result_ = builderForValue.build();
        onChanged();
      } else {
        resultBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.apicoord.Result result = 1;</code>
     */
    public Builder mergeResult(examples.apicoord.Result value) {
      if (resultBuilder_ == null) {
        if (result_ != null) {
          result_ =
            examples.apicoord.Result.newBuilder(result_).mergeFrom(value).buildPartial();
        } else {
          result_ = value;
        }
        onChanged();
      } else {
        resultBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.apicoord.Result result = 1;</code>
     */
    public Builder clearResult() {
      if (resultBuilder_ == null) {
        result_ = null;
        onChanged();
      } else {
        result_ = null;
        resultBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.apicoord.Result result = 1;</code>
     */
    public examples.apicoord.Result.Builder getResultBuilder() {
      
      onChanged();
      return getResultFieldBuilder().getBuilder();
    }
    /**
     * <code>.apicoord.Result result = 1;</code>
     */
    public examples.apicoord.ResultOrBuilder getResultOrBuilder() {
      if (resultBuilder_ != null) {
        return resultBuilder_.getMessageOrBuilder();
      } else {
        return result_ == null ?
            examples.apicoord.Result.getDefaultInstance() : result_;
      }
    }
    /**
     * <code>.apicoord.Result result = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        examples.apicoord.Result, examples.apicoord.Result.Builder, examples.apicoord.ResultOrBuilder> 
        getResultFieldBuilder() {
      if (resultBuilder_ == null) {
        resultBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            examples.apicoord.Result, examples.apicoord.Result.Builder, examples.apicoord.ResultOrBuilder>(
                getResult(),
                getParentForChildren(),
                isClean());
        result_ = null;
      }
      return resultBuilder_;
    }

    private examples.apicoord.Signature signature_;
    private com.google.protobuf.SingleFieldBuilderV3<
        examples.apicoord.Signature, examples.apicoord.Signature.Builder, examples.apicoord.SignatureOrBuilder> signatureBuilder_;
    /**
     * <code>.apicoord.Signature signature = 2;</code>
     * @return Whether the signature field is set.
     */
    public boolean hasSignature() {
      return signatureBuilder_ != null || signature_ != null;
    }
    /**
     * <code>.apicoord.Signature signature = 2;</code>
     * @return The signature.
     */
    public examples.apicoord.Signature getSignature() {
      if (signatureBuilder_ == null) {
        return signature_ == null ? examples.apicoord.Signature.getDefaultInstance() : signature_;
      } else {
        return signatureBuilder_.getMessage();
      }
    }
    /**
     * <code>.apicoord.Signature signature = 2;</code>
     */
    public Builder setSignature(examples.apicoord.Signature value) {
      if (signatureBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        signature_ = value;
        onChanged();
      } else {
        signatureBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.apicoord.Signature signature = 2;</code>
     */
    public Builder setSignature(
        examples.apicoord.Signature.Builder builderForValue) {
      if (signatureBuilder_ == null) {
        signature_ = builderForValue.build();
        onChanged();
      } else {
        signatureBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.apicoord.Signature signature = 2;</code>
     */
    public Builder mergeSignature(examples.apicoord.Signature value) {
      if (signatureBuilder_ == null) {
        if (signature_ != null) {
          signature_ =
            examples.apicoord.Signature.newBuilder(signature_).mergeFrom(value).buildPartial();
        } else {
          signature_ = value;
        }
        onChanged();
      } else {
        signatureBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.apicoord.Signature signature = 2;</code>
     */
    public Builder clearSignature() {
      if (signatureBuilder_ == null) {
        signature_ = null;
        onChanged();
      } else {
        signature_ = null;
        signatureBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.apicoord.Signature signature = 2;</code>
     */
    public examples.apicoord.Signature.Builder getSignatureBuilder() {
      
      onChanged();
      return getSignatureFieldBuilder().getBuilder();
    }
    /**
     * <code>.apicoord.Signature signature = 2;</code>
     */
    public examples.apicoord.SignatureOrBuilder getSignatureOrBuilder() {
      if (signatureBuilder_ != null) {
        return signatureBuilder_.getMessageOrBuilder();
      } else {
        return signature_ == null ?
            examples.apicoord.Signature.getDefaultInstance() : signature_;
      }
    }
    /**
     * <code>.apicoord.Signature signature = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        examples.apicoord.Signature, examples.apicoord.Signature.Builder, examples.apicoord.SignatureOrBuilder> 
        getSignatureFieldBuilder() {
      if (signatureBuilder_ == null) {
        signatureBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            examples.apicoord.Signature, examples.apicoord.Signature.Builder, examples.apicoord.SignatureOrBuilder>(
                getSignature(),
                getParentForChildren(),
                isClean());
        signature_ = null;
      }
      return signatureBuilder_;
    }

    private examples.apicoord.Signature signature2_;
    private com.google.protobuf.SingleFieldBuilderV3<
        examples.apicoord.Signature, examples.apicoord.Signature.Builder, examples.apicoord.SignatureOrBuilder> signature2Builder_;
    /**
     * <code>.apicoord.Signature signature2 = 3;</code>
     * @return Whether the signature2 field is set.
     */
    public boolean hasSignature2() {
      return signature2Builder_ != null || signature2_ != null;
    }
    /**
     * <code>.apicoord.Signature signature2 = 3;</code>
     * @return The signature2.
     */
    public examples.apicoord.Signature getSignature2() {
      if (signature2Builder_ == null) {
        return signature2_ == null ? examples.apicoord.Signature.getDefaultInstance() : signature2_;
      } else {
        return signature2Builder_.getMessage();
      }
    }
    /**
     * <code>.apicoord.Signature signature2 = 3;</code>
     */
    public Builder setSignature2(examples.apicoord.Signature value) {
      if (signature2Builder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        signature2_ = value;
        onChanged();
      } else {
        signature2Builder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.apicoord.Signature signature2 = 3;</code>
     */
    public Builder setSignature2(
        examples.apicoord.Signature.Builder builderForValue) {
      if (signature2Builder_ == null) {
        signature2_ = builderForValue.build();
        onChanged();
      } else {
        signature2Builder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.apicoord.Signature signature2 = 3;</code>
     */
    public Builder mergeSignature2(examples.apicoord.Signature value) {
      if (signature2Builder_ == null) {
        if (signature2_ != null) {
          signature2_ =
            examples.apicoord.Signature.newBuilder(signature2_).mergeFrom(value).buildPartial();
        } else {
          signature2_ = value;
        }
        onChanged();
      } else {
        signature2Builder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.apicoord.Signature signature2 = 3;</code>
     */
    public Builder clearSignature2() {
      if (signature2Builder_ == null) {
        signature2_ = null;
        onChanged();
      } else {
        signature2_ = null;
        signature2Builder_ = null;
      }

      return this;
    }
    /**
     * <code>.apicoord.Signature signature2 = 3;</code>
     */
    public examples.apicoord.Signature.Builder getSignature2Builder() {
      
      onChanged();
      return getSignature2FieldBuilder().getBuilder();
    }
    /**
     * <code>.apicoord.Signature signature2 = 3;</code>
     */
    public examples.apicoord.SignatureOrBuilder getSignature2OrBuilder() {
      if (signature2Builder_ != null) {
        return signature2Builder_.getMessageOrBuilder();
      } else {
        return signature2_ == null ?
            examples.apicoord.Signature.getDefaultInstance() : signature2_;
      }
    }
    /**
     * <code>.apicoord.Signature signature2 = 3;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        examples.apicoord.Signature, examples.apicoord.Signature.Builder, examples.apicoord.SignatureOrBuilder> 
        getSignature2FieldBuilder() {
      if (signature2Builder_ == null) {
        signature2Builder_ = new com.google.protobuf.SingleFieldBuilderV3<
            examples.apicoord.Signature, examples.apicoord.Signature.Builder, examples.apicoord.SignatureOrBuilder>(
                getSignature2(),
                getParentForChildren(),
                isClean());
        signature2_ = null;
      }
      return signature2Builder_;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:apicoord.ResultSignature2)
  }

  // @@protoc_insertion_point(class_scope:apicoord.ResultSignature2)
  private static final examples.apicoord.ResultSignature2 DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new examples.apicoord.ResultSignature2();
  }

  public static examples.apicoord.ResultSignature2 getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ResultSignature2>
      PARSER = new com.google.protobuf.AbstractParser<ResultSignature2>() {
    @java.lang.Override
    public ResultSignature2 parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new ResultSignature2(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ResultSignature2> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ResultSignature2> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public examples.apicoord.ResultSignature2 getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

