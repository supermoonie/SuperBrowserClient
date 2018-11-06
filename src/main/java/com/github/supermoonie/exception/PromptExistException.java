package com.github.supermoonie.exception;

/**
 * @author supermoonie
 * @date 2018/11/6 10:23
 */
public class PromptExistException extends SuperBrowserException {

    public PromptExistException() {
        super("Prompt already exist!");
    }
}
