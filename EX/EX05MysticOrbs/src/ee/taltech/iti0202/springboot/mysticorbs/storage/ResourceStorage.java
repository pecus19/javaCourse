package ee.taltech.iti0202.springboot.mysticorbs.storage;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ResourceStorage {
    private Map<String, Integer> resources = new HashMap<>();

    /**
     * @return check
     */
    public boolean isEmpty() {
        boolean check = true;
        if (resources.size() >= 1) {
            for (Map.Entry<String, Integer> entry : resources.entrySet()) {
                if (entry.getValue() > 0) {
                    check = false;
                    break;
                }
            }
        }
        return check;
    }

    /**
     * @param resource resource
     * @param amount   amount
     */
    public void addResource(String resource, int amount) {
        if (!resource.toLowerCase().trim().isEmpty() && amount > 0) {
            if (!resources.containsKey(resource.toLowerCase().trim())) {
                resources.put(resource.toLowerCase().trim(), amount);
            } else {
                resources.put(resource.toLowerCase().trim(), resources.get(resource.toLowerCase().trim()) + amount);
            }
        }
    }

    /**
     * @param resource resource
     * @return int
     */
    public int getResourceAmount(String resource) {
        if (resource != null && resources.containsKey(resource.toLowerCase(Locale.ROOT))) {
            return resources.get(resource.toLowerCase(Locale.ROOT));
        } else {
            return 0;
        }
    }

    /**
     * @param resource resource
     * @param amount   amount
     * @return false
     */
    public boolean hasEnoughResource(String resource, int amount) {
        if (amount >= 1 && resources.containsKey(resource.toLowerCase(Locale.ROOT))) {
            return resources.get(resource.toLowerCase(Locale.ROOT)) >= amount;
        } else {
            return false;
        }
    }

    /**
     * @param resource resource
     * @param amount   amount
     * @return false
     */
    public boolean takeResource(String resource, int amount) {
        if (amount >= 1 && resources.containsKey(resource)) {
            if (resources.get(resource.toLowerCase(Locale.ROOT)) - amount >= 0) {
                resources.put(resource.toLowerCase(Locale.ROOT), resources.get(resource.toLowerCase(Locale.ROOT))
                        - amount);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
