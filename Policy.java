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