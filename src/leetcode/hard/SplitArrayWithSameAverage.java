package leetcode.hard;

import java.util.Arrays;

/**
 * 805. Split Array With Same Average
 * You are given an integer array nums.
 *
 * You should move each element of nums into one of the two arrays A and B such that A and B are non-empty, and average(A) == average(B).
 *
 * Return true if it is possible to achieve that and false otherwise.
 *
 * Note that for an array arr, average(arr) is the sum of all the elements of arr over the length of arr.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4,5,6,7,8]
 * Output: true
 * Explanation: We can split the array into [1,4,5,8] and [2,3,6,7], and both of them have an average of 4.5.
 * Example 2:
 *
 * Input: nums = [3,1]
 * Output: false
 *
 * Constraints:
 *
 * 1 <= nums.length <= 30
 * 0 <= nums[i] <= 104
 */
public class SplitArrayWithSameAverage {

    public boolean check(int[] A, int leftSum, int leftNum, int startIndex) {
        if (leftNum == 0) return leftSum == 0;
        if ((A[startIndex] > leftSum / leftNum)) return false;
        for (int i = startIndex; i < A.length - leftNum + 1; i++) {
            if (i > startIndex && A[i] == A[i - 1]) continue;
            if (check(A, leftSum - A[i], leftNum - 1, i + 1)) return true;
        }
        return false;
    }

    public boolean splitArraySameAverage(int[] A) {
        if (A.length == 1) return false;
        int sumA = 0;
        for (int a : A) sumA += a;
        Arrays.sort(A);
        for (int lenOfB = 1; lenOfB <= A.length / 2; lenOfB++) {
            if ((sumA * lenOfB) % A.length == 0) {
                if (check(A, (sumA * lenOfB) / A.length, lenOfB, 0)) return true;
            }
        }
        return false;
    }

}
