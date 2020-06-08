package leetcode.companies.facebook;

import java.util.*;

public class FindTripletsSumZero {

    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> negatives = new HashMap<>();
        List<List<Integer>> result = new ArrayList<>();
        Set<List<Integer>> foundTriplets = new HashSet<>();
        for (int i = 0; i < n; i++) negatives.put(-1 * nums[i], i);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int twoSum = nums[i] + nums[j];
                Integer negativeIndex = negatives.get(twoSum);
                if (i != j && negativeIndex != null && negativeIndex != i && negativeIndex != j) {
                    List<Integer> tempList = new ArrayList<>();
                    tempList.add(nums[i]);
                    tempList.add(nums[j]);
                    tempList.add(-1 * (twoSum));
                    Collections.sort(tempList);
                    if (!foundTriplets.contains(tempList)) {
                        result.add(tempList);
                        foundTriplets.add(tempList);
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        FindTripletsSumZero f = new FindTripletsSumZero();
        int[] nums = {-1, 0, 1, 2, -1, -4};
        printTriplets(f.threeSum(nums));
        int[] nums2 = {0, 0, 0};
        printTriplets(f.threeSum(nums2));
    }

    private static void printTriplets(List<List<Integer>> triplets) {
        for (List<Integer> triplet : triplets) {
            for (Integer e : triplet) System.out.print(e + " ");
            System.out.println();
        }
    }

}
