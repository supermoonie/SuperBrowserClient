package com.github.supermoonie.browser;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * @author wangchao
 * @date 2018/11/3 23:26
 */
public class WaitUntilTest {

    @Test
    public void navigateUntilLoadFinished() throws InterruptedException {
        SuperBrowser superBrowser = new SuperBrowser();
        superBrowser.navigateUntilLoadFinished("http://httpbin.org/get", 5000);
    }
}