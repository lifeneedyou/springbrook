package com.brook.advice;

import org.apache.shiro.authc.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brook.bean.OperateCode;
import com.brook.bean.OperateResult;

import java.io.IOException;

@ControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);
    private static final String logExceptionFormat = "Capture Exception By GlobalExceptionHandler: Code: %s Detail: %s";

    //运行时异常
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public OperateResult runtimeExceptionHandler(RuntimeException ex) {
        return exceptionFormat(OperateCode.EX_RUN, ex);
    }

    //空指针异常
    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public OperateResult nullPointerExceptionHandler(NullPointerException ex) {
        return exceptionFormat(OperateCode.EX_NULL, ex);
    }

    //类型转换异常
    @ExceptionHandler(ClassCastException.class)
    @ResponseBody
    public OperateResult classCastExceptionHandler(ClassCastException ex) {
        return exceptionFormat(OperateCode.EX_CAST, ex);
    }

    //IO异常
    @ExceptionHandler(IOException.class)
    @ResponseBody
    public OperateResult iOExceptionHandler(IOException ex) {
        return exceptionFormat(OperateCode.EX_IO, ex);
    }

    //未知方法异常
    @ExceptionHandler(NoSuchMethodException.class)
    @ResponseBody
    public OperateResult noSuchMethodExceptionHandler(NoSuchMethodException ex) {
        return exceptionFormat(OperateCode.EX_NO_SUCH_METHOD, ex);
    }

    //数组越界异常
    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseBody
    public OperateResult indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) {
        return exceptionFormat(OperateCode.EX_INDEX_OUT_OF_BOUNDNS, ex);
    }

    //400错误
    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseBody
    public OperateResult requestNotReadable(HttpMessageNotReadableException ex) {
        logger.error("400..requestNotReadable");
        return exceptionFormat(OperateCode.BAD_REQUEST, ex);
    }

    //400错误
    @ExceptionHandler({TypeMismatchException.class})
    @ResponseBody
    public OperateResult requestTypeMismatch(TypeMismatchException ex) {
        logger.error("400..TypeMismatchException");
        return exceptionFormat(OperateCode.BAD_REQUEST, ex);
    }

    //400错误
    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseBody
    public OperateResult requestMissingServletRequest(MissingServletRequestParameterException ex) {
        logger.error("400..MissingServletRequest");
        return exceptionFormat(OperateCode.BAD_REQUEST, ex);
    }

    //405错误
    @ExceptionHandler({AuthenticationException.class})
    @ResponseBody
    public OperateResult request401(AuthenticationException ex) {
        return exceptionFormat(OperateCode.UNAUTHORIZED, ex);
    }

    //405错误
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseBody
    public OperateResult request405(HttpRequestMethodNotSupportedException ex) {
        return exceptionFormat(OperateCode.METHOD_NOT_ALLOWED, ex);
    }

    //406错误
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    @ResponseBody
    public OperateResult request406(HttpMediaTypeNotAcceptableException ex) {
        logger.error("406...");
        return exceptionFormat(OperateCode.NOT_ACCEPTABLE, ex);
    }

    //500错误
    @ExceptionHandler({ConversionNotSupportedException.class, HttpMessageNotWritableException.class})
    @ResponseBody
    public OperateResult server500(RuntimeException ex) {
        logger.error("500...");
        return exceptionFormat(OperateCode.INTERNAL_SERVER_ERROR, ex);
    }

    //栈溢出
    @ExceptionHandler({StackOverflowError.class})
    @ResponseBody
    public OperateResult requestStackOverflow(StackOverflowError ex) {
        return exceptionFormat(OperateCode.EX_STACK_OVER_FLOW, ex);
    }

    //其他错误
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public OperateResult exception(Exception ex) {
        return exceptionFormat(OperateCode.EX_OTHER, ex);
    }


    private <T extends Throwable> OperateResult exceptionFormat(OperateCode operateCode, T ex) {
        logger.error(String.format(logExceptionFormat, operateCode, ex));
        return OperateResult.exception(operateCode, ex);
    }

}
