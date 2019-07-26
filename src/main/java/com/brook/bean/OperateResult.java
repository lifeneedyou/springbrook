package com.brook.bean;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * OperateResult
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-14T15:01:36.900+08:00")

public class OperateResult<T> {
    @JsonProperty("operate_code")
    private String operateCode = null;

    @JsonProperty("operate_msg")
    private String operateMsg = null;

    @JsonProperty("operate_error")
    private String operateError = null;

    @JsonProperty("operate_result")
    private Object operateResult = null;

    @JsonProperty("operate_timestamp")
    private Long operateTimestamp = null;


    public OperateResult() {
        this.operateTimestamp = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }


    public OperateResult(OperateCode operateCode) {
        this.operateCode = operateCode.getOperateCode();
        this.operateMsg = operateCode.getOperateMsg();
        this.operateTimestamp = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    public <T extends Throwable> OperateResult(OperateCode operateCode, T ex) {
        this.operateCode = operateCode.getOperateCode();
        this.operateMsg = operateCode.getOperateMsg();
        this.operateError = ex.getMessage();
        this.operateTimestamp = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    public OperateResult(OperateCode operateCode, T operateResult) {
        this.operateCode = operateCode.getOperateCode();
        this.operateMsg = operateCode.getOperateMsg();
        this.operateResult = operateResult;
        this.operateTimestamp = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    public OperateResult(String code, String msg, T content) {
        this.operateCode = code;
        this.operateMsg = msg;
        this.operateResult = content;
        this.operateTimestamp = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }


    /**
     * 成功
     *
     * @param <T>
     * @return
     */
    public static <T> OperateResult<T> success() {
        return new OperateResult<T>(OperateCode.SUCCESS);
    }

    public static <T> OperateResult<T> success(T content) {
        return new OperateResult<T>(OperateCode.SUCCESS, content);
    }

    /**
     * 失败
     *
     * @param content
     * @param <T>
     * @return
     */
    public static <T> OperateResult<T> fail() {
        return new OperateResult<T>(OperateCode.FAIL);
    }

    /**
     * 设置
     *
     * @param operateCode
     * @param <T>
     * @return
     */
    public static <T> OperateResult<T> set(OperateCode operateCode) {
        return new OperateResult<T>(operateCode);
    }

    public static <T> OperateResult<T> set(OperateCode operateCode, T content) {
        return new OperateResult<T>(operateCode, content);
    }

    public static <T> OperateResult<T> set(String code, String msg) {
        return new OperateResult<T>(code, msg, null);
    }

    /**
     * 异常
     *
     * @param operateCode
     * @param ex
     * @param <T>
     * @return
     */
    public static <T extends Throwable> OperateResult exception(OperateCode operateCode, T ex) {
        return new OperateResult(operateCode,ex);
    }


    public OperateResult operateCode(String operateCode) {
        this.operateCode = operateCode;
        return this;
    }


    /**
     * 操作结果
     * @return operateCode
     **/
    @ApiModelProperty(value = "操作结果")


    public String getOperateCode() {
        return operateCode;
    }

    public void setOperateCode(String operateCode) {
        this.operateCode = operateCode;
    }

    public OperateResult operateMsg(String operateMsg) {
        this.operateMsg = operateMsg;
        return this;
    }

    /**
     * 操作消息
     * @return operateMsg
     **/
    @ApiModelProperty(value = "操作消息")


    public String getOperateMsg() {
        return operateMsg;
    }

    public void setOperateMsg(String operateMsg) {
        this.operateMsg = operateMsg;
    }

    public OperateResult operateError(String operateError) {
        this.operateError = operateError;
        return this;
    }

    /**
     * 错误信息
     * @return operateError
     **/
    @ApiModelProperty(value = "错误信息")


    public String getOperateError() {
        return operateError;
    }

    public void setOperateError(String operateError) {
        this.operateError = operateError;
    }

    public OperateResult operateResult(Object operateResult) {
        this.operateResult = operateResult;
        return this;
    }

    /**
     * 返回结果
     * @return operateResult
     **/
    @ApiModelProperty(value = "返回结果")


    public Object getOperateResult() {
        return operateResult;
    }

    public void setOperateResult(Object operateResult) {
        this.operateResult = operateResult;
    }

    public OperateResult operateTimestamp(Long operateTimestamp) {
        this.operateTimestamp = operateTimestamp;
        return this;
    }

    /**
     * 操作时间戳，单位毫秒
     * @return operateTimestamp
     **/
    @ApiModelProperty(value = "操作时间戳，单位毫秒")


    public Long getOperateTimestamp() {
        return operateTimestamp;
    }

    public void setOperateTimestamp(Long operateTimestamp) {
        this.operateTimestamp = operateTimestamp;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OperateResult operateResult = (OperateResult) o;
        return Objects.equals(this.operateCode, operateResult.operateCode) &&
                Objects.equals(this.operateMsg, operateResult.operateMsg) &&
                Objects.equals(this.operateError, operateResult.operateError) &&
                Objects.equals(this.operateResult, operateResult.operateResult) &&
                Objects.equals(this.operateTimestamp, operateResult.operateTimestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operateCode, operateMsg, operateError, operateResult, operateTimestamp);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class OperateResult {\n");

        sb.append("    operateCode: ").append(toIndentedString(operateCode)).append("\n");
        sb.append("    operateMsg: ").append(toIndentedString(operateMsg)).append("\n");
        sb.append("    operateError: ").append(toIndentedString(operateError)).append("\n");
        sb.append("    operateResult: ").append(toIndentedString(operateResult)).append("\n");
        sb.append("    operateTimestamp: ").append(toIndentedString(operateTimestamp)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

