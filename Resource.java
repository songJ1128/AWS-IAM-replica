import java.util.HashMap;
import java.util.Map;

public class Resource {
    private String resourceId;
    private Map<Permission, Boolean> actions;

    public Resource(String resourceId) {
        this.resourceId = resourceId;
        this.actions = new HashMap<>();
        this.actions.put(Permission.READ, true);
        this.actions.put(Permission.WRITE, true);
        this.actions.put(Permission.DELETE, true);
    }

    public String getResourceId() {
        return resourceId;
    }

    public boolean performAction(Permission action) {
        if (actions.getOrDefault(action, false)) {
            System.out.println("Action " + action + " performed on resource " + resourceId);
            return true;
        }
        System.out.println("Action " + action + " not allowed on resource " + resourceId);
        return false;
    }
}
