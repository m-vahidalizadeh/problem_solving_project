package leetcode.medium;

import leetcode.base.ArrayReader;

/**
 * Search in a Sorted Array of Unknown Size
 * Given an integer array sorted in ascending order, write a function to search target in nums.  If target exists, then return its index, otherwise return -1. However, the array size is unknown to you. You may only access the array using an ArrayReader interface, where ArrayReader.get(k) returns the element of the array at index k (0-indexed).
 * <p>
 * You may assume all integers in the array are less than 10000, and if you access the array out of bounds, ArrayReader.get will return 2147483647.
 * <p>
 * Example 1:
 * <p>
 * Input: array = [-1,0,3,5,9,12], target = 9
 * Output: 4
 * Explanation: 9 exists in nums and its index is 4
 * Example 2:
 * <p>
 * Input: array = [-1,0,3,5,9,12], target = 2
 * Output: -1
 * Explanation: 2 does not exist in nums so return -1
 * <p>
 * Note:
 * <p>
 * You may assume that all elements in the array are unique.
 * The value of each element in the array will be in the range [-9999, 9999].
 */
public class BinarySearchUnknownLength {

    class Boundry {
        int low;
        int high;

        Boundry(int low, int high) {
            this.low = low;
            this.high = high;
        }
    }

    public int search(ArrayReader reader, int target) {
        return binarySearch(reader, target, findBoundry(reader, target, 0, 1));
    }

    private int binarySearch(ArrayReader reader, int target, Boundry boundry) {
        if (boundry.low > boundry.high) return -1;
        int mid = (boundry.low + boundry.high) / 2;
        int midValue = reader.get(mid);
        if (target == midValue) {
            return mid;
        } else if (target < midValue) {
            boundry.high = mid - 1;
            return binarySearch(reader, target, boundry);
        }
        boundry.low = mid + 1;
        return binarySearch(reader, target, boundry);
    }

    private Boundry findBoundry(ArrayReader reader, int target, int low, int high) {
        if (reader.get(high) > target) return new Boundry(low, high);
        return findBoundry(reader, target, high, 2 * high);
    }

    public static void main(String[] args) {
        BinarySearchUnknownLength b = new BinarySearchUnknownLength();
        int[] array1 = {-1, 0, 3, 5, 9, 12};
        ArrayReader reader = new ArrayReader(array1);
        System.out.println(b.search(reader, 9));
        int[] array2 = {-1, 0, 3, 5, 9, 12};
        reader = new ArrayReader(array2);
        System.out.println(b.search(reader, 2));
    }

}
