package com.github.supermoonie.browser;

import com.github.supermoonie.type.page.ImageFormat;
import com.github.supermoonie.type.page.Viewport;
import org.junit.Test;

import javax.swing.*;
import java.net.ConnectException;
import java.util.Base64;

import static org.junit.Assert.*;

/**
 * @author supermoonie
 * @date 2018/11/6 17:52
 */
public class ScreenshotTest {

    @Test
    public void captureFullScreenshot() throws ConnectException {
        SuperBrowser superBrowser = new SuperBrowser();
        superBrowser.navigateUntilLoadFinished("https://ip.cn", 5000);
        byte[] base64Image = Base64.getDecoder().decode(superBrowser.captureScreenshot(true));
        JOptionPane.showMessageDialog(null, "", "Image", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(base64Image));
    }

    @Test
    public void captureScreenshot() throws ConnectException {
        SuperBrowser superBrowser = new SuperBrowser();
        superBrowser.navigateUntilLoadFinished("https://ip.cn", 5000);
        Viewport viewport = new Viewport();
        viewport.setX(0);
        viewport.setY(0);
        viewport.setHeight(500);
        viewport.setWidth(500);
        byte[] base64Image = Base64.getDecoder().decode(superBrowser.captureScreenshot(ImageFormat.Png, 20, viewport));
        JOptionPane.showMessageDialog(null, "", "Image", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(base64Image));
    }
}