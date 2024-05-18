import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Please enter the number of resources:");
        int m = sc.nextInt();

        System.out.println("Please enter the number of threads:");
        int n = sc.nextInt();

        System.out.println("Please enter the allocation matrix:");
        int[][] allocation = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                allocation[i][j] = sc.nextInt();
            }
        }

        System.out.println("Please enter the max matrix:");
        int[][] max = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                max[i][j] = sc.nextInt();
            }
        }

        System.out.println("Please enter the available resources:");
        int[] available = new int[m];
        for (int j = 0; j < m; j++) {
            available[j] = sc.nextInt();
        }

        BankersAlgorithm ba = new BankersAlgorithm(allocation, max, available);

        ba.isSafeState();
    }
}