package leetcode.medium;

public class PalindromicSubstrings {

    String s;
    int n;

    public int countSubstrings(String s) {
        this.s = s;
        int counter = 0;
        n = s.length();
        for (int i = 0; i < n; i++) {
            if (i < n - 1) {
                counter += rec(i, i + 1);
            }
            counter += rec(i, i);
        }
        return counter;
    }

    private int rec(int a, int b) {
        if (a < 0 || b >= n) return 0;
        if (s.charAt(a) != s.charAt(b)) return 0;
        return 1 + rec(a - 1, b + 1);
    }

}
