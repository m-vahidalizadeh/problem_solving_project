package leetcode.hard;

/**
 * 1842. Next Palindrome Using Same Digits
 * You are given a numeric string num, representing a very large palindrome.
 *
 * Return the smallest palindrome larger than num that can be created by rearranging its digits. If no such palindrome exists, return an empty string "".
 *
 * A palindrome is a number that reads the same backward as forward.
 *
 * Example 1:
 *
 * Input: num = "1221"
 * Output: "2112"
 * Explanation: The next palindrome larger than "1221" is "2112".
 * Example 2:
 *
 * Input: num = "32123"
 * Output: ""
 * Explanation: No palindromes larger than "32123" can be made by rearranging the digits.
 * Example 3:
 *
 * Input: num = "45544554"
 * Output: "54455445"
 * Explanation: The next palindrome larger than "45544554" is "54455445".
 *
 * Constraints:
 *
 * 1 <= num.length <= 105
 * num is a palindrome.
 */
public class NextPalindromeUsingSameDigits {

    public String nextPalindrome(String num) {
        int n = num.length();
        int[] arr = new int[n / 2];
        for (int i = 0; i < arr.length; i++) arr[i] = num.charAt(i) - '0';
        if (!nextPermutation(arr)) return "";
        StringBuilder sb = new StringBuilder();
        for (int item : arr) sb.append(item);
        return n % 2 == 0 ? sb.toString() + sb.reverse().toString() : sb.toString() + num.substring(n / 2, n / 2 + 1) + sb.reverse().toString();
    }

    private boolean nextPermutation(int[] arr) {
        int n = arr.length;
        int p = -1;
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] < arr[i + 1]) {
                p = i;
                break;
            }
        }
        if (p == -1) {
            reverse(arr, 0, n - 1);
            return false;
        }
        for (int i = n - 1; i >= 0; i--) {
            if (arr[p] < arr[i]) {
                swap(arr, p, i);
                break;
            }
        }
        reverse(arr, p + 1, n - 1);
        return true;
    }

    private void reverse(int[] arr, int s, int e) {
        while (s < e) swap(arr, s++, e--);
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
