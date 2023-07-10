package ee.taltech.iti0202.springboot.socialnetwork;

import ee.taltech.iti0202.springboot.socialnetwork.feed.Feed;
import ee.taltech.iti0202.springboot.socialnetwork.group.Group;
import ee.taltech.iti0202.springboot.socialnetwork.message.Message;
import ee.taltech.iti0202.springboot.socialnetwork.user.User;

import java.util.HashSet;
import java.util.Set;

public class SocialNetwork {
    private final Set<Group> groups = new HashSet<>();

    /**
     * @param group group
     */
    public void registerGroup(Group group) {
        if (group != null) {
            groups.add(group);
        }

    }

    public Set<Group> getGroups() {
        return groups;
    }

    /**
     * @param user user
     * @return Feed
     */
    public Feed getFeedForUser(User user) {
        HashSet<Message> messages = new HashSet<>();
        for (Group group : groups) {
            if (group.getParticipants().contains(user)) {
                messages.addAll(group.getMessages());
            }
        }
        return new Feed(user, messages);
    }
}
