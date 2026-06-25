import java.util.*;
import java.io.*;

public class search {
    public static void main(String[] args) {
        int n, k;
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of elements:");
        n = sc.nextInt();

        int ar[] = new int[n];

        System.out.println("Enter the elements (sorted order):");
        for (int i = 0; i < n; i++) {
            ar[i] = sc.nextInt();
        }

        System.out.println("Enter the element to search:");
        k = sc.nextInt();

        int low = 0;
        int high = n - 1;
        int mid;

        while (low <= high) {
            mid = (low + high) / 2;

            if (ar[mid] == k) {
                System.out.println("Item found at index: " + mid);
                return;
            }
            else if (ar[mid] < k) {
                low = mid + 1;
            }
            else {
                high = mid - 1;
            }
        }

        System.out.println("Item not found");
    }
}
