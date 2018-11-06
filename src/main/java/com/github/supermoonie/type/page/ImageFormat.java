package com.github.supermoonie.type.page;

/**
 * @author supermoonie
 * @date 2018/11/6 13:12
 */
public enum ImageFormat {

    /**
     * png
     */
    Png("png"),

    /**
     * jpeg
     */
    Jpeg("jpeg");

    private String value;

    ImageFormat(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
