package com.github.supermoonie.event;

/**
 *
 * @author Administrator
 * @date 2018/11/3 0003
 */
public enum Events {

    /**
     * loadFinished
     */
    loadFinished("loadFinished")
    ;

    private String name;

    Events(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
