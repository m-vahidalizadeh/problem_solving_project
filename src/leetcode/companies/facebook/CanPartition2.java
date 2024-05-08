package leetcode.companies.facebook;

import java.util.Arrays;

/**
 * 416. Partition Equal Subset Sum
 * Solved
 * Medium
 *
 * Topics
 *
 * Companies
 * Given an integer array nums, return true if you can partition the array into two subsets such that the sum of the elements in both subsets is equal or false otherwise.
 *
 * Example 1:
 *
 * Input: nums = [1,5,11,5]
 * Output: true
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 * Example 2:
 *
 * Input: nums = [1,2,3,5]
 * Output: false
 * Explanation: The array cannot be partitioned into equal sum subsets.
 *
 * Constraints:
 *
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 100
 */
public class CanPartition2 {

    public boolean canPartition(int[] nums) {
        if(nums.length==1) return false;
        Arrays.sort(nums);
        int left=0,right=nums.length-1,sumLeft=nums[0],sumRight=nums[nums.length-1];
        while(left<right){
            if(left==(right-1)){
                return sumLeft==sumRight;
            }
            if(sumRight>sumLeft) {
                left++;
                sumLeft+=nums[left];
            }
            else {
                right--;
                sumRight+=nums[right];
            }
        }
        return false;
    }

    public static void main(String[] args){
        CanPartition2 c=new CanPartition2();
        System.out.println(c.canPartition(new int[]{1,5,11,5}));
    }

}
