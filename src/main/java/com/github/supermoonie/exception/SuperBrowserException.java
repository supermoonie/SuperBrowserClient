package com.github.supermoonie.exception;

/**
 * @author wangchao
 * @date 2018/11/4 20:22
 */
public class SuperBrowserException extends RuntimeException {

    public SuperBrowserException(Throwable cause) {
        super(cause);
    }

    public SuperBrowserException(String message) {
        super(message);
    }
}
