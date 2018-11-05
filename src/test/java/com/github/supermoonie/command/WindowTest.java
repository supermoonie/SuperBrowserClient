package com.github.supermoonie.command;

import com.github.supermoonie.browser.SuperBrowser;
import com.github.supermoonie.type.browser.Bounds;
import com.github.supermoonie.type.browser.Version;
import com.github.supermoonie.type.browser.WindowState;
import org.junit.Test;

import java.io.IOException;
import java.net.ConnectException;
import java.nio.file.Paths;

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
    public void hasAlert() throws ConnectException {
        SuperBrowser superBrowser = new SuperBrowser();
        String path = Paths.get("src/test/resources/AlertTest.html").toAbsolutePath().toUri().toString();
        superBrowser.navigateUntilAlert(path, 5000);
        boolean exist = superBrowser.getWindow().hasAlert();
        System.out.println("exist: " + exist);
    }

    @Test
    public void alertText() throws ConnectException {
        SuperBrowser superBrowser = new SuperBrowser();
        String path = Paths.get("src/test/resources/AlertTest.html").toAbsolutePath().toUri().toString();
        superBrowser.navigateUntilAlert(path, 5000);
        boolean exist = superBrowser.getWindow().hasAlert();
        if (exist) {
            String text = superBrowser.getWindow().alertText();
            System.out.println("text: " + text);
        }
    }

    @Test
    public void closeAlert() throws ConnectException {
        SuperBrowser superBrowser = new SuperBrowser();
        String path = Paths.get("src/test/resources/AlertTest.html").toAbsolutePath().toUri().toString();
        superBrowser.navigateUntilAlert(path, 3000);
        boolean exist = superBrowser.getWindow().hasAlert();
        if (exist) {
            String text = superBrowser.getWindow().alertText();
            System.out.println("text: " + text);
            superBrowser.getWindow().closeAlert();
        }
    }

    @Test
    public void hasConfirm() throws ConnectException {
        SuperBrowser superBrowser = new SuperBrowser();
        String path = Paths.get("src/test/resources/ConfirmTest.html").toAbsolutePath().toUri().toString();
        superBrowser.navigateUntilConfirm(path, 5000);
    }

    @Test
    public void confirmText() throws ConnectException {
        SuperBrowser superBrowser = new SuperBrowser();
        String path = Paths.get("src/test/resources/ConfirmTest.html").toAbsolutePath().toUri().toString();
        superBrowser.navigateUntilConfirm(path, 5000);
        String text = superBrowser.getWindow().confirmText();
        System.out.println("text: " + text);
    }

    @Test
    public void acceptConfirm() throws ConnectException {
        SuperBrowser superBrowser = new SuperBrowser();
        String path = Paths.get("src/test/resources/ConfirmTest.html").toAbsolutePath().toUri().toString();
        superBrowser.navigateUntilConfirm(path, 5000);
        superBrowser.getWindow().acceptConfirm(false);
    }

    @Test
    public void close() throws IOException {
        SuperBrowser superBrowser = new SuperBrowser();
        superBrowser.close();
    }
}