package leetcode.companies.google;

import java.util.HashMap;
import java.util.Map;

/**
 * Please note that both secret number and friend's guess may contain duplicate digits.
 * <p>
 * Example 1:
 * <p>
 * Input: secret = "1807", guess = "7810"
 * <p>
 * Output: "1A3B"
 * <p>
 * Explanation: 1 bull and 3 cows. The bull is 8, the cows are 0, 1 and 7.
 * Example 2:
 * <p>
 * Input: secret = "1123", guess = "0111"
 * <p>
 * Output: "1A1B"
 * <p>
 * Explanation: The 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow.
 * Note: You may assume that the secret number and your friend's guess only contain digits, and their lengths are always equal.
 */
public class BullsAndCows {

    public String getHint(String secret, String guess) {
        int n = secret.length();
        int a = 0;
        int b = 0;
        char[] secretA = secret.toCharArray();
        char[] guessA = guess.toCharArray();
        Map<Character, Integer> digits = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (secretA[i] == guessA[i]) a++;
            else digits.put(secretA[i], digits.getOrDefault(secretA[i], 0) + 1);
        }
        for (int i = 0; i < n; i++) {
            if (secretA[i] != guessA[i] && digits.containsKey(guessA[i])) {
                int rem = digits.get(guessA[i]);
                if (rem > 0) {
                    b++;
                    digits.put(guessA[i], digits.get(guessA[i]) - 1);
                }
            }
        }
        return String.format("%sA%sB", a, b);
    }

    public static void main(String[] args) {
        BullsAndCows b = new BullsAndCows();
        System.out.println(b.getHint("1807", "7810"));
        System.out.println(b.getHint("1123", "0111"));
        System.out.println(b.getHint("11", "10"));
    }

}
