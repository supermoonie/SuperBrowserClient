package com.github.supermoonie.type.page;

/**
 * @author supermoonie
 * @date 2018/11/6 16:27
 */
public enum ScrollBarPolicy {

    /**
     * ScrollBarAsNeeded
     */
    ScrollBarAsNeeded("ScrollBarAsNeeded"),

    /**
     * ScrollBarAlwaysOff
     */
    ScrollBarAlwaysOff("ScrollBarAlwaysOff"),

    /**
     * ScrollBarAlwaysOn
     */
    ScrollBarAlwaysOn("ScrollBarAlwaysOn");

    private String name;

    ScrollBarPolicy(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
