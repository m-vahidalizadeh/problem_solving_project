import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FBFindIntersection {

    public static void main(String[] args) {
        /*
Example 1:
Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2]

Example 2:
Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [9,4]
         */
        int[] nums1 = new int[]{1, 2, 2, 1};
        int[] nums2 = new int[]{2, 2};
        int[] result = intersection(nums1, nums2);
        Arrays.stream(result).forEach(r -> System.out.println(r));
    }

    public static int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> num1Set = new HashSet<>();
        Arrays.stream(nums1).forEach(n -> num1Set.add(n));
        Set<Integer> num2Set = new HashSet<>();
        Arrays.stream(nums2).forEach(n -> num2Set.add(n));
        int n1 = num1Set.size();
        int n2 = num2Set.size();
        Set<Integer> result = new HashSet<>();
        if (n1 <= n2) {
            for (Integer e : num1Set) {
                if (num2Set.contains(e)) {
                    result.add(e);
                }
            }
        } else {
            for (Integer e : num2Set) {
                if (num1Set.contains(e)) {
                    result.add(e);
                }
            }
        }
        Integer[] resultArray = new Integer[result.size()];
        result.toArray(resultArray);
        int[] rA = new int[resultArray.length];
        for (int i = 0; i < rA.length; i++) {
            rA[i] = resultArray[i];
        }
        return rA;
    }

}
