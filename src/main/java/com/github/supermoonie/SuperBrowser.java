package com.github.supermoonie;

import com.github.supermoonie.command.*;
import com.github.supermoonie.ws.DefaultWebSocketListener;
import com.github.supermoonie.ws.WebSocketContext;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Enhancer;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author supermoonie
 * @date 2018/11/2 11:21
 */
public class SuperBrowser implements Closeable {

    private WebSocket webSocket;

    private CommandInterceptor invocationHandler;

    private final Map<Class, Command> proxies = new ConcurrentHashMap<>();


    public SuperBrowser() {
        OkHttpClient client = new OkHttpClient.Builder().pingInterval(0, TimeUnit.SECONDS).build();
        Request request = new Request.Builder().url("ws://127.0.0.1:9900").build();
        Map<Integer, WebSocketContext> contexts = new ConcurrentHashMap<>();
        webSocket = client.newWebSocket(request, new DefaultWebSocketListener(contexts));
        invocationHandler = new CommandInterceptor(contexts, webSocket, 3000);
    }

    public Browser getBrowser() {
        return (Browser) getProxy(Browser.class);
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

    @Override
    public void close() throws IOException {
        // TODO
        // getBrowser().close();
        webSocket.close(1001, "");
    }
}
