package com.github.supermoonie.exception;

/**
 * @author supermoonie
 * @date 2018/11/5 19:04
 */
public class ConfirmExistException extends SuperBrowserException {

    public ConfirmExistException() {
        super("Confirm already exist!");
    }
}
