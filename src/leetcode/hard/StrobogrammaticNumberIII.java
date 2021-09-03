package leetcode.hard;

import java.util.*;

/**
 * 248. Strobogrammatic Number III
 * Given two strings low and high that represent two integers low and high where low <= high, return the number of strobogrammatic numbers in the range [low, high].
 *
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 *
 * Example 1:
 *
 * Input: low = "50", high = "100"
 * Output: 3
 * Example 2:
 *
 * Input: low = "0", high = "0"
 * Output: 1
 *
 * Constraints:
 *
 * 1 <= low.length, high.length <= 15
 * low and high consist of only digits.
 * low <= high
 * low and high do not contain any leading zeros except for zero itself.
 */
public class StrobogrammaticNumberIII {

    int res;
    long l;
    long h;
    Map<Character, Character> pairs;

    public int strobogrammaticInRange(String low, String high) {
        res = 0;
        l = Long.parseLong(low);
        h = Long.parseLong(high);
        int highLength = high.length();
        pairs = Map.of('0', '0', '1', '1', '8', '8', '9', '6', '6', '9');
        rec(highLength); // Since we always add 2 digits one at the beginning one at the end x+pre+y
        rec(highLength - 1);
        return res;
    }

    private List<String> rec(int n) {
        List<String> currNums = new ArrayList<>();
        if (n == 0) {
            currNums.add("");
            return currNums;
        }
        if (n == 1) {
            currNums.add("1");
            currNums.add("8");
            currNums.add("0");
        } else {
            List<String> preNums = rec(n - 2);
            for (String preNum : preNums) {
                for (Map.Entry<Character, Character> pair : pairs.entrySet()) {
                    currNums.add(pair.getKey() + preNum + pair.getValue());
                }
            }
        }
        for (String num : currNums) {
            if ("0".equals(num) || num.charAt(0) != '0') {
                long number = Long.parseLong(num);
                if (number >= l && number <= h) res++;
            }
        }
        return currNums;
    }

}
