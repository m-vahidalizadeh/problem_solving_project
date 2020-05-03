package hackerrank;

import java.util.*;

public class QuickSort2 {

    static void quickSort(int[] ar) {
        quickSortRecursive(ar, 0, ar.length - 1);
    }

    static void quickSortRecursive(int[] ar, int start, int end) {
        if (start >= end) {
            return;
        }
        if (end == start + 1) {
            if (ar[start] > ar[end]) {
                int temp = ar[start];
                ar[start] = ar[end];
                ar[end] = temp;
            }
            printArray(ar, start, end);
            return;
        }
        int pivot = ar[start];
        int pivotIndex = start;
        for (int i = start + 1; i <= end; i++) {
            if (ar[i] < pivot) {
                for (int j = i; j > pivotIndex; j--) {
                    int temp = ar[j - 1];
                    ar[j - 1] = ar[j];
                    ar[j] = temp;
                }
                pivotIndex++;
            }
        }
        quickSortRecursive(ar, start, pivotIndex - 1);
        quickSortRecursive(ar, pivotIndex + 1, end);
        printArray(ar, start, end);
    }

    static void printArray(int[] ar, int start, int end) {
        for (int i = start; i <= end; i++) {
            System.out.print(ar[i] + " ");
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] ar = new int[n];
        for (int i = 0; i < n; i++) {
            ar[i] = in.nextInt();
        }
        quickSort(ar);
    }
}
