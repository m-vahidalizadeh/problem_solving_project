package leetcode.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * 282. Expression Add Operators
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
 * Input: num = "105", target = 5
 * Output: ["1*0+5","10-5"]
 * Explanation: Both "1*0+5" and "10-5" evaluate to 5.
 * Note that "1-05" is not a valid expression because the 5 has a leading zero.
 * Example 4:
 *
 * Input: num = "00", target = 0
 * Output: ["0*0","0+0","0-0"]
 * Explanation: "0*0", "0+0", and "0-0" all evaluate to 0.
 * Note that "00" is not a valid expression because the 0 has a leading zero.
 * Example 5:
 *
 * Input: num = "3456237490", target = 9191
 * Output: []
 * Explanation: There are no expressions that can be created from "3456237490" to evaluate to 9191.
 *
 * Constraints:
 *
 * 1 <= num.length <= 10
 * num consists of only digits.
 * -2^31 <= target <= 2^31 - 1
 */
public class ExpressionAddOperators {

    int n;
    int target;
    String num;
    List<String> res;
    StringBuilder sb;

    public List<String> addOperators(String num, int target) {
        this.n = num.length();
        this.target = target;
        this.num = num;
        this.res = new ArrayList<>();
        this.sb = new StringBuilder();
        rec(0, 0, 0, 0); // pre,curr,val,s
        return res;
    }

    private void rec(int index, long pre, long curr, long val) {
        if (index == n) {
            if (val == target && curr == 0) res.add(sb.toString());
            return;
        }
        curr = curr * 10 + Character.getNumericValue(num.charAt(index));
        int origLength = sb.length();
        if (curr > 0) rec(index + 1, pre, curr, val);
        if (sb.length() == 0) {
            sb.append(curr);
            rec(index + 1, curr, 0, val + curr);
            sb.setLength(origLength);
        } else {
            sb.append("+").append(curr);
            rec(index + 1, curr, 0, val + curr);
            sb.setLength(origLength);
            sb.append("-").append(curr);
            rec(index + 1, -curr, 0, val - curr);
            sb.setLength(origLength);
            sb.append("*").append(curr);
            rec(index + 1, pre * curr, 0, val - pre + pre * curr);
            sb.setLength(origLength);
        }
    }

}
