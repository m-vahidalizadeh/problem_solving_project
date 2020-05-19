package leetcode.medium;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Pancake Sorting
 * Given an array A, we can perform a pancake flip: We choose some positive integer k <= A.length, then reverse the order of the first k elements of A.  We want to perform zero or more pancake flips (doing them one after another in succession) to sort the array A.
 * <p>
 * Return the k-values corresponding to a sequence of pancake flips that sort A.  Any valid answer that sorts the array within 10 * A.length flips will be judged as correct.
 * <p>
 * Example 1:
 * <p>
 * Input: [3,2,4,1]
 * Output: [4,2,4,3]
 * Explanation:
 * We perform 4 pancake flips, with k values 4, 2, 4, and 3.
 * Starting state: A = [3, 2, 4, 1]
 * After 1st flip (k=4): A = [1, 4, 2, 3]
 * After 2nd flip (k=2): A = [4, 1, 2, 3]
 * After 3rd flip (k=4): A = [3, 2, 1, 4]
 * After 4th flip (k=3): A = [1, 2, 3, 4], which is sorted.
 * Example 2:
 * <p>
 * Input: [1,2,3]
 * Output: []
 * Explanation: The input is already sorted, so there is no need to flip anything.
 * Note that other answers, such as [3, 3], would also be accepted.
 * <p>
 * Note:
 * <p>
 * 1 <= A.length <= 100
 * A[i] is a permutation of [1, 2, ..., A.length]
 */
public class PancakeSorting {

    public List<Integer> pancakeSort(int[] A) {
        int n = A.length;
        List<Integer> flips = new LinkedList<>();
        if (isSorted(A, n)) return flips;
        for (int i = n - 1; i > 0; i--) {
            int maxIndex = findMaxIndex(A, i);
            if (maxIndex != 0) {
                flips.add(maxIndex + 1);
                performFlips(A, maxIndex);
            }
            flips.add(i + 1);
            performFlips(A, i);
        }
        return flips;
    }

    private void performFlips(int[] A, int k) {
        int temp, start = 0;
        while (start < k) {
            temp = A[start];
            A[start++] = A[k];
            A[k--] = temp;
        }
    }

    private int findMaxIndex(int[] A, int nTrail) {
        int max = A[0];
        int maxIndex = 0;
        for (int i = 1; i <= nTrail; i++) {
            int currentElement = A[i];
            if (currentElement >= max) {
                maxIndex = i;
                max = currentElement;
            }
        }
        return maxIndex;
    }

    private boolean isSorted(int[] A, int n) {
        if (n == 0 || n == 1) return true;
        return IntStream.range(1, n).filter(i -> A[i - 1] > A[i]).findAny().isEmpty();
    }

    public static void main(String[] args) {
        PancakeSorting p = new PancakeSorting();
        int[] A1 = {3, 2, 4, 1};
        List<Integer> result = p.pancakeSort(A1);
        printList(result);
        int[] A2 = {1, 2, 3};
        result = p.pancakeSort(A2);
        printList(result);
    }

    private static void printList(List<Integer> result) {
        System.out.print("The list: ");
        for (Integer r : result) {
            System.out.print(r + " ");
        }
        System.out.println();
    }

}
