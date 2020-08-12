package leetcode.companies.google;

/**
 * License Key Formatting
 * You are given a license key represented as a string S which consists only alphanumeric character and dashes. The string is separated into N+1 groups by N dashes.
 * <p>
 * Given a number K, we would want to reformat the strings such that each group contains exactly K characters, except for the first group which could be shorter than K, but still must contain at least one character. Furthermore, there must be a dash inserted between two groups and all lowercase letters should be converted to uppercase.
 * <p>
 * Given a non-empty string S and a number K, format the string according to the rules described above.
 * <p>
 * Example 1:
 * <p>
 * Input: S = "5F3Z-2e-9-w", K = 4
 * <p>
 * Output: "5F3Z-2E9W"
 * <p>
 * Explanation: The string S has been split into two parts, each part has 4 characters.
 * Note that the two extra dashes are not needed and can be removed.
 * Example 2:
 * <p>
 * Input: S = "2-5g-3-J", K = 2
 * <p>
 * Output: "2-5G-3J"
 * <p>
 * Explanation: The string S has been split into three parts, each part has 2 characters except the first part as it could be shorter as mentioned above.
 * Note:
 * <p>
 * The length of string S will not exceed 12,000, and K is a positive integer.
 * String S consists only of alphanumerical characters (a-z and/or A-Z and/or 0-9) and dashes(-).
 * String S is non-empty.
 */
public class LicenseKeyFormatting {

    public String licenseKeyFormatting(String S, int K) {
        StringBuilder result = new StringBuilder();
        int n = S.length();
        int g = 0;
        for (int i = n - 1; i >= 0; i--) {
            char c = S.charAt(i);
            if (c != '-') {
                g++;
                c = Character.toUpperCase(c);
                result.insert(0, c);
                if (g == K) {
                    result.insert(0, '-');
                    g = 0;
                }
            }
        }
        if (result.length() > 0 && result.charAt(0) == '-') result.deleteCharAt(0);
        return result.toString();
    }

    public static void main(String[] args) {
        LicenseKeyFormatting l = new LicenseKeyFormatting();
        System.out.println(l.licenseKeyFormatting("5F3Z-2e-9-w", 4));
        System.out.println(l.licenseKeyFormatting("2-5g-3-J", 2));
        System.out.println(l.licenseKeyFormatting("--a-a-a-a--", 2));
    }

}
