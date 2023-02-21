package ee.taltech.iti0202.socialnetwork;

import ee.taltech.iti0202.socialnetwork.feed.Feed;
import ee.taltech.iti0202.socialnetwork.group.Group;
import ee.taltech.iti0202.socialnetwork.message.Message;
import ee.taltech.iti0202.socialnetwork.user.User;

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
        HashSet<Message> newGroup = new HashSet<>();
        for (Group group : groups) {
            if (group.getParticipants().contains(user)) {
                newGroup.addAll(group.getMessages());
            }
        }
        return new Feed(user, newGroup);
    }
}
