import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int[][] allocation = {{0, 0, 1, 2}, {1, 0, 0, 0}, {1, 3, 5, 4}, {0, 6, 3, 2}, {0, 0, 1, 4}};

        int[][] max = {{0, 0, 1, 2}, {1, 7, 5, 0}, {2, 3, 5, 6}, {0, 6, 5, 2}, {0, 6, 5, 6}};

        int[] available = {1, 5, 2, 0};

        BankersAlgorithm ba = new BankersAlgorithm(allocation, max, available);

        ba.isSafeState();

        Scanner sc = new Scanner(System.in);
        System.out.println("Do you want to request resources for a thread? Type y/n");
        char ans = sc.next().charAt(0);
        if (ans == 'y') {
            System.out.println("Enter the number of the thread that requires resources:");
            int threadNum = sc.nextInt();
            System.out.println("Enter the resources required by T[" + threadNum + "]");
            int[] request = new int[available.length];
            for (int i = 0; i < available.length; i++) {
                request[i] = sc.nextInt();
            }
            ba.requestResources(threadNum, request);
        } else {
            System.out.println("No resource request made. Exiting program.");
        }

    }
}