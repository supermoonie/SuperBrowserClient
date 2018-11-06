package com.github.supermoonie.type.window;

/**
 * @author supermoonie
 * @date 2018/11/2 17:56
 */
public class Version {

    private int major;

    private int minor;

    public int getMajor() {
        return major;
    }

    public void setMajor(int major) {
        this.major = major;
    }

    public int getMinor() {
        return minor;
    }

    public void setMinor(int minor) {
        this.minor = minor;
    }

    @Override
    public String toString() {
        return "Version{" +
                "major=" + major +
                ", minor=" + minor +
                '}';
    }
}
