package com.github.supermoonie.exception;

/**
 * @author wangchao
 * @date 2018/11/4 20:24
 */
public class LoadTimeOutException extends TimeOutException {

    public LoadTimeOutException(Throwable cause) {
        super(cause);
    }

    public LoadTimeOutException(String message) {
        super(message);
    }
}
