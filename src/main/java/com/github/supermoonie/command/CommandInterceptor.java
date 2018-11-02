package com.github.supermoonie.command;

import com.alibaba.fastjson.JSONObject;
import com.github.supermoonie.annotation.Param;
import com.github.supermoonie.annotation.Returns;
import com.github.supermoonie.ws.WebSocketContext;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import okhttp3.WebSocket;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author supermoonie
 * @date 2018/11/2 11:53
 */
public class CommandInterceptor implements MethodInterceptor {

    private final AtomicInteger counter = new AtomicInteger(0);

    private final Map<Integer, WebSocketContext> contexts;

    private final WebSocket webSocket;

    private long timeOut;

    public CommandInterceptor(Map<Integer, WebSocketContext> contexts, WebSocket webSocket, long timeOut) {
        this.contexts = contexts;
        this.webSocket = webSocket;
        this.timeOut = timeOut;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        final String command = method.getName();
        int id = counter.incrementAndGet();
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("method", command);
        final boolean hasArgs = null != args && args.length > 0;
        if (hasArgs) {
            Map<String, Object> params = new HashMap<>(args.length);
            int argIndex = 0;
            Parameter[] parameters = method.getParameters();
            for (Object argValue : args) {
                String argName = parameters[argIndex++].getAnnotation(Param.class).value();
                params.put(argName, argValue);
            }
            json.put("params", params);
        }
        String message = json.toJSONString();
        WebSocketContext context = new WebSocketContext();
        contexts.put(id, context);
        webSocket.send(message);
        context.await(timeOut);
        if (null != context.getError()) {
            throw context.getError();
        }
        final boolean voidMethod = void.class.equals(method.getReturnType());
        Class<?> returnType = method.getReturnType();
        if (voidMethod || returnType.equals(Void.class)) {
            return null;
        }
        JSONObject data = context.getData();
        if (null == data) {
            return null;
        }
        String resultKey = "result";
        if (!data.containsKey(resultKey)) {
            throw new Exception("invalid result: " + data.toString());
        }
        JSONObject resultJson = data.getJSONObject(resultKey);
        Object result;
        Type genericReturnType = method.getGenericReturnType();
        String returns = method.isAnnotationPresent(Returns.class) ?
                method.getAnnotation(Returns.class).value() : null;
        if(null != returns) {
            boolean hasReturns = resultJson.containsKey(returns);
            if (hasReturns) {
                result = resultJson.getObject(returns, genericReturnType);
            } else {
                result = resultJson.toJavaObject(genericReturnType);
            }
        } else {
            result = resultJson.toJavaObject(genericReturnType);
        }
        return result;
    }

    public long getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(long timeOut) {
        this.timeOut = timeOut;
    }
}
