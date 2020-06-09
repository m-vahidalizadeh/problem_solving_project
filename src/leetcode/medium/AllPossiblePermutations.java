package leetcode.medium;

import java.util.*;

public class AllPossiblePermutations {

    public class Result {
        public List<List<Integer>> permutations;

        public Result() {
            this.permutations = new ArrayList<>();
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        Result result = new Result();
        Set<Integer> available = new HashSet<>();
        Arrays.stream(nums).forEach(available::add);
        getPermutationsRec(available, new ArrayList<>(), result);
        return result.permutations;
    }

    private void getPermutationsRec(Set<Integer> available, List<Integer> currPermutation, Result result) {
        if (available.isEmpty()) result.permutations.add(currPermutation);
        else {
            for (Integer a : available) {
                Set<Integer> newAvailable = new HashSet<>(available);
                newAvailable.remove(a);
                List<Integer> newPermutation = new ArrayList<>(currPermutation);
                newPermutation.add(a);
                getPermutationsRec(newAvailable, newPermutation, result);
            }
        }
    }

    public static void main(String[] args) {
        AllPossiblePermutations a=new AllPossiblePermutations();
        int[] nums={1,2,3};
        System.out.println(a.permute(nums));
    }

}
