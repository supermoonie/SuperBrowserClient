package com.github.supermoonie.command;

import net.sf.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

/**
 * @author supermoonie
 * @date 2018/11/2 17:15
 */
public class CommandCallBackFilter implements CallbackFilter {

    private final static CommandCallBackFilter COMMAND_CALL_BACK_FILTER = new CommandCallBackFilter();

    public static CommandCallBackFilter instance() {
        return COMMAND_CALL_BACK_FILTER;
    }

    private CommandCallBackFilter() {
    }

    @Override
    public int accept(Method method) {
        final String command = method.getName();
        final String toString = "toString";
        if (command.equals(toString)) {
            return 1;
        }
        return 0;
    }
}
