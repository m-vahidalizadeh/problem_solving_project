package leetcode.companies.amazon;

import java.util.LinkedList;
import java.util.List;

public class AMZNPermutations {

    static List<List<Integer>> result = new LinkedList<>();

    public static void main(String[] args) {
/*
Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
 */
        int[] input = new int[]{1, 2, 3};
        List<List<Integer>> result = permute(input);
        for (List<Integer> le : result) {
            for (Integer e : le) {
                System.out.print(e + " ");
            }
            System.out.println();
        }
    }

    public static List<List<Integer>> permute(int[] nums) {
        addPermutations(0, nums);
        return result;
    }

    public static void addPermutations(int start, int[] input) {
        int n = input.length;
        if (start == n) {
            List<Integer> tempList = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                tempList.add(input[i]);
            }
            result.add(tempList);
        } else {
            for (int i = start; i < n; i++) {
                // Change i and start
                int temp1 = input[i];
                input[i] = input[start];
                input[start] = temp1;
                // permute start+1 and input
                addPermutations(start + 1, input);
                // Change start and i
                int temp2 = input[i];
                input[i] = input[start];
                input[start] = temp2;
            }
        }
    }

}
