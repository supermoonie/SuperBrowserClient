package com.github.supermoonie.exception;

/**
 * @author supermoonie
 * @date 2018/11/5 17:43
 */
public class AlertExistException extends SuperBrowserException {

    public AlertExistException() {
        super("Alert already exist!");
    }
}
