package leetcode.medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 1447. Simplified Fractions
 * Given an integer n, return a list of all simplified fractions between 0 and 1 (exclusive) such that the denominator is less-than-or-equal-to n. The fractions can be in any order.
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: ["1/2"]
 * Explanation: "1/2" is the only unique fraction with a denominator less-than-or-equal-to 2.
 * Example 2:
 *
 * Input: n = 3
 * Output: ["1/2","1/3","2/3"]
 * Example 3:
 *
 * Input: n = 4
 * Output: ["1/2","1/3","1/4","2/3","3/4"]
 * Explanation: "2/4" is not a simplified fraction because it can be simplified to "1/2".
 * Example 4:
 *
 * Input: n = 1
 * Output: []
 *
 * Constraints:
 *
 * 1 <= n <= 100
 */
public class SimplifiedFractions {

    public List<String> simplifiedFractions(int n) {
        Set<Double> generated = new HashSet<>();
        List<String> result = new ArrayList<>();
        for (int d = 1; d <= n; d++) {
            for (int nu = 1; nu < d; nu++) {
                String candidate = nu + "/" + d;
                double checker = nu / (double) d;
                if (!generated.contains(checker)) {
                    result.add(candidate);
                    generated.add(checker);
                }
            }
        }
        return result;
    }

}
