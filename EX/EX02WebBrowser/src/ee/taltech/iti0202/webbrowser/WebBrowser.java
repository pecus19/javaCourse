package ee.taltech.iti0202.webbrowser;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class WebBrowser {
    private String homePage;
    private final ArrayList<String> history = new ArrayList<>();
    private final ArrayList<String> bookmarks = new ArrayList<>();
    private String currentPage = "google.com";
    private Integer counter = 0;
    private boolean homeCheck = false;
    private final Map<String, Integer> map = new TreeMap<>();
    public static final int ONE = 1;
    public static final int ZERO = 0;
    public static final int THREE = 3;

    /**
     * Goes to homepage.
     */
    public void homePage() {
        homeCheck = true;
        currentPage = homePage;
        goTo(homePage);
    }

    /**
     * Goes back to previous page.
     */
    public void back() {
        if (counter - ONE >= ZERO) {
            counter--;
            currentPage = history.get(counter);
            history.add(history.get(counter));
        }
    }

    /**
     * Goes forward to next page.
     */
    public void forward() {
        if (counter + ONE <= history.size() - ONE) {
            counter++;
            currentPage = history.get(counter);
            history.add(history.get(counter));
        }
    }

    /**
     * Go to a webpage.
     *
     * @param url url to go to
     */
    public void goTo(String url) {
        if (!homeCheck) {
            if (url != null) {
                history.add(url);
                counter++;
                currentPage = url;
            }
        } else {
            if (url != null) {
                history.add(url);
                counter = history.size();
                homeCheck = false;

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
//        System.out.println(map);
        map.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed().thenComparing(Map.Entry::getKey))
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
        return currentPage;
    }

    public static void main(String[] args) {
        WebBrowser test = new WebBrowser();
//        System.out.println(test.getCurrentUrl()); // - > "google.com"
//        test.setHomePage("neti.ee"); //
//        test.goTo("facebook.com"); //
//        test.back(); //
//        test.back(); //
//        System.out.println(test.getHistory()); // - > [google.com, facebook.com, google.com]
//        test.setHomePage("neti.ee"); //
//        test.goTo("facebook.com"); //
//        test.forward(); //
//        test.forward(); //
//        System.out.println(test.getHistory()); //- > [google.com, facebook.com]

//        test.setHomePage("neti.ee"); //
//        test.goTo("facebook.com"); //
//        test.back(); //
//        test.homePage(); //
//        test.forward(); //
//        System.out.println(test.getHistory()); // - >[google.com, facebook.com, google.com, neti.ee]

//        System.out.println(test.getCurrentUrl()); // - > "google.com"
//        test.setHomePage("neti.ee"); //
//        test.goTo("facebook.com"); //
//        System.out.println(test.getCurrentUrl()); // - > "facebook.com"
//        test.goTo("google.com"); //
//        System.out.println(test.getCurrentUrl()); // - > "google.com"
//        test.back(); //
//        System.out.println(test.getCurrentUrl()); // - > "facebook.com"
//        test.addAsBookmark(); //
//        System.out.println(test.getBookmarks());
//        test.goTo("neti.ee");
//        test.goTo("jalgpall.ee");
//        test.back();
//        test.addAsBookmark();
//        test.back();
//        test.forward();
//        System.out.println(test.getTop3VisitedPages());
//        System.out.println(test.getCurrentUrl()); // - > "google.com"
//        test.setHomePage("neti.ee"); //
//        test.goTo("facebook.com"); //
//        System.out.println(test.getCurrentUrl()); // - > "facebook.com"
//        test.goTo("google.com"); //
//        System.out.println(test.getCurrentUrl()); //  - > "google.com"
//        test.back(); //
//        System.out.println(test.getCurrentUrl()); //  - > "facebook.com"
//        test.addAsBookmark(); //
//        test.forward(); //
//        System.out.println(test.getCurrentUrl()); //  - > "google.com"
//        test.homePage(); //
//        System.out.println(test.getCurrentUrl()); // - > "neti.ee"
//        test.addAsBookmark(); //
//        System.out.println(test.getBookmarks()); // - > [facebook.com, neti.ee]
//        test.goTo("taltech"); //
//        System.out.println(test.getHistory()); //  - > [google.com, facebook.com, google.com, facebook.com,
//        google.com, neti.ee]
//        System.out.println(test.getTop3VisitedPages());
        test.goTo("twitter.com ");
        test.back();
        test.back();
        test.back();
        test.back();
        test.back();
        System.out.println(test.getTop3VisitedPages());

    }
}
