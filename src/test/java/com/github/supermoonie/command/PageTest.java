package com.github.supermoonie.command;

import com.github.supermoonie.browser.SuperBrowser;
import org.junit.Test;

import java.io.IOException;

/**
 * @author supermoonie
 * @date 2018/11/2 17:44
 */
public class PageTest {

    @Test
    public void navigate() {
        SuperBrowser superBrowser = new SuperBrowser();
        superBrowser.getPage().navigate("http://httpbin.org/get");
    }

    @Test
    public void currentUrl() {
        SuperBrowser superBrowser = new SuperBrowser();
        superBrowser.navigateUntilLoadFinished("http://httpbin.org/get", 5000);
        String currentUrl = superBrowser.getPage().currentUrl();
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