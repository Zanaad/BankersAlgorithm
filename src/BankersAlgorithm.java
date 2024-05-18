import java.util.Arrays;

public class BankersAlgorithm {
    private final int[][] allocation;
    private final int[][] max;
    private final int[] available;
    private final int[][] need;
    private final int numThreads;
    private final int numResources;

    public BankersAlgorithm(int[][] allocation, int[][] max, int[] available) {
        this.allocation = allocation;
        this.max = max;
        this.available = available;
        this.numThreads = allocation.length;
        this.numResources = available.length;
        this.need = calculateNeed();
    }

    // Calculate the Need matrix: Need[i,j] = Max[i,j] - Allocation[i,j]
    private int[][] calculateNeed() {
        int[][] need = new int[numThreads][numResources];
        for (int i = 0; i < numThreads; i++) {
            for (int j = 0; j < numResources; j++) {
                need[i][j] = max[i][j] - allocation[i][j];
            }
        }
        return need;
    }

    // Check if the system is in a safe state with detailed output
    public boolean isSafeState() {
        int[] work = Arrays.copyOf(available, available.length);
        boolean[] finish = new boolean[numThreads];
        String[] safeSequence = new String[numThreads];
        int count = 0;

        System.out.println("Initial Available: " + Arrays.toString(work));
        System.out.println("Initial Need Matrix:");
        for (int[] row : need) {
            System.out.println(Arrays.toString(row));
        }

        while (count < numThreads) {
            boolean found = false;
            for (int i = 0; i < numThreads; i++) {
                if (!finish[i]) {
                    System.out.println("Step " + (count + 1) + ": Check T" + i);
                    System.out.println("Need[T" + i + "] = " + Arrays.toString(need[i]) + " ≤ Available = " + Arrays.toString(work));
                    if (canSatisfy(need[i], work)) {
                        System.out.println("T" + i + " can be satisfied. Allocate resources to T" + i + " and release them back.");
                        for (int k = 0; k < numResources; k++) {
                            work[k] += allocation[i][k];
                        }
                        finish[i] = true;
                        safeSequence[count++] = "T" + i;
                        found = true;
                        System.out.println("Available = " + Arrays.toString(work));
                        System.out.println("---------------------------------------");
                    } else {
                        System.out.println("T" + i + " must wait.");
                        System.out.println("---------------------------------------");
                    }
                }
            }
            if (!found) {
                break;
            }
        }

        boolean isSafe = (count == numThreads);
        if (isSafe) {
            System.out.println("System is in a safe state.");
            System.out.println("Safe sequence is: " + Arrays.toString(safeSequence));
        } else System.out.println("System is not in a safe state.");

        return isSafe;
    }

    // Helper method to check if resources can satisfy the need
    private boolean canSatisfy(int[] need, int[] available) {
        for (int j = 0; j < need.length; j++) {
            if (need[j] > available[j]) return false;
        }
        return true;
    }

    // Request resources for a given thread
    public boolean requestResources(int threadNum, int[] request) {
        // Check if the request is greater than the need
        for (int j = 0; j < numResources; j++) {
            if (request[j] > need[threadNum][j]) {
                System.out.println("Thread " + threadNum + " has exceeded its maximum claim.");
                return false;
            }
        }

        // Check if the request is greater than the available resources
        for (int j = 0; j < numResources; j++) {
            if (request[j] > available[j]) {
                System.out.println("Thread " + threadNum + "'s request cannot be granted because there are not enough available resources.");
                return false;
            }
        }

        // Allocate the requested resources
        for (int j = 0; j < numResources; j++) {
            available[j] -= request[j];
            allocation[threadNum][j] += request[j];
            need[threadNum][j] -= request[j];
        }

        // Check if the system is in a safe state after allocation
        if (isSafeState()) {
            System.out.println("Request by thread " + threadNum + " can be granted immediately.");
            return true;
        } else {
            // Roll back the allocation
            for (int j = 0; j < numResources; j++) {
                available[j] += request[j];
                allocation[threadNum][j] -= request[j];
                need[threadNum][j] += request[j];
            }
            System.out.println("Request by thread " + threadNum + " cannot be granted immediately. Rolling back.");
            return false;
        }
    }
}