package com.github.supermoonie.exception;

/**
 * @author wangchao
 * @date 2018/11/5 21:58
 */
public class CommandException extends SuperBrowserException {

    public CommandException(Throwable cause) {
        super(cause);
    }

    public CommandException(String message) {
        super(message);
    }
}
