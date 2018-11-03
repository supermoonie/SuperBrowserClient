package com.github.supermoonie.command;

import com.github.supermoonie.annotation.Param;
import com.github.supermoonie.annotation.Returns;
import com.github.supermoonie.type.browser.Bounds;
import com.github.supermoonie.type.browser.Version;
import com.github.supermoonie.type.browser.WindowState;

/**
 * @author supermoonie
 * @date 2018/11/2 11:15
 */
public interface Window extends Command {

    /**
     * version
     *
     * @return  version
     */
    Version getVersion();

    /**
     * getWindowState
     *
     * @return  WindowState
     */
    @Returns("state")
    WindowState getWindowState();

    /**
     * setWindowState
     *
     * @param state WindowState
     */
    void setWindowState(@Param("state") WindowState state);

    /**
     * getWindowBounds
     *
     * @return  Bounds
     */
    Bounds getWindowBounds();

    /**
     * setWindowBounds
     *
     * @param bounds    Bounds
     */
    void setWindowBounds(@Param("bounds") Bounds bounds);

    /**
     * setWindowBounds
     *
     * @param x     x
     * @param y     y
     * @param width     width
     * @param height    height
     */
    void setWindowBounds(@Param("x") int x, @Param("y") int y, @Param("width") int width, @Param("height") int height);

    /**
     * close
     */
    void close();
}
