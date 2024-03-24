package leetcode.companies.facebook;

import java.util.ArrayList;
import java.util.List;

/**
 * 282. Expression Add Operators
 * Solved
 * Hard
 *
 * Topics
 *
 * Companies
 *
 * Hint
 * Given a string num that contains only digits and an integer target, return all possibilities to insert the binary operators '+', '-', and/or '*' between the digits of num so that the resultant expression evaluates to the target value.
 *
 * Note that operands in the returned expressions should not contain leading zeros.
 *
 * Example 1:
 *
 * Input: num = "123", target = 6
 * Output: ["1*2*3","1+2+3"]
 * Explanation: Both "1*2*3" and "1+2+3" evaluate to 6.
 * Example 2:
 *
 * Input: num = "232", target = 8
 * Output: ["2*3+2","2+3*2"]
 * Explanation: Both "2*3+2" and "2+3*2" evaluate to 8.
 * Example 3:
 *
 * Input: num = "3456237490", target = 9191
 * Output: []
 * Explanation: There are no expressions that can be created from "3456237490" to evaluate to 9191.
 *
 * Constraints:
 *
 * 1 <= num.length <= 10
 * num consists of only digits.
 * -231 <= target <= 231 - 1
 */
public class AddOperandsResultTarget {

    public List<String> addOperators(String num, int target) {
        List<String> ans = new ArrayList<>();
        if (num == null || num.isEmpty()) return ans;
        helper("", 0, 0, 0, ans, num, target);
        return ans;
    }

    private void helper(String path, int i, long eval, long mul, List<String> ans, String num, int target) {
        if (i == num.length()) {
            if (target == eval) ans.add(path);
            return;
        }
        for (int j = i; j < num.length(); j++) {
            if (j != i && num.charAt(i) == '0') break;
            long cur = Long.parseLong(num.substring(i, j + 1));
            if (i == 0) helper(path + cur, j + 1, cur, cur, ans, num, target);
            else {
                helper(path + '+' + cur, j + 1, eval + cur, cur, ans, num, target);
                helper(path + '-' + cur, j + 1, eval - cur, -cur, ans, num, target);
                helper(path + '*' + cur, j + 1, eval - mul + mul * cur, mul * cur, ans, num, target);
            }
        }
    }

}
