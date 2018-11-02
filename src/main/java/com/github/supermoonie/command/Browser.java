package com.github.supermoonie.command;

import com.github.supermoonie.annotation.Param;
import com.github.supermoonie.annotation.Returns;
import com.github.supermoonie.type.browser.Version;
import com.github.supermoonie.type.browser.WindowState;

/**
 * @author supermoonie
 * @date 2018/11/2 11:15
 */
public interface Browser extends Command {

    /**
     * version
     *
     * @return  version
     */
    Version getVersion();

    /**
     * navigate
     *
     * @param url   url
     */
    void navigate(@Param("url") String url);

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
}
