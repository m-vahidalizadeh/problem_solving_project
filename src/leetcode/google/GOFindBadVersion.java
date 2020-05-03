package leetcode.google;

import java.util.HashMap;
import java.util.Map;

public class GOFindBadVersion {

    static Map<Long, Boolean> visibilityMap = new HashMap<>();

    public static void main(String[] args) {
/*
Given n = 5, and version = 4 is the first bad version.
call isBadVersion(3) -> false
call isBadVersion(5) -> true
call isBadVersion(4) -> true
Then 4 is the first bad version.
 */
/*
2126753390
1702766719
 */
        System.out.println(firstBadVersion(2126753390));
    }

    public static int firstBadVersion(int n) {
        return Long.valueOf(binarySearch(1L, Long.valueOf(n))).intValue();
    }

    public static long binarySearch(long begin, long end) {
        if (end < begin) {
            return -1L;
        }
        long mid = (begin + end) / 2;
        boolean isMidBad;
        if (visibilityMap.containsKey(mid)) {
            isMidBad = visibilityMap.get(mid);
        } else {
            isMidBad = isBadVersion(Long.valueOf(mid).intValue());
            visibilityMap.put(mid, isMidBad);
        }
        if (isMidBad) {
            if (mid == 1) {
                return mid;
            }
            boolean isMidMinusOneBad;
            if (visibilityMap.containsKey(mid - 1)) {
                isMidMinusOneBad = visibilityMap.get(mid - 1);
            } else {
                isMidMinusOneBad = isBadVersion(Long.valueOf(mid - 1).intValue());
                visibilityMap.put(mid - 1, isMidMinusOneBad);
            }
            if (!isMidMinusOneBad) {
                return mid;
            } else {
                if (begin < 1) {
                    return binarySearch(1, mid - 1);
                }
                return binarySearch(begin, mid - 1);
            }
        } else {
            if (mid < 1) {
                return binarySearch(1, end);
            }
            return binarySearch(mid + 1, end);
        }
    }

    /* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

    public static boolean isBadVersion(int input) {
        if (input >= 1702766719) {
            return true;
        }
        return false;
    }

}
