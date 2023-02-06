package ee.taltech.iti0202.webbrowser;

import static org.junit.jupiter.api.Assertions.*;

class WebBrowserTest {
    WebBrowser webBrowser = new WebBrowser();

    @org.junit.jupiter.api.Test
    void basicCurrentTest() {
        assertEquals("google.com", webBrowser.getCurrentUrl());
    }

    @org.junit.jupiter.api.Test
    void goToCurrentTest() {
        webBrowser.goTo("taltech.ee");
        assertEquals("taltech.ee", webBrowser.getCurrentUrl());
    }

    @org.junit.jupiter.api.Test
    void goToAndBackCurrentTest() {
        webBrowser.goTo("taltech.ee");
        webBrowser.back();
        assertEquals("google.com", webBrowser.getCurrentUrl());
    }

    @org.junit.jupiter.api.Test
    void goToAndBackAndForwardCurrentTest() {
        webBrowser.goTo("taltech.ee");
        webBrowser.back();
        webBrowser.forward();
        assertEquals("taltech.ee", webBrowser.getCurrentUrl());
    }

    @org.junit.jupiter.api.Test
    void homePageTest() {
        webBrowser.setHomePage("taltech.com");
        webBrowser.homePage();
        assertEquals("taltech.com", webBrowser.getCurrentUrl());
    }

    @org.junit.jupiter.api.Test
    void getBookmarksTest() {
        webBrowser.goTo("neti.ee");
        webBrowser.goTo("jalgpall.ee");
        webBrowser.addAsBookmark();
        webBrowser.back();
        webBrowser.addAsBookmark();
        webBrowser.removeBookmark("jalgpall.ee");
        assertEquals("neti.ee", webBrowser.getBookmarks().get(0));
    }

    @org.junit.jupiter.api.Test
    void getHistoryTest() {
        webBrowser.goTo("neti.ee");
        webBrowser.goTo("jalgpall.ee");
        webBrowser.back();
        webBrowser.addAsBookmark();
        assertEquals(4, webBrowser.getHistory().size());
    }
//    @org.junit.jupiter.api.Test
//    void getTopThreeTest() {
//        webBrowser.goTo("neti.ee");
//        webBrowser.goTo("jalgpall.ee");
//        webBrowser.back();
//        webBrowser.addAsBookmark();
//        webBrowser.back();
//        webBrowser.forward();
//        assertEquals(4, webBrowser.getTop3VisitedPages());
//    }


}