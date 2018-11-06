package com.github.supermoonie.browser;

import com.github.supermoonie.command.*;
import com.github.supermoonie.event.Event;
import com.github.supermoonie.event.EventListener;
import com.github.supermoonie.exception.SuperBrowserException;
import com.github.supermoonie.exception.TimeOutException;
import com.github.supermoonie.ws.DefaultWebSocketListener;
import com.github.supermoonie.ws.WebSocketContext;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

import java.io.Closeable;
import java.io.IOException;
import java.net.ConnectException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author supermoonie
 * @date 2018/11/2 11:21
 */
public class SuperBrowser implements Closeable, WaitUntil, Screenshot {

    private WebSocket webSocket;

    private DefaultWebSocketListener webSocketListener;

    private CommandInterceptor invocationHandler;

    private final Map<Class, Command> proxies = new ConcurrentHashMap<>();

    private final Map<Event, EventListener> eventListeners = new ConcurrentHashMap<>();

    public SuperBrowser() throws ConnectException {
        OkHttpClient client = new OkHttpClient.Builder().pingInterval(0, TimeUnit.SECONDS).build();
        Request request = new Request.Builder().url("ws://127.0.0.1:9900").build();
        Map<Integer, WebSocketContext> contexts = new ConcurrentHashMap<>();
        webSocketListener = new DefaultWebSocketListener(contexts, eventListeners);
        webSocket = client.newWebSocket(request, webSocketListener);
        webSocketListener.waitConnect(3000, TimeUnit.MILLISECONDS);
        invocationHandler = new CommandInterceptor(contexts, webSocket, this, 3000);
    }

    public Window getWindow() {
        return (Window) getProxy(Window.class);
    }

    public Page getPage() {
        return (Page) getProxy(Page.class);
    }

    private Command getProxy(Class clazz) {
        Command proxy = proxies.get(clazz);
        if (proxy != null) {
            return proxy;
        }
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        Callback[] callbacks = new Callback[]{invocationHandler, DefaultCommandInterceptor.instance()};
        enhancer.setCallbacks(callbacks);
        enhancer.setCallbackFilter(CommandCallBackFilter.instance());
        proxy = (Command) enhancer.create();
        Command existing = proxies.putIfAbsent(clazz, proxy);
        if (existing != null) {
            return existing;
        }
        return proxy;
    }

    public void addEventListener(Event event, EventListener listener) {
        eventListeners.put(event, listener);
    }

    public void removeEventListener(Event event) {
        eventListeners.remove(event);
    }

    public <T> T until(Condition<T> condition, long timeout) {
        long start = System.currentTimeMillis();
        do {
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                throw new SuperBrowserException(e);
            }
            T t = condition.apply(this);
            if (null != t) {
                return t;
            }
            if (System.currentTimeMillis() - start >= timeout) {
                throw new TimeOutException("Time out");
            }
        } while (true);
    }

    public boolean isConnected() {
        return webSocketListener.isConnected();
    }

    public boolean isClosing() {
        return webSocketListener.isClosing();
    }

    public boolean isClosed() {
        return webSocketListener.isClosed();
    }

    @Override
    public void close() throws IOException {
        getWindow().close();
        webSocket.close(1001, "");
        webSocketListener.waitClose(3000, TimeUnit.MILLISECONDS);
    }

    @Override
    public SuperBrowser getThis() {
        return this;
    }
}
