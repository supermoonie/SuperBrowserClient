package com.github.supermoonie.browser;

/**
 * @author supermoonie
 * @date 2018/11/5 18:52
 */
public class Conditions {

    public static Condition<Boolean> hasAlertCondition = (Condition<Boolean>) superBrowser -> {
        if (superBrowser.getWindow().hasAlert()) {
            return Boolean.TRUE;
        }
        return null;
    };

    public static Condition<Boolean> hasConfirmCondition = (Condition<Boolean>) superBrowser -> {
        if (superBrowser.getWindow().hasConfirm()) {
            return Boolean.TRUE;
        }
        return null;
    };
}
