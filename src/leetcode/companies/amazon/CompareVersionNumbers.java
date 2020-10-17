package leetcode.companies.amazon;

/**
 * 165. Compare Version Numbers
 * Given two version numbers, version1 and version2, compare them.
 * <p>
 * Version numbers consist of one or more revisions joined by a dot '.'. Each revision consists of digits and may contain leading zeros. Every revision contains at least one character. Revisions are 0-indexed from left to right, with the leftmost revision being revision 0, the next revision being revision 1, and so on. For example 2.5.33 and 0.1 are valid version numbers.
 * <p>
 * To compare version numbers, compare their revisions in left-to-right order. Revisions are compared using their integer value ignoring any leading zeros. This means that revisions 1 and 001 are considered equal. If a version number does not specify a revision at an index, then treat the revision as 0. For example, version 1.0 is less than version 1.1 because their revision 0s are the same, but their revision 1s are 0 and 1 respectively, and 0 < 1.
 * <p>
 * Return the following:
 * <p>
 * If version1 < version2, return -1.
 * If version1 > version2, return 1.
 * Otherwise, return 0.
 * <p>
 * Example 1:
 * <p>
 * Input: version1 = "1.01", version2 = "1.001"
 * Output: 0
 * Explanation: Ignoring leading zeroes, both "01" and "001" represent the same integer "1".
 * Example 2:
 * <p>
 * Input: version1 = "1.0", version2 = "1.0.0"
 * Output: 0
 * Explanation: version1 does not specify revision 2, which means it is treated as "0".
 * Example 3:
 * <p>
 * Input: version1 = "0.1", version2 = "1.1"
 * Output: -1
 * Explanation: version1's revision 0 is "0", while version2's revision 0 is "1". 0 < 1, so version1 < version2.
 * Example 4:
 * <p>
 * Input: version1 = "1.0.1", version2 = "1"
 * Output: 1
 * Example 5:
 * <p>
 * Input: version1 = "7.5.2.4", version2 = "7.5.3"
 * Output: -1
 * <p>
 * Constraints:
 * <p>
 * 1 <= version1.length, version2.length <= 500
 * version1 and version2 only contain digits and '.'.
 * version1 and version2 are valid version numbers.
 * All the given revisions in version1 and version2 can be stored in a 32-bit integer.
 */
public class CompareVersionNumbers {

    public int compareVersion(String version1, String version2) {
        int i1 = 0;
        int i2 = 0;
        int n1 = version1.length();
        int n2 = version2.length();
        while (i1 < n1 || i2 < n2) {
            // Fid num1
            int num1 = 0;
            if (i1 < n1) {
                StringBuilder s1 = new StringBuilder();
                while (i1 < n1 && version1.charAt(i1) != '.') {
                    s1.append(version1.charAt(i1++));
                }
                num1 = Integer.parseInt(s1.toString());
                if (i1 < n1) {
                    version1 = version1.substring(i1 + 1, n1);
                    n1 = version1.length();
                    i1 = 0;
                }
            }
            // Find num2
            int num2 = 0;
            if (i2 < n2) {
                StringBuilder s2 = new StringBuilder();
                while (i2 < n2 && version2.charAt(i2) != '.') {
                    s2.append(version2.charAt(i2++));
                }
                num2 = Integer.parseInt(s2.toString());
                if (i2 < n2) {
                    version2 = version2.substring(i2 + 1, n2);
                    n2 = version2.length();
                    i2 = 0;
                }
            }
            if (num1 > num2) return 1;
            if (num1 < num2) return -1;
        }
        return 0;
    }

    public static void main(String[] args) {
        CompareVersionNumbers c = new CompareVersionNumbers();
        System.out.println(c.compareVersion("1.01", "1.001"));
        System.out.println(c.compareVersion("1.0", "1.0.0"));
        System.out.println(c.compareVersion("0.1", "1.1"));
        System.out.println(c.compareVersion("1.0.1", "1"));
        System.out.println(c.compareVersion("7.5.2.4", "7.5.3"));
    }

}
