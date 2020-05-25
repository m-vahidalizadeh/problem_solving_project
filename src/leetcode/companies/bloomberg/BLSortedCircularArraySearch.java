package leetcode.companies.bloomberg;

public class BLSortedCircularArraySearch {

    public static void main(String[] args) {
        /*
(i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).
Example 1:
Input: nums = [2,5,6,0,0,1,2], target = 0
Output: true

Example 2:
Input: nums = [2,5,6,0,0,1,2], target = 3
Output: false
         */
        int[] nums = new int[]{2, 5, 6, 0, 0, 1, 2};
        BLSortedCircularArraySearch blSortedCircularArraySearch = new BLSortedCircularArraySearch();
        System.out.println(blSortedCircularArraySearch.search(nums, 0));
        nums = new int[]{2, 5, 6, 0, 0, 1, 2};
        System.out.println(blSortedCircularArraySearch.search(nums, 3));
        nums = new int[]{1};
        System.out.println(blSortedCircularArraySearch.search(nums, 0));
        nums = new int[]{1, 1};
        System.out.println(blSortedCircularArraySearch.search(nums, 0));
    }

    public boolean search(int[] nums, int target) {
        if (nums.length == 0) {
            return false;
        } else if (nums.length == 1) {
            if (nums[0] == target) {
                return true;
            } else {
                return false;
            }
        }
        int startIndex = findPivot(nums);
        int index = startIndex;
        while (true) {
            if (index == startIndex - 1) {
                if (nums[index] == target) {
                    return true;
                } else {
                    return false;
                }
            }
            if (nums[index] == target) {
                return true;
            }
            index = (index + 1) % (nums.length);
        }
    }

    public int findPivot(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (i + 1 < nums.length && nums[i] > nums[i + 1]) {
                return i + 1;
            }
        }
        return 1;
    }

}
