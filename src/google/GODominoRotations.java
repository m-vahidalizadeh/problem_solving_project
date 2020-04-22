package google;

import java.util.*;

public class GODominoRotations {

    public static void main(String[] args) {
        int[] A = new int[]{2, 1, 2, 4, 2, 2};
        int[] B = new int[]{5, 2, 6, 2, 3, 2};
        System.out.println(minDominoRotations(A, B));
        A = new int[]{1, 2, 1, 1, 1, 2, 2, 2};
        B = new int[]{2, 1, 2, 2, 2, 2, 2, 2};
        System.out.println(minDominoRotations(A, B));
    }

    public static int minDominoRotations(int[] A, int[] B) {
        int result = -1;
        int resultA = Integer.MAX_VALUE;
        int resultB = Integer.MAX_VALUE;
        int n = A.length;
        // Find max frequency of A
        Map<Integer, Integer> freqMapA = new HashMap<>();
        int maxNumberA = 0;
        int maxNumberFreqA = 0;
        for (int i = 0; i < n; i++) {
            int numberFreqA = 0;
            if (freqMapA.containsKey(A[i])) {
                numberFreqA = freqMapA.get(A[i]) + 1;
            } else {
                numberFreqA = 1;
            }
            freqMapA.put(A[i], numberFreqA);
            if (numberFreqA > maxNumberFreqA) {
                maxNumberA = A[i];
                maxNumberFreqA = numberFreqA;
            }
        }
        int maxFreqNumberA = maxNumberA;
        // Find max frequency of B
        Map<Integer, Integer> freqMapB = new HashMap<>();
        int maxNumberB = 0;
        int maxNumberFreqB = 0;
        for (int i = 0; i < n; i++) {
            int numberFreqB = 0;
            if (freqMapB.containsKey(B[i])) {
                numberFreqB = freqMapB.get(B[i]) + 1;
            } else {
                numberFreqB = 1;
            }
            freqMapB.put(B[i], numberFreqB);
            if (numberFreqB > maxNumberFreqB) {
                maxNumberB = B[i];
                maxNumberFreqB = numberFreqB;
            }
        }
        int maxFreqNumberB = maxNumberB;
        // Try As
        int counter = 0;
        boolean feasible = true;
        for (int i = 0; i < n; i++) {
            if (A[i] != maxFreqNumberA && B[i] != maxFreqNumberA) {
                feasible = false;
                break;
            } else if (A[i] == maxFreqNumberA) {
            } else {
                counter++;
            }
        }
        if (feasible) {
            resultA = counter;
        }
        // Try Bs
        counter = 0;
        feasible = true;
        for (int i = 0; i < n; i++) {
            if (A[i] != maxFreqNumberB && B[i] != maxFreqNumberB) {
                feasible = false;
                break;
            } else if (B[i] == maxFreqNumberB) {
            } else {
                counter++;
            }
        }
        if (feasible) {
            return Math.min(resultA, counter);
        }
        // Return result
        return result;
    }

}
