package leetcode.companies.google;

import java.util.*;

/**
 * Strobogrammatic Number II
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 * <p>
 * Find all strobogrammatic numbers that are of length = n.
 * <p>
 * Example:
 * <p>
 * Input:  n = 2
 * Output: ["11","69","88","96"]
 */
public class StrobogrammaticNumberII {

    Map<Integer, Integer> rotations;
    List<String> result;
    List<String> centerRotations;

    public List<String> findStrobogrammatic(int n) {
        if (n == 0) return Collections.emptyList();
        centerRotations = List.of("0", "1", "8");
        if (n == 1) return centerRotations;
        rotations = new HashMap<>() {{
            put(0, 0);
            put(1, 1);
            put(6, 9);
            put(8, 8);
            put(9, 6);
        }};
        result = new ArrayList<>();
        addStrobogrammatic(0, n - 1, new int[n]);
        return result;
    }

    private void addStrobogrammatic(int l, int r, int[] curr) {
        if (l > r) {
            StringBuilder sb = new StringBuilder();
            for (int n : curr) sb.append(n);
            result.add(sb.toString());
        } else if (l == r) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < l; i++) sb.append(curr[i]);
            String left = sb.toString();
            sb = new StringBuilder();
            for (int i = r + 1; i < curr.length; i++) sb.append(curr[i]);
            String right = sb.toString();
            for (String i : centerRotations) {
                result.add(left + i + right);
            }
        } else {
            for (Map.Entry<Integer, Integer> e : rotations.entrySet()) {
                if (l == 0 && e.getKey() == 0) continue;
                curr[l] = e.getKey();
                curr[r] = e.getValue();
                addStrobogrammatic(l + 1, r - 1, curr);
            }
        }
    }

    public static void main(String[] args) {
        StrobogrammaticNumberII s = new StrobogrammaticNumberII();
        System.out.println(s.findStrobogrammatic(2));
    }

}
