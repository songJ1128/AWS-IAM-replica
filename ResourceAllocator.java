import java.util.ArrayList;
import java.util.List;

public class ResourceAllocator {
    private int totalCpu;      // Total available CPU cores
    private int totalMemory;    // Total available memory in MB
    private int totalStorage;   // Total available storage in MB
    private List<Instance> allocatedInstances;

    public ResourceAllocator(int cpu, int memory, int storage) {
        this.totalCpu = cpu;
        this.totalMemory = memory;
        this.totalStorage = storage;
        this.allocatedInstances = new ArrayList<>();
    }

    public boolean allocateResources(String instanceId, int cpu, int memory, int storage) {
        int usedCpu = allocatedInstances.stream().mapToInt(i -> i.allocatedCpu).sum();
        int usedMemory = allocatedInstances.stream().mapToInt(i -> i.allocatedMemory).sum();
        int usedStorage = allocatedInstances.stream().mapToInt(i -> i.allocatedStorage).sum();

        if ((usedCpu + cpu <= totalCpu) && (usedMemory + memory <= totalMemory) && (usedStorage + storage <= totalStorage)) {
            Instance instance = new Instance(instanceId, cpu, memory, storage);
            allocatedInstances.add(instance);
            System.out.println("Resources allocated to instance " + instanceId);
            return true;
        } else {
            System.out.println("Insufficient resources to allocate instance " + instanceId);
            return false;
        }
    }

    public Instance getInstance(String instanceId) {
        return allocatedInstances.stream().filter(i -> i.getInstanceId().equals(instanceId)).findFirst().orElse(null);
    }
}
