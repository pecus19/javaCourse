package ee.taltech.iti0202.socialnetwork;


import ee.taltech.iti0202.socialnetwork.feed.Feed;
import ee.taltech.iti0202.socialnetwork.group.Group;
import ee.taltech.iti0202.socialnetwork.message.Message;
import ee.taltech.iti0202.socialnetwork.user.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SocialNetworkTest {
    public static final int AGE = 10;

    @org.junit.jupiter.api.Test
    void addUserToGroupTest() {
        User user = new User("Ago", AGE);
        User user2 = new User("Danila", AGE);
        Group group = new Group("group1", user);
        group.addUser(user2);
        assertEquals(2, group.getParticipants().size());
    }

    @org.junit.jupiter.api.Test
    void addMessageToGroupTest() {
        List<Message> list = new ArrayList<>();
        User user = new User("Ago");

        Group group = new Group("group1", user);
        Message message = new Message("Message1", "This is message1", user);
        group.publishMessage(message);
        list.add(message);
        assertEquals(list, group.getMessages());
    }

    @org.junit.jupiter.api.Test
    void gettersSettersTest() {
        User user3 = new User("User3", AGE);
        User user4 = new User("User4", AGE);
        assertEquals("User3", user3.getName());
        assertEquals(AGE, user3.getAge());
        Message message3 = new Message("", "", user3);
        message3.setMessage("This is message");
        message3.setAuthor(user4);
        message3.setTitle("Title1");
        assertEquals("This is message", message3.getMessage());
        assertEquals("Title1", message3.getTitle());
        assertEquals(user4, message3.getAuthor());
        Group group3 = new Group("Group3", user3);
        group3.setName("Name");
        group3.setOwner(user4);
        assertEquals("Name", group3.getName());
        assertEquals(user4, group3.getOwner());
        Set<Message> set = new HashSet<>();
        set.add(message3);
        Feed feed4 = new Feed(user3, set);
        feed4.setMessages(set);
        assertEquals(set, feed4.getMessages());
        feed4.setUser(user3);
        assertEquals(user3, feed4.getUser());
    }

    @org.junit.jupiter.api.Test
    void registerGroupTest() {
        User user3 = new User("User3", AGE);
        Group group5 = new Group("Group3", user3);
        SocialNetwork socialNetwork = new SocialNetwork();
        socialNetwork.registerGroup(group5);
        assertEquals(1, socialNetwork.getGroups().size());

    }

    @org.junit.jupiter.api.Test
    void getFeedForUserTest() {
        SocialNetwork socialNetwork = new SocialNetwork();
        User user6 = new User("User6", AGE);
        User user7 = new User("User7", AGE);
        Group group6 = new Group("Group6", user7);
        socialNetwork.registerGroup(group6);
        Message message6 = new Message("Title", "Message6", user6);
        Set<Message> set = new HashSet<>();
        set.add(message6);
        Feed feed = new Feed(user6, set);

        group6.addUser(user6);
        group6.publishMessage(message6);
        assertEquals(feed.getUser(), socialNetwork.getFeedForUser(user6).getUser());
    }
}
