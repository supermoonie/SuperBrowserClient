package com.github.supermoonie.command;

import com.github.supermoonie.annotation.Param;
import com.github.supermoonie.annotation.Returns;
import com.github.supermoonie.type.page.ImageFormat;
import com.github.supermoonie.type.page.ScrollBarPolicy;
import com.github.supermoonie.type.page.Viewport;

/**
 * @author supermoonie
 * @date 2018/11/2 17:35
 */
public interface Page extends Command {

    /**
     * navigate
     *
     * @param url url
     */
    void navigate(@Param("url") String url);

    /**
     * currentUrl
     *
     * @return current url
     */
    @Returns("currentUrl")
    String currentUrl();

    /**
     * getUserAgent
     *
     * @return userAgent
     */
    @Returns("userAgent")
    String getUserAgent();

    /**
     * setUserAgent
     *
     * @param userAgent ua
     */
    void setUserAgent(@Param("userAgent") String userAgent);

    /**
     * hasAlert
     *
     * @return is exist
     */
    @Returns("exist")
    boolean hasAlert();

    /**
     * alert text
     *
     * @return text
     */
    @Returns("text")
    String alertText();

    /**
     * close alert
     */
    void closeAlert();

    /**
     * has confirm
     *
     * @return is exist
     */
    @Returns("exist")
    boolean hasConfirm();

    /**
     * confirm text
     *
     * @return text
     */
    @Returns("text")
    String confirmText();

    /**
     * close confirm
     *
     * @param accept is accept
     */
    void acceptConfirm(@Param("accept") boolean accept);

    /**
     * has prompt
     *
     * @return exist
     */
    @Returns("exist")
    boolean hasPrompt();

    /**
     * set prompt value
     *
     * @param text text
     */
    void setPromptValue(@Param("text") String text);

    /**
     * accept prompt
     *
     * @param accept is accept
     */
    void acceptPrompt(@Param("accept") boolean accept);

    /**
     * set html
     *
     * @param html    html
     * @param baseUrl base url
     */
    void setHtml(@Param("html") String html, @Param("baseUrl") String baseUrl);

    /**
     * to html
     *
     * @return html
     */
    @Returns("html")
    String toHtml();

    /**
     * to plain text
     *
     * @return text
     */
    @Returns("text")
    String toPlainText();

    /**
     * set scrollbar policy
     *
     * @param horizontalPolicy horizontal policy
     * @param verticalPolicy   vertical policy
     */
    void setScrollBarPolicy(@Param("horizontalPolicy") ScrollBarPolicy horizontalPolicy, @Param("verticalPolicy") ScrollBarPolicy verticalPolicy);

    /**
     * captureScreenshot
     *
     * @param format  format
     * @param quality quality [0-100]
     * @param clip    viewport
     * @return base-encoded image
     */
    @Returns("data")
    String captureScreenshot(@Param("format") ImageFormat format, @Param("quality") int quality, @Param("clip") Viewport clip);

    /**
     * captureScreenshot
     *
     * @param fullScreen is fullScreen
     * @return base-encoded image
     */
    @Returns("data")
    String captureScreenshot(@Param("fullScreen") boolean fullScreen);
}
