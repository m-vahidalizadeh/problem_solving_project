package interesting;

import java.util.HashSet;
import java.util.Set;

public class FindPermutations {

    static class Permutations {
        Set<String> permutations = new HashSet<>();
    }

    /**
     * Building all the permutations by adding chars of left to right one by one.
     *
     * @param left         The left string
     * @param right        The right string
     * @param permutations The permutations
     */
    private void findPermutations(String left, String right, Permutations permutations) {
        int n = left.length();
        if (n == 0) {
            permutations.permutations.add(right);
        }
        for (int i = 0; i < n; i++) {
            findPermutations(left.substring(0, i) + left.substring(i + 1, n), right + left.charAt(i), permutations);
        }
    }

    /**
     * Gets all the permutations of a string s.
     *
     * @param s The input string
     * @return all the permutations of a string s
     */
    public Permutations getPermutations(String s) {
        Permutations permutations = new Permutations();
        findPermutations(s, "", permutations);
        return permutations;
    }

    public static void main(String[] args) {
        FindPermutations findPermutations = new FindPermutations();
        String s = "ABC";
        Permutations permutations = findPermutations.getPermutations(s);
        printPermutations(permutations);
    }

    private static void printPermutations(Permutations permutations) {
        for (String p : permutations.permutations) {
            System.out.println(p);
        }
    }

}
