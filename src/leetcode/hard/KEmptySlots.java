package leetcode.hard;

/**
 * 683. K Empty Slots
 * You have n bulbs in a row numbered from 1 to n. Initially, all the bulbs are turned off. We turn on exactly one bulb every day until all bulbs are on after n days.
 *
 * You are given an array bulbs of length n where bulbs[i] = x means that on the (i+1)th day, we will turn on the bulb at position x where i is 0-indexed and x is 1-indexed.
 *
 * Given an integer k, return the minimum day number such that there exists two turned on bulbs that have exactly k bulbs between them that are all turned off. If there isn't such day, return -1.
 *
 * Example 1:
 *
 * Input: bulbs = [1,3,2], k = 1
 * Output: 2
 * Explanation:
 * On the first day: bulbs[0] = 1, first bulb is turned on: [1,0,0]
 * On the second day: bulbs[1] = 3, third bulb is turned on: [1,0,1]
 * On the third day: bulbs[2] = 2, second bulb is turned on: [1,1,1]
 * We return 2 because on the second day, there were two on bulbs with one off bulb between them.
 * Example 2:
 *
 * Input: bulbs = [1,2,3], k = 1
 * Output: -1
 *
 * Constraints:
 *
 * n == bulbs.length
 * 1 <= n <= 2 * 104
 * 1 <= bulbs[i] <= n
 * bulbs is a permutation of numbers from 1 to n.
 * 0 <= k <= 2 * 104
 */
public class KEmptySlots {

    public int kEmptySlots(int[] bulbs, int k) {
        int n = bulbs.length;
        int[] arr = new int[n];
        for (int i = 0; i < bulbs.length; i++) {
            int bulbIndex = bulbs[i] - 1;
            arr[bulbIndex] = 1;
            if (isValid(bulbIndex - k - 1, bulbIndex, arr, n) || isValid(bulbIndex, bulbIndex + k + 1, arr, n))
                return i + 1;
        }
        return -1;
    }

    private boolean isValid(int start, int end, int[] arr, int n) {
        if (start < 0 || end >= n || arr[start] == 0 || arr[end] == 0) return false;
        for (int i = start + 1; i < end; i++) {
            if (arr[i] == 1) return false;
        }
        return true;
    }

}