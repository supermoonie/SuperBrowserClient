package com.github.supermoonie.browser;

import com.github.supermoonie.event.Event;
import com.github.supermoonie.exception.AlertExistException;
import com.github.supermoonie.exception.ConfirmExistException;
import com.github.supermoonie.exception.LoadTimeOutException;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author wangchao
 * @date 2018/11/3 23:22
 */
public interface WaitUntil {

    /**
     * navigate until load finished
     *
     * @param url     url
     * @param timeout wait time
     * @return SuperBrowser
     */
    default SuperBrowser navigateUntilLoadFinished(String url, long timeout) {
        CountDownLatch latch = new CountDownLatch(1);
        long start = System.currentTimeMillis();
        getThis().addEventListener(Event.loadFinished, (webSocket, data) -> latch.countDown());
        getThis().getPage().navigate(url);
        try {
            latch.await(timeout, TimeUnit.MILLISECONDS);
            if (System.currentTimeMillis() - start >= timeout) {
                throw new LoadTimeOutException("Page not load finish in " + timeout + " milliseconds");
            }
        } catch (InterruptedException e) {
            throw new LoadTimeOutException(e);
        }
        return getThis();
    }

    /**
     * navigate until alert
     *
     * @param url     url
     * @param timeout timeout
     * @return SuperBrowser
     */
    default SuperBrowser navigateUntilAlert(String url, long timeout) {
        if (getThis().getWindow().hasAlert()) {
            throw new AlertExistException();
        }
        getThis().getPage().navigate(url);
        getThis().until(Conditions.hasAlertCondition, timeout);
        return getThis();
    }

    /**
     * navigate until confirm
     *
     * @param url     url
     * @param timeout timeout
     * @return SuperBrowser
     */
    default SuperBrowser navigateUntilConfirm(String url, long timeout) {
        if (getThis().getWindow().hasConfirm()) {
            throw new ConfirmExistException();
        }
        getThis().getPage().navigate(url);
        getThis().until(Conditions.hasConfirmCondition, timeout);
        return getThis();
    }

    /**
     * get SuperBrowser
     *
     * @return SuperBrowser
     */
    SuperBrowser getThis();
}
