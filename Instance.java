public class Instance {
    private String instanceId;
    private int allocatedCpu;
    private int allocatedMemory;
    private int allocatedStorage; 
    private boolean isRunning;

    public Instance(String instanceId, int cpu, int memory, int storage) {
        this.instanceId = instanceId;
        this.allocatedCpu = cpu;
        this.allocatedMemory = memory;
        this.allocatedStorage = storage;
        this.isRunning = false;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void start() {
        if (!isRunning) {
            isRunning = true;
            System.out.println("Instance " + instanceId + " started with CPU: " + allocatedCpu + " cores, Memory: " 
                               + allocatedMemory + " MB, Storage: " + allocatedStorage + " MB");
        } else {
            System.out.println("Instance " + instanceId + " is already running.");
        }
    }

    public void stop() {
        if (isRunning) {
            isRunning = false;
            System.out.println("Instance " + instanceId + " stopped.");
        } else {
            System.out.println("Instance " + instanceId + " is already stopped.");
        }
    }

    public void terminate() {
        stop();
        System.out.println("Instance " + instanceId + " terminated.");

    }
}
