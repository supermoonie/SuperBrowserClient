package com.github.supermoonie.command;

import com.github.supermoonie.annotation.Param;
import com.github.supermoonie.annotation.Returns;

/**
 * @author supermoonie
 * @date 2018/11/2 17:35
 */
public interface Page extends Command{

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
