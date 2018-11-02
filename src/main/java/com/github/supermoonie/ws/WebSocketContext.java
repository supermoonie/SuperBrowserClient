package com.github.supermoonie.ws;

import com.alibaba.fastjson.JSONObject;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author supermoonie
 * @date 2018/11/2 11:11
 */
public class WebSocketContext {
    private CountDownLatch latch = new CountDownLatch(1);

    private JSONObject data;

    private Exception error;

    public void await(final long timeout) throws InterruptedException {
        latch.await(timeout, TimeUnit.MILLISECONDS);
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
        latch.countDown();
    }

    public Exception getError() {
        return error;
    }

    public void setError(Exception error) {
        this.error = error;
        latch.countDown();
    }
}
