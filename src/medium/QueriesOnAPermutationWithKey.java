package medium;

import java.util.HashMap;
import java.util.Map;

/**
 * Queries on a Permutation With Key
 * Given the array queries of positive integers between 1 and m, you have to process all queries[i] (from i=0 to
 * i=queries.length-1) according to the following rules:
 * <p>
 * In the beginning, you have the permutation P=[1,2,3,...,m].
 * For the current i, find the position of queries[i] in the permutation P (indexing from 0) and then move this at
 * the beginning of the permutation P. Notice that the position of queries[i] in P is the result for queries[i].
 * Return an array containing the result for the given queries.
 * <p>
 * Example 1:
 * <p>
 * Input: queries = [3,1,2,1], m = 5
 * Output: [2,1,2,1]
 * Explanation: The queries are processed as follow:
 * For i=0: queries[i]=3, P=[1,2,3,4,5], position of 3 in P is 2, then we move 3 to the beginning of P resulting in P=[3,1,2,4,5].
 * For i=1: queries[i]=1, P=[3,1,2,4,5], position of 1 in P is 1, then we move 1 to the beginning of P resulting in P=[1,3,2,4,5].
 * For i=2: queries[i]=2, P=[1,3,2,4,5], position of 2 in P is 2, then we move 2 to the beginning of P resulting in P=[2,1,3,4,5].
 * For i=3: queries[i]=1, P=[2,1,3,4,5], position of 1 in P is 1, then we move 1 to the beginning of P resulting in P=[1,2,3,4,5].
 * Therefore, the array containing the result is [2,1,2,1].
 * Example 2:
 * <p>
 * Input: queries = [4,1,2,2], m = 4
 * Output: [3,1,2,0]
 * Example 3:
 * <p>
 * Input: queries = [7,5,5,8,3], m = 8
 * Output: [6,5,0,7,5]
 * <p>
 * Constraints:
 * <p>
 * 1 <= m <= 10^3
 * 1 <= queries.length <= m
 * 1 <= queries[i] <= m
 */
public class QueriesOnAPermutationWithKey {

    public int[] processQueries(int[] queries, int m) {
        Map<Integer, Integer> locations = new HashMap<>();
        // Initialization
        int[] result = new int[m];
        for (int i = 0; i < m; i++) {
            result[i] = i;
            locations.put(i, i);
        }
        int n = queries.length;
        for (int i = 0; i < n; i++) {
            // Process the current query
            int currentQuery = queries[i];
            int currentLocation = locations.get(currentQuery);
            // Indexes 0 to currentLocation-1 should be shifted to right 1 location. Current query should be moved to 0.
            for (int j = currentLocation - 1; j >= 0; j--) {
                locations.put(result[j], j + 1);
                result[j + 1] = result[j];
            }
            result[0] = currentQuery;
            locations.put(currentQuery, 0);
        }
        return result;
    }

    public static void main(String[] args) {
        QueriesOnAPermutationWithKey queriesOnAPermutationWithKey = new QueriesOnAPermutationWithKey();
        int[] input1 = {4, 1, 2, 2};
        int[] input2 = {7, 5, 5, 8, 3};
    }

}
