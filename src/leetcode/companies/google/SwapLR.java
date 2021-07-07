package leetcode.companies.google;

/**
 * 777. Swap Adjacent in LR String
 * In a string composed of 'L', 'R', and 'X' characters, like "RXXLRXRXL", a move consists of either replacing one occurrence of "XL" with "LX", or replacing one occurrence of "RX" with "XR". Given the starting string start and the ending string end, return True if and only if there exists a sequence of moves to transform one string to the other.
 *
 * Example 1:
 *
 * Input: start = "RXXLRXRXL", end = "XRLXXRRLX"
 * Output: true
 * Explanation: We can transform start to end following these steps:
 * RXXLRXRXL ->
 * XRXLRXRXL ->
 * XRLXRXRXL ->
 * XRLXXRRXL ->
 * XRLXXRRLX
 * Example 2:
 *
 * Input: start = "X", end = "L"
 * Output: false
 * Example 3:
 *
 * Input: start = "LLR", end = "RRL"
 * Output: false
 * Example 4:
 *
 * Input: start = "XL", end = "LX"
 * Output: true
 * Example 5:
 *
 * Input: start = "XLLR", end = "LXLX"
 * Output: false
 *
 * Constraints:
 *
 * 1 <= start.length <= 104
 * start.length == end.length
 * Both start and end will only consist of characters in 'L', 'R', and 'X'.
 */
public class SwapLR {

    public boolean canTransform(String start, String end) {
        if (!start.replace("X", "").equals(end.replace("X", ""))) return false;
        int sIndex = 0;
        int eIndex = 0;
        int n = start.length();
        while (sIndex < n && eIndex < n) {
            while (sIndex < n && start.charAt(sIndex) == 'X') sIndex++;
            while (eIndex < n && end.charAt(eIndex) == 'X') eIndex++;
            if (sIndex < n && start.charAt(sIndex) == 'R' && eIndex < sIndex) return false;
            if (sIndex < n && end.charAt(eIndex) == 'L' && sIndex < eIndex) return false;
            sIndex++;
            eIndex++;
        }
        return true;
    }

}
