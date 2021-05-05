package leetcode.companies.facebook;

import java.util.*;

/**
 * Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.
 *
 * Example 1:
 *
 * Input: nums = [1,1,2]
 * Output:
 * [[1,1,2],
 *  [1,2,1],
 *  [2,1,1]]
 * Example 2:
 *
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * Constraints:
 *
 * 1 <= nums.length <= 8
 * -10 <= nums[i] <= 10
 */
public class UniquePermutations {

    public List<List<Integer>> permuteUnique(int[] nums) {
        Set<List<Integer>> resSet=new HashSet<>();
        List<Integer> currPermutation=new ArrayList<>();
        for(int num:nums) currPermutation.add(num);
        rec(currPermutation,0,nums.length,resSet);
        return new ArrayList<>(resSet);
    }

    private void rec(List<Integer> currPermutation,int first,int n,Set<List<Integer>> res){
        if(first==n) res.add(new ArrayList<>(currPermutation));
        else{
            for(int i=first;i<n;i++){
                Collections.swap(currPermutation,first,i);
                rec(currPermutation,first+1,n,res);
                Collections.swap(currPermutation,i,first);
            }
        }
    }

}
