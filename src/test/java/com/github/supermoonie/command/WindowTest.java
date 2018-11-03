package com.github.supermoonie.command;

import com.github.supermoonie.browser.SuperBrowser;
import com.github.supermoonie.type.browser.Bounds;
import com.github.supermoonie.type.browser.Version;
import com.github.supermoonie.type.browser.WindowState;
import org.junit.Test;

import java.io.IOException;

/**
 * @author supermoonie
 * @date 2018/11/2 17:30
 */
public class WindowTest {


    @Test
    public void getVersion() throws IOException {
        SuperBrowser superBrowser = new SuperBrowser();
        Version version = superBrowser.getWindow().getVersion();
        System.out.println(version.toString());
        superBrowser.close();
    }

    @Test
    public void getWindowState() throws IOException {
        SuperBrowser superBrowser = new SuperBrowser();
        WindowState windowState = superBrowser.getWindow().getWindowState();
        System.out.println(windowState);
        superBrowser.close();
    }

    @Test
    public void setWindowState() throws IOException {
        SuperBrowser superBrowser = new SuperBrowser();
        superBrowser.getWindow().setWindowState(WindowState.normal);
        superBrowser.close();
    }

    @Test
    public void getWindowBounds() throws IOException {
        SuperBrowser superBrowser = new SuperBrowser();
        Bounds windowBounds = superBrowser.getWindow().getWindowBounds();
        System.out.println(windowBounds);
        superBrowser.close();
    }

    @Test
    public void setWindowBounds() throws IOException {
        SuperBrowser superBrowser = new SuperBrowser();
        Bounds bounds = new Bounds();
        bounds.setX(0);
        bounds.setX(0);
        bounds.setWidth(800);
        bounds.setHeight(600);
        superBrowser.getWindow().setWindowBounds(bounds);
        superBrowser.getWindow().setWindowBounds(0, 0, 800, 600);
        superBrowser.close();
    }

    @Test
    public void close() throws IOException {
        SuperBrowser superBrowser = new SuperBrowser();
        superBrowser.close();
    }
}