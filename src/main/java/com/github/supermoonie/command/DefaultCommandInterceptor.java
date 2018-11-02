package com.github.supermoonie.command;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author supermoonie
 * @date 2018/11/2 17:16
 */
public class DefaultCommandInterceptor implements MethodInterceptor {

    private final static DefaultCommandInterceptor DEFAULT_COMMAND_INTERCEPTOR = new DefaultCommandInterceptor();

    public static DefaultCommandInterceptor instance() {
        return DEFAULT_COMMAND_INTERCEPTOR;
    }

    private DefaultCommandInterceptor() {
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        return proxy.invoke(obj, args);
    }
}
