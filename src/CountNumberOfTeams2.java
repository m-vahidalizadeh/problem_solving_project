import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CountNumberOfTeams2 {

    public static void main(String[] args) {
    /*
Example 1:
Input: rating = [2,5,3,4,1]
Output: 3
Explanation: We can form three teams given the conditions. (2,3,4), (5,4,1), (5,3,1).

Example 2:
Input: rating = [2,1,3]
Output: 0
Explanation: We can't form any team given the conditions.

Example 3:
Input: rating = [1,2,3,4]
Output: 4
     */
        CountNumberOfTeams2 countNumberOfTeams = new CountNumberOfTeams2();
        int[] rating = {2, 5, 3, 4, 1};
        System.out.println(countNumberOfTeams.numTeams(rating));
        int[] rating2 = {2, 1, 3};
        System.out.println(countNumberOfTeams.numTeams(rating2));
        int[] rating3 = {1, 2, 3, 4};
        System.out.println(countNumberOfTeams.numTeams(rating3));
    }

    public int numTeams(int[] rating) {
        Map<Integer, Set<Integer>> incPairs = new HashMap<>();
        Map<Integer, Set<Integer>> decPairs = new HashMap<>();
        int n = rating.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++)
                if (rating[i] < rating[j]) {
                    Set<Integer> iSet;
                    if (incPairs.containsKey(i)) {
                        iSet = incPairs.get(i);
                        iSet.add(j);
                        incPairs.put(i, iSet);
                    } else {
                        iSet = new HashSet<>();
                        iSet.add(j);
                        incPairs.put(i, iSet);
                    }
                } else {
                    Set<Integer> iSet;
                    if (decPairs.containsKey(i)) {
                        iSet = decPairs.get(i);
                        iSet.add(j);
                        decPairs.put(i, iSet);
                    } else {
                        iSet = new HashSet<>();
                        iSet.add(j);
                        decPairs.put(i, iSet);
                    }
                }
        }
        int count = 0;
        // Count inc groups
        for (Map.Entry<Integer, Set<Integer>> e : incPairs.entrySet()) {
            Set<Integer> iSet = e.getValue();
            for (Integer i : iSet) {
                if (incPairs.containsKey(i)) {
                    count += incPairs.get(i).size();
                }
            }
        }
        // Count dec groups
        for (Map.Entry<Integer, Set<Integer>> e : decPairs.entrySet()) {
            Set<Integer> dSet = e.getValue();
            for (Integer d : dSet) {
                if (decPairs.containsKey(d)) {
                    count += decPairs.get(d).size();
                }
            }
        }
        return count;
    }

}
