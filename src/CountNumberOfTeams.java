import java.util.HashSet;
import java.util.Set;

public class CountNumberOfTeams {

    class Total {
        int total = 0;
    }

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
        CountNumberOfTeams countNumberOfTeams = new CountNumberOfTeams();
        int[] rating = {2, 5, 3, 4, 1};
        System.out.println(countNumberOfTeams.numTeams(rating));
        int[] rating2 = {2, 1, 3};
        System.out.println(countNumberOfTeams.numTeams(rating2));
        int[] rating3 = {1, 2, 3, 4};
        System.out.println(countNumberOfTeams.numTeams(rating3));
    }

    public int numTeams(int[] rating) {
        Set<Integer> maxEnd = new HashSet<>();
        Set<Integer> minEnd = new HashSet<>();
        int n = rating.length;
        if (n < 3)
            return 0;
        Total t = new Total();
        for (int i = 0; i < n - 2; i++) {
            int[] group = new int[3];
            int groupIndex = 0;
            group[0] = i;
            // Increasing order
            countGroups(rating, group, groupIndex, i + 1, t, true, minEnd, maxEnd);
            // Decreasing order
            countGroups(rating, group, groupIndex, i + 1, t, false, minEnd, maxEnd);
        }
        return t.total;
    }

    private void countGroups(int[] rating, int[] group, int groupIndex, int start, Total t, boolean isIncreasing, Set<Integer> minEnd, Set<Integer> maxEnd) {
        if (groupIndex == 2) {
            t.total++;
            return;
        }
        int lastGroupElement = rating[group[groupIndex]];
        if (isIncreasing) {
            // Increasing order
            if (maxEnd.contains(start))
                return;
            boolean found = false;
            int initialStart = start;
            while (start < rating.length) {
                if (rating[start] > lastGroupElement) {
                    int[] newGroup = group.clone();
                    newGroup[groupIndex + 1] = start;
                    countGroups(rating, newGroup, groupIndex + 1, start + 1, t, true, minEnd, maxEnd);
                    found = true;
                }
                start++;
            }
            if (!found) {
                maxEnd.add(initialStart);
            }
        } else {
            // Decreasing order
            if (minEnd.contains(start))
                return;
            boolean found = false;
            int initialStart = start;
            while (start < rating.length) {
                if (rating[start] < lastGroupElement) {
                    int[] newGroup = group.clone();
                    newGroup[groupIndex + 1] = start;
                    countGroups(rating, newGroup, groupIndex + 1, start + 1, t, false, minEnd, maxEnd);
                    found = true;
                }
                start++;
            }
            if (!found) {
                minEnd.add(initialStart);
            }
        }
    }

}
