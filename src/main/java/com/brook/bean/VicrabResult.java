package com.brook.bean;


public class VicrabResult<T> {

    private OperateCode operateCode;
    private T data;

    public VicrabResult(OperateCode operateCode, T data) {
        this.operateCode = operateCode;
        this.data = data;
    }

    public static <T> VicrabResult<T> set(OperateCode operateCode, T data) {
        return new VicrabResult<T>(operateCode, data);
    }

    public static <T> VicrabResult<T> set(OperateCode operateCode) {
        return new VicrabResult<T>(operateCode, null);
    }

    public OperateCode getOperateCode() {
        return operateCode;
    }

    public void setOperateCode(OperateCode operateCode) {
        this.operateCode = operateCode;
    }

    public T getData() {
        return data;
    }

    public void setContent(T content) {
        this.data = content;
    }
}
