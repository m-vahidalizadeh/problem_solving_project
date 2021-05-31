package leetcode.companies.google;

import java.util.Map;
import java.util.TreeMap;

/**
 * 975. Odd Even Jump
 * You are given an integer array arr. From some starting index, you can make a series of jumps. The (1st, 3rd, 5th, ...) jumps in the series are called odd-numbered jumps, and the (2nd, 4th, 6th, ...) jumps in the series are called even-numbered jumps. Note that the jumps are numbered, not the indices.
 *
 * You may jump forward from index i to index j (with i < j) in the following way:
 *
 * During odd-numbered jumps (i.e., jumps 1, 3, 5, ...), you jump to the index j such that arr[i] <= arr[j] and arr[j] is the smallest possible value. If there are multiple such indices j, you can only jump to the smallest such index j.
 * During even-numbered jumps (i.e., jumps 2, 4, 6, ...), you jump to the index j such that arr[i] >= arr[j] and arr[j] is the largest possible value. If there are multiple such indices j, you can only jump to the smallest such index j.
 * It may be the case that for some index i, there are no legal jumps.
 * A starting index is good if, starting from that index, you can reach the end of the array (index arr.length - 1) by jumping some number of times (possibly 0 or more than once).
 *
 * Return the number of good starting indices.
 */
public class JumpsOddEven {

    public int oddEvenJumps(int[] A) {
        int n = A.length;
        boolean[] odd = new boolean[n];
        boolean[] even = new boolean[n];
        odd[n - 1] = even[n - 1] = true;
        TreeMap<Integer, Integer> tm = new TreeMap<>(); // num,last seen index
        int good = 1; // the last index is always good
        tm.put(A[n - 1], n - 1);
        for (int i = n - 2; i >= 0; i--) {
            Map.Entry<Integer, Integer> ceil = tm.ceilingEntry(A[i]);
            if (ceil != null) odd[i] = even[ceil.getValue()];
            Map.Entry<Integer, Integer> floor = tm.floorEntry(A[i]);
            if (floor != null) even[i] = odd[floor.getValue()];
            if (odd[i]) good++; // Always first jump is odd (1st)
            tm.put(A[i], i);
        }
        return good;
    }

}
