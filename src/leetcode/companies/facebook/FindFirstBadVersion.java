package leetcode.companies.facebook;

import java.util.HashMap;
import java.util.Map;

/**
 * 278. First Bad Version
 * Solved
 * Easy
 *
 * Topics
 *
 * Companies
 * You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.
 *
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.
 *
 * You are given an API bool isBadVersion(version) which returns whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.
 *
 * Example 1:
 *
 * Input: n = 5, bad = 4
 * Output: 4
 * Explanation:
 * call isBadVersion(3) -> false
 * call isBadVersion(5) -> true
 * call isBadVersion(4) -> true
 * Then 4 is the first bad version.
 * Example 2:
 *
 * Input: n = 1, bad = 1
 * Output: 1
 *
 * Constraints:
 *
 * 1 <= bad <= n <= 2^31 - 1
 */
public class FindFirstBadVersion {

    public int firstBadVersion(int n) {
        int left = 1, right = n;
        Map<Integer, Boolean> bad = new HashMap<>();
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (isBad(mid, bad)) {
                if ((mid == 1) || (!isBad(mid - 1, bad))) {
                    return mid;
                }
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean isBad(int version, Map<Integer, Boolean> bad) {
        if (bad.containsKey(version)) return bad.get(version);
        boolean res = isBadVersion(version);
        bad.put(version, res);
        return res;
    }

    private boolean isBadVersion(int version) {
        return version >= 1702766719;
    }

}
