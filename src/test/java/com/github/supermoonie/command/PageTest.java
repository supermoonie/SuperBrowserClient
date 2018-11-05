package com.github.supermoonie.command;

import com.github.supermoonie.browser.SuperBrowser;
import org.junit.Test;

import java.io.IOException;
import java.net.ConnectException;
import java.nio.file.Paths;

/**
 * @author supermoonie
 * @date 2018/11/2 17:44
 */
public class PageTest {

    @Test
    public void navigate() throws ConnectException {
        SuperBrowser superBrowser = new SuperBrowser();
        String path = Paths.get("src/test/resources/PromptTest.html").toAbsolutePath().toUri().toString();
        superBrowser.getPage().navigate(path);
    }

    @Test
    public void currentUrl() throws ConnectException {
        SuperBrowser superBrowser = new SuperBrowser();
        String currentUrl = superBrowser.navigateUntilLoadFinished("http://httpbin.org/get", 5000).getPage().currentUrl();
        System.out.println("currentUrl: " + currentUrl);
    }

    @Test
    public void getUserAgent() throws IOException {
        SuperBrowser superBrowser = new SuperBrowser();
        String userAgent = superBrowser.getPage().getUserAgent();
        System.out.println(userAgent);
        superBrowser.close();
    }

    @Test
    public void setUserAgent() throws IOException {
        SuperBrowser superBrowser = new SuperBrowser();
        superBrowser.getPage().setUserAgent("Hello SuperBrowser!");
        superBrowser.close();
    }
}