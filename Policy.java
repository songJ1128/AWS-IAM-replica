import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Policy {
    private String name;
    private Set<Permission> permissions;
    private Set<String> resources; 
    private Map<String, Condition> conditions; 
    private String version; 
    private Policy parentPolicy;

    public Policy(String name, Set<Permission> permissions, Set<String> resources, String version) {
        this.name = name;
        this.permissions = permissions;
        this.resources = resources;
        this.version = version;
        this.conditions = new HashMap<>();
        this.parentPolicy = null;
    }
    public String getName() {
        return name;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<String> getResources() {
        return resources;
    }

    public String getVersion() {
        return version;
    }

    public void addCondition(String key, Condition condition) {
        conditions.put(key, condition);
    }

    public boolean evaluateConditions(Map<String, String> context) {
        for (Condition condition : conditions.values()) {
            if (!condition.evaluate(context)) {
                return false;
            }
        }
        return true;
    }


    public void setParentPolicy(Policy parentPolicy) {
        this.parentPolicy = parentPolicy;
    }


    public boolean isActionAllowed(Permission action, String resource, Map<String, String> context) {
        if (permissions.contains(action) && resources.contains(resource) && evaluateConditions(context)) {
            return true;
        }
        if (parentPolicy != null) {
            return parentPolicy.isActionAllowed(action, resource, context);
        }

        return false;
    }
    public void updateVersion(String newVersion) {
        this.version = newVersion;
    }

    @Override
    public String toString() {
        return "Policy{" +
                "name='" + name + '\'' +
                ", permissions=" + permissions +
                ", resources=" + resources +
                ", version='" + version + '\'' +
                ", conditions=" + conditions +
                ", parentPolicy=" + (parentPolicy != null ? parentPolicy.getName() : "none") +
                '}';
    }
}


