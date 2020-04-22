package adobe;

import java.util.*;

public class ADBQuadruplets {

    public static void main(String[] args) {
        int[] input = new int[]{1, 0, -1, 0, -2, 2};
        List<List<Integer>> result = fourSum(input, 0);
        System.out.println();
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        Set<List<Integer>> solutions = new HashSet<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    for (int l = k + 1; l < n; l++) {
                        if (nums[i] + nums[j] + nums[k] + nums[l] == target) {
                            int[] tempSolution = new int[]{nums[i], nums[j], nums[k], nums[l]};
                            List<Integer> tempSolutionList = new LinkedList<>();
                            Arrays.sort(tempSolution);
                            for (int temp : tempSolution) {
                                tempSolutionList.add(temp);
                            }
                            solutions.add(tempSolutionList);
                        }
                    }
                }
            }
        }
        List<List<Integer>> result = new LinkedList<>();
        for (List<Integer> solution : solutions) {
            result.add(solution);
        }
        return result;
    }

}
