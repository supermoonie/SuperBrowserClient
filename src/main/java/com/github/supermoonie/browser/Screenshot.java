package com.github.supermoonie.browser;

import com.github.supermoonie.type.page.ImageFormat;
import com.github.supermoonie.type.page.ScrollBarPolicy;
import com.github.supermoonie.type.page.Viewport;

/**
 * @author supermoonie
 * @date 2018/11/6 17:45
 */
public interface Screenshot {

    /**
     * captureScreenshot
     *
     * @param fullScreen is full screen
     * @return base64-encoded image
     */
    default String captureScreenshot(boolean fullScreen) {
        getThis().getPage().setScrollBarPolicy(ScrollBarPolicy.ScrollBarAlwaysOff, ScrollBarPolicy.ScrollBarAlwaysOff);
        String base64Image = getThis().getPage().captureScreenshot(fullScreen);
        getThis().getPage().setScrollBarPolicy(ScrollBarPolicy.ScrollBarAsNeeded, ScrollBarPolicy.ScrollBarAsNeeded);
        return base64Image;
    }

    /**
     * captureScreenshot
     *
     * @param format  ImageFormat
     * @param quality quality
     * @param clip    Viewport
     * @return base64-encoded image
     */
    default String captureScreenshot(ImageFormat format, int quality, Viewport clip) {
        getThis().getPage().setScrollBarPolicy(ScrollBarPolicy.ScrollBarAlwaysOff, ScrollBarPolicy.ScrollBarAlwaysOff);
        String base64Image = getThis().getPage().captureScreenshot(format, quality, clip);
        getThis().getPage().setScrollBarPolicy(ScrollBarPolicy.ScrollBarAsNeeded, ScrollBarPolicy.ScrollBarAsNeeded);
        return base64Image;
    }

    /**
     * get this
     *
     * @return SuperBrowser
     */
    SuperBrowser getThis();
}
