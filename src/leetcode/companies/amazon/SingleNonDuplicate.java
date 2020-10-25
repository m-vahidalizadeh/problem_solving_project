package leetcode.companies.amazon;

/**
 * 540. Single Element in a Sorted Array
 * You are given a sorted array consisting of only integers where every element appears exactly twice, except for one
 * element which appears exactly once. Find this single element that appears only once.
 *
 * Follow up: Your solution should run in O(log n) time and O(1) space.
 *
 * Example 1:
 *
 * Input: nums = [1,1,2,3,3,4,4,8,8]
 * Output: 2
 * Example 2:
 *
 * Input: nums = [3,3,7,7,10,11,11]
 * Output: 10
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^5
 */
public class SingleNonDuplicate {

    public int singleNonDuplicate(int[] nums) {
        return bs(nums, 0, nums.length - 1, nums.length - 1);
    }

    private int bs(int[] nums, int l, int r, int last) {
        int mid = l + ((r - l) / 2);
        int midElement = nums[mid];
        int midL = mid == 0 ? -1 : nums[mid - 1];
        int midR = mid == last ? -1 : nums[mid + 1];
        if (midElement == midL) {
            int lSize = mid - 2 - l + 1;
            if (lSize % 2 == 0) return bs(nums, mid + 1, r, last);
            else return bs(nums, l, mid - 2, last);
        } else if (midElement == midR) {
            int lSize = mid - 1 - l + 1;
            if (lSize % 2 == 0) return bs(nums, mid + 2, r, last);
            else return bs(nums, l, mid - 1, last);
        }
        return midElement;
    }

    public static void main(String[] args) {
        SingleNonDuplicate s = new SingleNonDuplicate();
        System.out.println(s.singleNonDuplicate(new int[]{1, 1, 2, 3, 3, 4, 4, 8, 8}));
        System.out.println(s.singleNonDuplicate(new int[]{3, 3, 7, 7, 10, 11, 11}));
    }

}
