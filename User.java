import java.util.HashSet;
import java.util.Set;

public class User {
    private String username;
    private Set<Role> roles;
    private Set<Group> groups;
    private boolean mfaEnabled; 
    private boolean mfaVerified;

    public User(String username) {
        this.username = username;
        this.roles = new HashSet<>();
        this.groups = new HashSet<>();
        this.mfaEnabled = false;
        this.mfaVerified = false;
    }

    public String getUsername() {
        return username;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public boolean isMfaEnabled() {
        return mfaEnabled;
    }

    public boolean isMfaVerified() {
        return mfaVerified;
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public void removeRole(Role role) {
        roles.remove(role);
    }

    public void addGroup(Group group) {
        groups.add(group);
    }

    public void enableMfa() {
        this.mfaEnabled = true;
    }

    public void verifyMfa() {
        this.mfaVerified = true;
    }

    public void unverifyMfa() {
        this.mfaVerified = false;
    }
}