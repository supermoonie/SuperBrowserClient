package com.github.supermoonie.exception;

/**
 * @author wangchao
 * @date 2018/11/4 20:23
 */
public class TimeOutException extends SuperBrowserException {

    public TimeOutException(Throwable cause) {
        super(cause);
    }

    public TimeOutException(String message) {
        super(message);
    }
}
