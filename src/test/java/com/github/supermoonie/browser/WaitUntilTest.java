package com.github.supermoonie.browser;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;

import static org.junit.Assert.*;

/**
 * @author wangchao
 * @date 2018/11/3 23:26
 */
public class WaitUntilTest {

    @Test
    public void navigateUntilLoadFinished() {
        SuperBrowser superBrowser = new SuperBrowser();
        superBrowser.navigateUntilLoadFinished("http://httpbin.org/get", 5000);
    }

    @Test
    public void navigateUntilConfirm() {
        SuperBrowser superBrowser = new SuperBrowser();
        String path = Paths.get("src/test/resources/ConfirmTest.html").toAbsolutePath().toUri().toString();
        superBrowser.navigateUntilConfirm(path, 5000);
    }
}