package com.github.supermoonie.browser;

import com.github.supermoonie.event.Event;

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
     * @param url   url
     * @param ms    wait time
     * @throws InterruptedException e
     */
    default void navigateUntilLoadFinished(String url, long ms) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        getThis().addEventListener(Event.loadFinished,  (webSocket, data) -> latch.countDown());
        getThis().getPage().navigate(url);
        latch.await(ms, TimeUnit.MILLISECONDS);
    }

    /**
     * get SuperBrowser
     *
     * @return  SuperBrowser
     */
    SuperBrowser getThis();
}
