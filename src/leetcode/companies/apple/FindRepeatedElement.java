package leetcode.companies.apple;

import java.util.HashSet;
import java.util.Set;

/**
 * N-Repeated Element in Size 2N Array
 * In a array A of size 2N, there are N+1 unique elements, and exactly one of these elements is repeated N times.
 * <p>
 * Return the element repeated N times.
 * <p>
 * Example 1:
 * <p>
 * Input: [1,2,3,3]
 * Output: 3
 * Example 2:
 * <p>
 * Input: [2,1,2,5,3,2]
 * Output: 2
 * Example 3:
 * <p>
 * Input: [5,1,5,2,5,3,5,4]
 * Output: 5
 * <p>
 * Note:
 * <p>
 * 4 <= A.length <= 10000
 * 0 <= A[i] < 10000
 * A.length is even
 */
public class FindRepeatedElement {

    public int repeatedNTimes(int[] A) {
        Set<Integer> seen = new HashSet<>();
        for (int i = 0; i < A.length; i++) {
            if (seen.contains(A[i])) return A[i];
            seen.add(A[i]);
        }
        return -1;
    }

}
