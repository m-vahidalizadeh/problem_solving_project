package leetcode.companies.google;

import java.util.HashSet;
import java.util.Set;

/**
 * Cracking the Safe
 * There is a box protected by a password. The password is a sequence of n digits where each digit can be one of the first k digits 0, 1, ..., k-1.
 * <p>
 * While entering a password, the last n digits entered will automatically be matched against the correct password.
 * <p>
 * For example, assuming the correct password is "345", if you type "012345", the box will open because the correct password matches the suffix of the entered password.
 * <p>
 * Return any password of minimum length that is guaranteed to open the box at some point of entering it.
 * <p>
 * Example 1:
 * <p>
 * Input: n = 1, k = 2
 * Output: "01"
 * Note: "10" will be accepted too.
 * Example 2:
 * <p>
 * Input: n = 2, k = 2
 * Output: "00110"
 * Note: "01100", "10011", "11001" will be accepted too.
 * <p>
 * Note:
 * <p>
 * n will be in the range [1, 4].
 * k will be in the range [1, 10].
 * k^n will be at most 4096.
 */
public class CrackSafe {

    private Set<String> seen;
    private StringBuilder result;

    public String crackSafe(int n, int k) {
        if (n == 1 && k == 1) return "0";
        seen = new HashSet<>();
        result = new StringBuilder();
        StringBuilder startSb = new StringBuilder();
        startSb.append("0".repeat(n - 1));
        dfs(startSb.toString(), k);
        result.append(startSb);
        return result.toString();
    }

    private void dfs(String node, int k) {
        for (int i = 0; i < k; i++) {
            String n = node + i;
            if (!seen.contains(n)) {
                seen.add(n);
                dfs(n.substring(1), k);
                result.append(i);
            }
        }
    }

}
