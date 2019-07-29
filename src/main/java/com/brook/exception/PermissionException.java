package com.brook.exception;

/**
 * @Author: xuequan
 * @Date: 2019/5/12 14:47
 * @Description:
 */
public class PermissionException extends RuntimeException {

    public PermissionException() {
        super();
    }

    public PermissionException(String msg) {
        super(msg);
    }

}
