package com.github.supermoonie.browser;

/**
 * @author supermoonie
 * @date 2018/11/5 18:52
 */
public class Conditions {

    public static Condition<Boolean> hasAlertCondition = (Condition<Boolean>) superBrowser -> {
        if (superBrowser.getPage().hasAlert()) {
            return Boolean.TRUE;
        }
        return null;
    };

    public static Condition<Boolean> hasConfirmCondition = (Condition<Boolean>) superBrowser -> {
        if (superBrowser.getPage().hasConfirm()) {
            return Boolean.TRUE;
        }
        return null;
    };

    public static Condition<Boolean> hasPromptCondition = (Condition<Boolean>) superBrowser -> {
        if (superBrowser.getPage().hasPrompt()) {
            return Boolean.TRUE;
        }
        return null;
    };
}
