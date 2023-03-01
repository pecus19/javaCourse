package ee.taltech.iti0202.tk.art;

public class Painting {
    private String title;
    private String author;

    /**
     * @param title  title
     * @param author author
     */
    public Painting(String title, String author) {
        this.title = title;
        this.author = author;
    }

    /**
     * @param title title
     */
    public Painting(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return getAuthor() != null ? String.format("This is a painting named %s and made by %s.",
                getTitle(), getAuthor())
                : String.format("This is a painting named %s and made by an unknown artist.", getTitle());
    }
}
