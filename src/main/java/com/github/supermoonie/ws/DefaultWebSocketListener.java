package com.github.supermoonie.ws;

import com.alibaba.fastjson.JSONObject;
import com.github.supermoonie.event.Event;
import com.github.supermoonie.event.EventListener;
import com.github.supermoonie.exception.CommandException;
import com.github.supermoonie.exception.SuperBrowserException;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

import java.net.ConnectException;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author supermoonie
 * @date 2018/11/2 10:52
 */
public class DefaultWebSocketListener extends WebSocketListener {

    private final Map<Integer, WebSocketContext> contexts;

    private final Map<Event, EventListener> eventListeners;

    private CountDownLatch openLatch = new CountDownLatch(1);

    private CountDownLatch closeLatch = new CountDownLatch(1);

    private State state;

    public DefaultWebSocketListener(Map<Integer, WebSocketContext> contexts, Map<Event, EventListener> eventListeners) {
        this.contexts = contexts;
        this.eventListeners = eventListeners;
    }

    public void waitConnect(long timeout, TimeUnit unit) throws ConnectException {
        try {
            long deadline = System.nanoTime() + unit.toNanos(timeout);
            openLatch.await(timeout, unit);
            if (System.nanoTime() > deadline) {
                throw new ConnectException();
            }
        } catch (InterruptedException e) {
            throw new SuperBrowserException(e);
        }
    }

    public void waitClose(long timeout, TimeUnit unit) {
        try {
            long deadline = System.nanoTime() + unit.toNanos(timeout);
            closeLatch.await(timeout, unit);
            if (System.nanoTime() > deadline) {
                throw new SuperBrowserException("Could not close web socket!");
            }
        } catch (InterruptedException e) {
            throw new SuperBrowserException(e);
        }
    }

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        state = State.Open;
        openLatch.countDown();
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        System.out.println("receive: " + text);
        JSONObject json = JSONObject.parseObject(text);
        String eventKey = "event";
        String idKey = "id";
        if (json.containsKey(eventKey)) {
            String eventName = json.getString(eventKey);
            Event event = Event.valueOf(eventName);
            EventListener listener = eventListeners.remove(event);
            if (null != listener) {
                listener.onEvent(webSocket, json.getJSONObject("result"));
            }
        } else if (json.containsKey(idKey)) {
            int id = json.getIntValue(idKey);
            WebSocketContext context = contexts.remove(id);
            if (null != context) {
                String errorKey = "error";
                if (json.containsKey(errorKey)) {
                    String errorMsg = json.getString(errorKey);
                    context.setError(new CommandException(errorMsg));
                } else {
                    context.setData(json);
                }
            }
        }
    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        state = State.Closing;
    }

    @Override
    public void onClosed(WebSocket webSocket, int code, String reason) {
        state = State.Closed;
        closeLatch.countDown();
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        t.printStackTrace();
    }

    public boolean isConnected() {
        return state != null && state == State.Open;
    }

    public boolean isClosing() {
        return state != null && state == State.Closing;
    }

    public boolean isClosed() {
        return state != null && state == State.Closed;
    }

    private enum State {
        /**
         * open
         */
        Open,
        /**
         * closing
         */
        Closing,
        /**
         * closed
         */
        Closed
    }
}
