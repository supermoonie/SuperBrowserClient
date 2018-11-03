package com.github.supermoonie.ws;

import com.alibaba.fastjson.JSONObject;
import com.github.supermoonie.event.Event;
import com.github.supermoonie.event.EventListener;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

import java.util.Map;

/**
 * @author supermoonie
 * @date 2018/11/2 10:52
 */
public class DefaultWebSocketListener extends WebSocketListener {

    private final Map<Integer, WebSocketContext> contexts;

    private final Map<Event, EventListener> eventListeners;

    public DefaultWebSocketListener(Map<Integer, WebSocketContext> contexts, Map<Event, EventListener> eventListeners) {
        this.contexts = contexts;
        this.eventListeners = eventListeners;
    }

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        System.out.println("open");
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
            if(null != context) {
                String errorKey = "error";
                if (json.containsKey(errorKey)) {
                    String errorMsg = json.getString(errorKey);
                    context.setError(new Exception("error message: " + errorMsg));
                } else {
                    context.setData(json);
                }
            }
        }
    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        System.out.println("closing");
    }

    @Override
    public void onClosed(WebSocket webSocket, int code, String reason) {
        System.out.println("closed");
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        t.printStackTrace();
    }
}
