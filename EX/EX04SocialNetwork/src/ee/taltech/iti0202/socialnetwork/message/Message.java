package ee.taltech.iti0202.socialnetwork.message;

import ee.taltech.iti0202.socialnetwork.user.User;

public class Message {
    private String title;
    private String message;
    private User author;

    public Message(String title, String message, User author) {
        this.title = title;
        this.message = message;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

//    @Override
//    public String toString() {
//        return "Message{" +
//                "title='" + title + '\'' +
//                ", message='" + message + '\'' +
//                ", author=" + author +
//                '}';
//    }
}

