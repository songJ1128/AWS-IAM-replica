import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        IAM iamSystem = new IAM();
        Set<Permission> permissions = new HashSet<>();
        permissions.add(Permission.READ);
        permissions.add(Permission.WRITE);
        Set<String> resources = new HashSet<>();
        resources.add("Document1");
        resources.add("Document2");

        Policy policy1 = new Policy("ReadWritePolicy", permissions, resources, "v1.0");
        policy1.addCondition("Time", new Condition("time", "startsWith", "09:00"));
        Policy policy2 = new Policy("ReadOnlyPolicy", Set.of(Permission.READ), resources, "v1.0");
        policy2.setParentPolicy(policy1); 
        iamSystem.addPolicyToRole("adminRole", policy1.getName(), permissions, resources, "v1.0");
        iamSystem.addPolicyToRole("userRole", policy2.getName(), Set.of(Permission.READ), resources, "v1.0");
        Map<String, String> context = new HashMap<>();
        context.put("time", "09:00");
        System.out.println(policy1);
        System.out.println(policy2);
    }
}
