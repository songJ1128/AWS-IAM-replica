import java.util.Objects;

public class Permission {
    private String action;       // e.g., READ, WRITE, EXECUTE
    private String resource;     // e.g., "Resource1", "Bucket1"

    public Permission(String action, String resource) {
        this.action = action.toUpperCase(); // Ensure consistency with uppercase actions
        this.resource = resource;
    }

    public String getAction() {
        return action;
    }

    public String getResource() {
        return resource;
    }

    // Check if this permission matches another permission
    public boolean matches(Permission other) {
        boolean actionMatches = this.action.equals(other.getAction());
        boolean resourceMatches = this.resource.equals("*") || this.resource.equals(other.getResource());
        return actionMatches && resourceMatches;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "action='" + action + '\'' +
                ", resource='" + resource + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permission that = (Permission) o;
        return Objects.equals(action, that.action) && Objects.equals(resource, that.resource);
    }

    @Override
    public int hashCode() {
        return Objects.hash(action, resource);
    }
}
