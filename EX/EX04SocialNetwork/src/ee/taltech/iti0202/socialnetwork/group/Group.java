package ee.taltech.iti0202.socialnetwork.group;

import ee.taltech.iti0202.socialnetwork.user.User;
import ee.taltech.iti0202.socialnetwork.message.Message;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Group {
    private String name;
    private User owner;
    private final Set<User> users = new HashSet<>();
    private final List<Message> messages = new ArrayList<>();

    /**
     * @param name  name
     * @param owner owner
     */
    public Group(String name, User owner) {
        this.name = name;
        this.owner = owner;
        users.add(owner);
    }


    public String getName() {
        return name;
    }

    /**
     * @param user user
     */
    public void addUser(User user) {
        if (user != null) {
            users.add(user);
        }
    }

    /**
     * @return User
     */
    public Set<User> getParticipants() {
        return users;
    }

    /**
     * @param message message
     */
    public void publishMessage(Message message) {
        if (users.contains(message.getAuthor())) {
            messages.add(message);
        }
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
