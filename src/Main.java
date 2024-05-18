public class Main {

    public static void main(String[] args) {

        int[][] allocation = {{0, 0, 1, 2}, {1, 0, 0, 0}, {1, 3, 5, 4}, {0, 6, 3, 2}, {0, 0, 1, 4}};

        int[][] max = {{0, 0, 1, 2}, {1, 7, 5, 0}, {2, 3, 5, 6}, {0, 6, 5, 2}, {0, 6, 5, 6}};

        int[] available = {1, 5, 2, 0};

        BankersAlgorithm ba = new BankersAlgorithm(allocation, max, available);

        ba.isSafeState();

        // Request resources
        int[] request = {0, 4, 2, 0};
        int threadNum = 1;
        ba.requestResources(threadNum, request);
    }
}