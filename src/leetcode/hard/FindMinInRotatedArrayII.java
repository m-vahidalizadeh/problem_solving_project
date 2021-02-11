package leetcode.hard;

/**
 * 154. Find Minimum in Rotated Sorted Array II
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 *
 * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
 *
 * Find the minimum element.
 *
 * The array may contain duplicates.
 *
 * Example 1:
 *
 * Input: [1,3,5]
 * Output: 1
 * Example 2:
 *
 * Input: [2,2,2,0,1]
 * Output: 0
 * Note:
 *
 * This is a follow up problem to Find Minimum in Rotated Sorted Array.
 * Would allow duplicates affect the run-time complexity? How and why?
 */
public class FindMinInRotatedArrayII {

    public int findMin(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] > nums[r])
                l = mid + 1; // The right side of mid has a cut which has min: ex. (3) 4 mid=(5) 1 r=(2)
            else if (nums[mid] < nums[r])
                r = mid; // The left side of mid has the min, its right side is all bigger than mid
            else r--; // The min might be in left or right. Hard to conclude. Let's narrow the range.
        }
        return nums[l]; // This is the mid
    }

}
