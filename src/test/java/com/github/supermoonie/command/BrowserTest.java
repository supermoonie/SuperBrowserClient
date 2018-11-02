package com.github.supermoonie.command;

import com.github.supermoonie.SuperBrowser;
import com.github.supermoonie.type.browser.Version;
import com.github.supermoonie.type.browser.WindowState;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * @author supermoonie
 * @date 2018/11/2 17:30
 */
public class BrowserTest {

    @Test
    public void getVersion() throws IOException {
        SuperBrowser superBrowser = new SuperBrowser();
        Version version = superBrowser.getBrowser().getVersion();
        System.out.println(version.toString());
        superBrowser.close();
    }

    @Test
    public void navigate() throws IOException {
        SuperBrowser superBrowser = new SuperBrowser();
        superBrowser.getBrowser().navigate("http://httpbin.org/get");
        superBrowser.close();
    }

    @Test
    public void getWindowState() throws IOException {
        SuperBrowser superBrowser = new SuperBrowser();
        WindowState windowState = superBrowser.getBrowser().getWindowState();
        System.out.println(windowState);
        superBrowser.close();
    }

    @Test
    public void setWindowState() throws IOException {
        SuperBrowser superBrowser = new SuperBrowser();
        superBrowser.getBrowser().setWindowState(WindowState.normal);
        superBrowser.close();
    }
}