public class InstanceManager {
    private ResourceAllocator resourceAllocator;

    public InstanceManager(int totalCpu, int totalMemory, int totalStorage) {
        this.resourceAllocator = new ResourceAllocator(totalCpu, totalMemory, totalStorage);
    }

    public void createInstance(String instanceId, int cpu, int memory, int storage) {
        if (resourceAllocator.allocateResources(instanceId, cpu, memory, storage)) {
            System.out.println("Instance " + instanceId + " created successfully.");
        }
    }

    public void startInstance(String instanceId) {
        Instance instance = resourceAllocator.getInstance(instanceId);
        if (instance != null) {
            instance.start();
        } else {
            System.out.println("Instance " + instanceId + " not found.");
        }
    }

    public void stopInstance(String instanceId) {
        Instance instance = resourceAllocator.getInstance(instanceId);
        if (instance != null) {
            instance.stop();
        } else {
            System.out.println("Instance " + instanceId + " not found.");
        }
    }

    public void terminateInstance(String instanceId) {
        Instance instance = resourceAllocator.getInstance(instanceId);
        if (instance != null) {
            instance.terminate();
            resourceAllocator.allocatedInstances.remove(instance);
        } else {
            System.out.println("Instance " + instanceId + " not found.");
        }
    }
}
