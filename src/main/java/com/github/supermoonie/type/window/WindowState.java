package com.github.supermoonie.type.window;

/**
 * @author supermoonie
 * @date 2018/11/2 11:17
 */
public enum WindowState {

    /**
     * normal
     */
    normal("normal"),

    /**
     * minimized
     */
    minimized("minimized"),

    /**
     * maximized
     */
    maximized("maximized"),

    /**
     * fullscreen
     */
    fullscreen("fullscreen"),
    /**
     * active
     */
    active("active");

    private final String value;

    WindowState(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
