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

    @Test
    public void alertText() throws ConnectException {
        SuperBrowser superBrowser = new SuperBrowser();
        String path = Paths.get("src/test/resources/AlertTest.html").toAbsolutePath().toUri().toString();
        superBrowser.navigateUntilAlert(path, 5000);
        boolean exist = superBrowser.getPage().hasAlert();
        if (exist) {
            String text = superBrowser.getPage().alertText();
            System.out.println("text: " + text);
        }
    }

    @Test
    public void closeAlert() throws ConnectException {
        SuperBrowser superBrowser = new SuperBrowser();
        String path = Paths.get("src/test/resources/AlertTest.html").toAbsolutePath().toUri().toString();
        superBrowser.navigateUntilAlert(path, 3000);
        boolean exist = superBrowser.getPage().hasAlert();
        if (exist) {
            String text = superBrowser.getPage().alertText();
            System.out.println("text: " + text);
            superBrowser.getPage().closeAlert();
        }
    }

    @Test
    public void confirmText() throws ConnectException {
        SuperBrowser superBrowser = new SuperBrowser();
        String path = Paths.get("src/test/resources/ConfirmTest.html").toAbsolutePath().toUri().toString();
        superBrowser.navigateUntilConfirm(path, 5000);
        String text = superBrowser.getPage().confirmText();
        System.out.println("text: " + text);
    }

    @Test
    public void acceptConfirm() throws ConnectException {
        SuperBrowser superBrowser = new SuperBrowser();
        String path = Paths.get("src/test/resources/ConfirmTest.html").toAbsolutePath().toUri().toString();
        superBrowser.navigateUntilConfirm(path, 5000);
        superBrowser.getPage().acceptConfirm(false);
    }

    @Test
    public void setPromptValue() throws ConnectException {
        SuperBrowser superBrowser = new SuperBrowser();
        String path = Paths.get("src/test/resources/PromptTest.html").toAbsolutePath().toUri().toString();
        superBrowser.navigateUntilPrompt(path, 5000).getPage().setPromptValue("Hello SuperBrowser!");
//        superBrowser.getPage().setPromptValue("Hello SuperBrowser!");
    }

    @Test
    public void acceptPrompt() throws ConnectException {
        SuperBrowser superBrowser = new SuperBrowser();
        String path = Paths.get("src/test/resources/PromptTest.html").toAbsolutePath().toUri().toString();
        superBrowser.navigateUntilPrompt(path, 5000).getPage().setPromptValue("Hello SuperBrowser!");
        superBrowser.getPage().acceptPrompt(true);
    }

    @Test
    public void setHtml() throws ConnectException {
        SuperBrowser superBrowser = new SuperBrowser();
        String html = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>PromptTest</title>" +
                "</head>\n" +
                "<body>\n" +
                "<h1>SetHtml Test</h1>\n" +
                "</body>\n" +
                "</html>";
        superBrowser.getPage().setHtml(html, "");
    }

    @Test
    public void toHtml() throws ConnectException {
        SuperBrowser superBrowser = new SuperBrowser();
        String html = superBrowser.navigateUntilLoadFinished("http://httpbin.org/get", 5000).getPage().toHtml();
        System.out.println("html: " + html);
    }

    @Test
    public void toPlainText() throws ConnectException {
        SuperBrowser superBrowser = new SuperBrowser();
        String text = superBrowser.navigateUntilLoadFinished("http://httpbin.org/get", 5000).getPage().toPlainText();
        System.out.println("text: " + text);
    }
}