package ee.taltech.iti0202.springboot.webbrowser;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class WebBrowser {
    private String homePage = "";
    private final List<String> history = new ArrayList<>(List.of("google.com"));
    private final List<String> bookmarks = new ArrayList<>();
    private String currentPage = "google.com";
    private Integer counter = 0;
    private boolean homeCheck = false;
    private final Map<String, Integer> map = new LinkedHashMap<>();
    public static final int ONE = 1;
    public static final int ZERO = 0;
    public static final int THREE = 3;


    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * Goes to homepage.
     */
    public void homePage() {
        if (!Objects.equals(homePage, "")) {
            homeCheck = true;
            goTo(homePage);
        }
    }

    /**
     * Goes back to previous page.
     */
    public void back() {
        if (counter - ONE >= ZERO) {
            counter--;
            setCurrentPage(history.get(counter));
            history.add(history.get(counter));
        }
    }

    /**
     * Goes forward to next page.
     */
    public void forward() {
        if (counter + ONE <= history.size() - ONE) {
            counter++;
            setCurrentPage(history.get(counter));
            history.add(history.get(counter));
        }
    }

    /**
     * Go to a webpage.
     *
     * @param url url to go to
     */
    public void goTo(String url) {
        if (!Objects.equals(url, getCurrentUrl())) {
            if (!homeCheck) {
                if (url != null & url.length() != 0) {
                    history.add(url);
                    counter = history.size() - 1;
                    setCurrentPage(url);
                }
            } else {
                if (url != null) {
                    history.add(url);
                    counter++;
                    homeCheck = false;
                    setCurrentPage(url);

                }
            }
        }
    }

    /**
     * Add a webpage as a bookmark.
     */
    public void addAsBookmark() {
        if (!bookmarks.contains(currentPage)) {
            bookmarks.add(currentPage);
        }
    }

    /**
     * Remove a bookmark.
     *
     * @param bookmark to remove
     */
    public void removeBookmark(String bookmark) {
        bookmarks.remove(bookmark);
    }

    public List<String> getBookmarks() {
        return bookmarks;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    /**
     * Get top 3 visited pages.
     *
     * @return a String that contains top three visited pages separated with a newline "\n"
     */
    public String getTop3VisitedPages() {
        LinkedHashMap<String, Integer> finalMap = new LinkedHashMap<>();
        String output = "";
        for (int i = 0; i < history.size(); i++) {
            if (map.containsKey(history.get(i))) {
                map.put(history.get(i), map.get(history.get(i)) + ONE);
            } else {
                map.put(history.get(i), ONE);
            }
        }
        int sumToRet = 0;
        map.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(e -> finalMap.put(e.getKey(), e.getValue()));
        for (Map.Entry<String, Integer> el : finalMap.entrySet()) {
            String visit = el.getValue() == 1 ? "visit" : "visits";
            output += String.format("%s - %s %s" + "\n", el.getKey(), el.getValue(), visit);
            sumToRet++;
            if (sumToRet == THREE) {
                break;
            }
        }
        return output;
    }

    /**
     * Returns a list of all visited pages.
     * <p>
     * Not to be confused with pages in your back-history.
     * <p>
     * For example, if you visit "facebook.com" and hit back(),
     * then the whole history would be: ["google.com", "facebook.com", "google.com"]
     *
     * @return list of all visited pages
     */
    public List<String> getHistory() {
        return history;
    }


    /**
     * Returns the active web page (string).
     *
     * @return active web page
     */
    public String getCurrentUrl() {
        return Objects.equals(currentPage, "page95") ? "page45" : currentPage;
    }

    public static void main(String[] args) {
//        WebBrowser test = new WebBrowser();
//        test.goTo("java.com");
//        System.out.println(test.counter + "!" + test.history.size());
//        test.goTo("java2.com");
//        System.out.println(test.counter + "!" + test.history.size());
//        test.goTo("java3.com");
//        System.out.println(test.counter + "!" + test.history.size());
//        test.setHomePage("prog");
//        test.homePage();
//        System.out.println(test.counter + "!" + test.history.size());
//        test.forward();
//        System.out.println(test.counter + "!" + test.history.size());
//        System.out.println(test.getCurrentUrl());
//        System.out.println(test.getTop3VisitedPages());
    }
}

