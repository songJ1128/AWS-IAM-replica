public class IAM {
    private Map<String, User> users;
    private Map<String, Role> roles;
    private Map<String, Group> groups;
    private AuditLog auditLog;

    public IAM() {
        users = new HashMap<>();
        roles = new HashMap<>();
        groups = new HashMap<>();
        auditLog = new AuditLog();
    }

    public void createUser(String username) {
        users.put(username, new User(username));
    }

    public void createRole(String roleName) {
        roles.put(roleName, new Role(roleName));
    }

    public void createGroup(String groupName) {
        groups.put(groupName, new Group(groupName));
    }

    public void assignRoleToUser(String username, String roleName) {
        User user = users.get(username);
        Role role = roles.get(roleName);
        if (user != null && role != null) {
            user.addRole(role);
            auditLog.log("Assigned role " + roleName + " to user " + username);
        }
    }

    public void assignRoleToGroup(String groupName, String roleName) {
        Group group = groups.get(groupName);
        Role role = roles.get(roleName);
        if (group != null && role != null) {
            group.addRole(role);
            auditLog.log("Assigned role " + roleName + " to group " + groupName);
        }
    }

    public void addPolicyToRole(String roleName, String policyName, Set<Permission> permissions, Set<String> resources, String version) {
        Role role = roles.get(roleName);
        if (role != null) {
            role.addPolicy(new Policy(policyName, permissions, resources, version));
            auditLog.log("Added policy " + policyName + " to role " + roleName);
        }
    }

    public boolean checkPermission(String username, Permission requiredPermission, String resource, Map<String, String> context) {
        User user = users.get(username);
        Set<Role> userRoles = new HashSet<>(user.getRoles());

        for (Group group : user.getGroups()) {
            userRoles.addAll(group.getRoles());
        }

        for (Role role : userRoles) {
            for (Policy policy : role.getPolicies()) {
                if (policy.getPermissions().contains(requiredPermission) &&
                    policy.getResources().contains(resource) &&
                    policy.evaluateConditions(context)) {
                    if (user.isMfaEnabled() && !user.isMfaVerified()) {
                        auditLog.log("MFA not verified for user " + username);
                        return false; 
                    }
                    auditLog.log(username + " granted " + requiredPermission + " on " + resource);
                    return true; 
                }
            }

            
            for (Role childRole : role.getChildRoles()) {
                for (Policy policy : childRole.getPolicies()) {
                    if (policy.getPermissions().contains(requiredPermission) &&
                        policy.getResources().contains(resource) &&
                        policy.evaluateConditions(context)) {
                        if (user.isMfaEnabled() && !user.isMfaVerified()) {
                            auditLog.log("MFA not verified for user " + username);
                            return false; 
                        }
                        auditLog.log(username + " granted " + requiredPermission + " on " + resource + " through child role " + childRole.getName());
                        return true; 
                    }
                }
            }
        }
        auditLog.log(username + " denied " + requiredPermission + " on " + resource);
        return false; 
    }

    public AuditLog getAuditLog() {
        return auditLog;
    }
}