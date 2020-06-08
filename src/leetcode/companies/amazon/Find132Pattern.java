package leetcode.companies.amazon;

/**
 * 132 Pattern
 * Given a sequence of n integers a1, a2, ..., an, a 132 pattern is a subsequence ai, aj, ak such that i < j < k and ai < ak < aj. Design an algorithm that takes a list of n numbers as input and checks whether there is a 132 pattern in the list.
 * <p>
 * Note: n will be less than 15,000.
 * <p>
 * Example 1:
 * <p>
 * Input: [1, 2, 3, 4]
 * <p>
 * Output: False
 * <p>
 * Explanation: There is no 132 pattern in the sequence.
 * Example 2:
 * <p>
 * Input: [3, 1, 4, 2]
 * <p>
 * Output: True
 * <p>
 * Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
 * Example 3:
 * <p>
 * Input: [-1, 3, 2, 0]
 * <p>
 * Output: True
 * <p>
 * Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].
 */
public class Find132Pattern {

    public boolean find132pattern(int[] nums) {
        int n=nums.length;
        if(n<3) return false;
        int[] mins=new int[n];
        mins[0]=nums[0];
        for(int i=1;i<n;i++) mins[i]=Math.min(mins[i-1],nums[i]);
        for(int j=n-2, k=n-1;j>=0;j--){
            if(nums[j]>mins[j]){
                while(k<n && nums[k]<=mins[j]) k++;
                if(k<n && nums[k]<nums[j]) return true;
                nums[--k] = nums[j];
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Find132Pattern f = new Find132Pattern();
        int[] nums1 = {1, 2, 3, 4};
        System.out.println(f.find132pattern(nums1));
        int[] nums2 = {3, 1, 4, 2};
        System.out.println(f.find132pattern(nums2));
        int[] nums3 = {-1, 3, 2, 0};
        System.out.println(f.find132pattern(nums3));
        int[] nums4 = {-2, 1, 2, -2, 1, 2};
        System.out.println(f.find132pattern(nums4));
    }

}
