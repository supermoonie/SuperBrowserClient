package com.github.supermoonie.command;

import com.github.supermoonie.annotation.Param;
import com.github.supermoonie.annotation.Returns;

/**
 * @author supermoonie
 * @date 2018/11/2 17:35
 */
public interface Page extends Command{

    /**
     * navigate
     *
     * @param url   url
     */
    void navigate(@Param("url") String url);

    /**
     * currentUrl
     *
     * @return  current url
     */
    @Returns("currentUrl")
    String currentUrl();

    /**
     * getUserAgent
     *
     * @return  userAgent
     */
    @Returns("userAgent")
    String getUserAgent();

    /**
     * setUserAgent
     *
     * @param userAgent ua
     */
    void setUserAgent(@Param("userAgent") String userAgent);
}
