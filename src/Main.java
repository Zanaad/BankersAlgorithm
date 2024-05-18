public class Main {
    public static void main(String[] args) {
        // Allocation matrix
        int[][] allocation = {
                {0, 0, 1, 2},
                {1, 0, 0, 0},
                {1, 3, 5, 4},
                {0, 6, 3, 2},
                {0, 0, 1, 4}
        };

        // Max matrix
        int[][] max = {
                {0, 0, 1, 2},
                {1, 7, 5, 0},
                {2, 3, 5, 6},
                {0, 6, 5, 2},
                {0, 6, 5, 6}
        };

        // Available resources
        int[] available = {1, 5, 2, 0};

        // Initialize Banker's Algorithm
        BankersAlgorithm ba = new BankersAlgorithm(allocation, max, available);

        // Check if the system is in a safe state initially
        ba.isSafeState();
    }
}