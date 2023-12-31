package ee.taltech.iti0202.springboot.socialnetwork.feed;

import ee.taltech.iti0202.springboot.socialnetwork.message.Message;
import ee.taltech.iti0202.springboot.socialnetwork.user.User;

import java.util.Set;

public class Feed {
    private User user;
    private Set<Message> messages;

    /**
     * @param user     ser
     * @param messages messages
     */
    public Feed(User user, Set<Message> messages) {
        this.user = user;
        this.messages = messages;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }
}
