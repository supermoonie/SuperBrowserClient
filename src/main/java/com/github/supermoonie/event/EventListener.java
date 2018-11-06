package com.github.supermoonie.event;

import com.alibaba.fastjson.JSONObject;
import okhttp3.WebSocket;

/**
 * @author wangchao
 * @date 2018/11/3 14:37
 */
public interface EventListener<T> {

    /**
     * when event emitted
     *
     * @param webSocket WebSocket
     * @param data      data
     */
    void onEvent(WebSocket webSocket, JSONObject data);
}
