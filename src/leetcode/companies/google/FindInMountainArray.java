package leetcode.companies.google;

import java.util.HashMap;
import java.util.Map;

/**
 * Find in Mountain Array
 * (This problem is an interactive problem.)
 * <p>
 * You may recall that an array A is a mountain array if and only if:
 * <p>
 * A.length >= 3
 * There exists some i with 0 < i < A.length - 1 such that:
 * A[0] < A[1] < ... A[i-1] < A[i]
 * A[i] > A[i+1] > ... > A[A.length - 1]
 * Given a mountain array mountainArr, return the minimum index such that mountainArr.get(index) == target.  If such an index doesn't exist, return -1.
 * <p>
 * You can't access the mountain array directly.  You may only access the array using a MountainArray interface:
 * <p>
 * MountainArray.get(k) returns the element of the array at index k (0-indexed).
 * MountainArray.length() returns the length of the array.
 * Submissions making more than 100 calls to MountainArray.get will be judged Wrong Answer.  Also, any solutions that attempt to circumvent the judge will result in disqualification.
 * <p>
 * Example 1:
 * <p>
 * Input: array = [1,2,3,4,5,3,1], target = 3
 * Output: 2
 * Explanation: 3 exists in the array, at index=2 and index=5. Return the minimum index, which is 2.
 * Example 2:
 * <p>
 * Input: array = [0,1,2,4,2,1], target = 3
 * Output: -1
 * Explanation: 3 does not exist in the array, so we return -1.
 * <p>
 * Constraints:
 * <p>
 * 3 <= mountain_arr.length() <= 10000
 * 0 <= target <= 10^9
 * 0 <= mountain_arr.get(index) <= 10^9
 */
public class FindInMountainArray {

    public interface MountainArray {
        public int get(int index);

        public int length();
    }

    public static class MountainArrayImpl implements MountainArray {

        int[] array;

        public MountainArrayImpl(int[] array) {
            this.array = array;
        }

        @Override
        public int get(int index) {
            return array[index];
        }

        @Override
        public int length() {
            return array.length;
        }
    }

    Map<Integer, Integer> cache;

    public int findInMountainArray(int target, MountainArray mountainArr) {
        cache = new HashMap<>();
        int n = mountainArr.length();
        int nMinusOne = n - 1;
        int peak = findPeak(mountainArr, 1, nMinusOne);
        if (getElement(peak, mountainArr) == target) return peak;
        int l = binarySearch(mountainArr, 0, peak, target);
        if (l != -1) return l;
        return binarySearchDec(mountainArr, peak, nMinusOne, target);
    }

    private int binarySearch(MountainArray mountainArray, int s, int e, int target) {
        if (s > e) return -1;
        int mid = (s + e) / 2;
        int curr = getElement(mid, mountainArray);
        if (curr == target) return mid;
        if (target < curr) return binarySearch(mountainArray, s, mid - 1, target);
        return binarySearch(mountainArray, mid + 1, e, target);
    }

    private int binarySearchDec(MountainArray mountainArray, int s, int e, int target) {
        if (s > e) return -1;
        int mid = (s + e) / 2;
        int curr = getElement(mid, mountainArray);
        if (curr == target) return mid;
        if (target < curr) return binarySearchDec(mountainArray, mid + 1, e, target);
        return binarySearchDec(mountainArray, s, mid - 1, target);
    }

    private int findPeak(MountainArray mountainArray, int s, int e) {
        if (e < s) return -1;
        int mid = (s + e) / 2;
        int prev = getElement(mid - 1, mountainArray);
        int next = getElement(mid + 1, mountainArray);
        int curr = getElement(mid, mountainArray);
        if (curr > prev && curr > next) return mid;
        if (curr < next) return findPeak(mountainArray, mid + 1, e);
        return findPeak(mountainArray, s, mid - 1);
    }

    private int getElement(int i, MountainArray mountainArray) {
        if (cache.containsKey(i)) return cache.get(i);
        int element = mountainArray.get(i);
        cache.put(i, element);
        return element;
    }

    public static void main(String[] args) {
        FindInMountainArray f = new FindInMountainArray();
        System.out.println(f.findInMountainArray(3, new MountainArrayImpl(new int[]{1, 2, 3, 4, 5, 3, 1})));
        System.out.println(f.findInMountainArray(3, new MountainArrayImpl(new int[]{0, 1, 2, 4, 2, 1})));
        System.out.println(f.findInMountainArray(2, new MountainArrayImpl(new int[]{1, 3, 4, 5, 2, 1})));
        System.out.println(f.findInMountainArray(0, new MountainArrayImpl(new int[]{3, 5, 3, 2, 0})));
    }

}
