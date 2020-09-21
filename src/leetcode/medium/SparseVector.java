package leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * Dot Product of Two Sparse Vectors
 * Given two sparse vectors, compute their dot product.
 * <p>
 * Implement class SparseVector:
 * <p>
 * SparseVector(nums) Initializes the object with the vector nums
 * dotProduct(vec) Compute the dot product between the instance of SparseVector and vec
 * A sparse vector is a vector that has mostly zero values, you should store the sparse vector efficiently and compute the dot product between two SparseVector.
 * <p>
 * Follow up: What if only one of the vectors is sparse?
 * <p>
 * Example 1:
 * <p>
 * Input: nums1 = [1,0,0,2,3], nums2 = [0,3,0,4,0]
 * Output: 8
 * Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
 * v1.dotProduct(v2) = 1*0 + 0*3 + 0*0 + 2*4 + 3*0 = 8
 * Example 2:
 * <p>
 * Input: nums1 = [0,1,0,0,0], nums2 = [0,0,0,0,2]
 * Output: 0
 * Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
 * v1.dotProduct(v2) = 0*0 + 1*0 + 0*0 + 0*0 + 0*2 = 0
 * Example 3:
 * <p>
 * Input: nums1 = [0,1,0,0,2,0,0], nums2 = [1,0,0,0,3,0,4]
 * Output: 6
 * <p>
 * Constraints:
 * <p>
 * n == nums1.length == nums2.length
 * 1 <= n <= 10^5
 * 0 <= nums1[i], nums2[i] <= 100
 */
public class SparseVector {

    Map<Integer, Integer> numsMap;

    SparseVector(int[] nums) {
        numsMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) if (nums[i] > 0) numsMap.put(i, nums[i]);
    }

    public int dotProduct(SparseVector vec) {
        int sum = 0;
        for (Map.Entry<Integer, Integer> e : this.numsMap.entrySet()) {
            Integer key = e.getKey();
            if (vec.numsMap.containsKey(key)) sum += vec.numsMap.get(key) * e.getValue();
        }
        return sum;
    }

    public static void main(String[] args) {
        SparseVector s1 = new SparseVector(new int[]{1, 0, 0, 2, 3});
        System.out.println(s1.dotProduct(new SparseVector(new int[]{0, 3, 0, 4, 0})));
        SparseVector s2 = new SparseVector(new int[]{0, 1, 0, 0, 0});
        System.out.println(s2.dotProduct(new SparseVector(new int[]{0, 0, 0, 0, 2})));
        SparseVector s3 = new SparseVector(new int[]{0, 1, 0, 0, 2, 0, 0});
        System.out.println(s3.dotProduct(new SparseVector(new int[]{1, 0, 0, 0, 3, 0, 4})));
    }

}
