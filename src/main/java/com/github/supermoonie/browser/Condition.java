package com.github.supermoonie.browser;

/**
 * @author supermoonie
 * @date 2018/11/5 14:27
 */
public interface Condition<T> {

    /**
     * apply
     *
     * @param superBrowser  SuperBrowser
     * @return  t
     */
    T apply(SuperBrowser superBrowser);
}
