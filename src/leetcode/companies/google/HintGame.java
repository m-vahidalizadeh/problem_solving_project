package leetcode.companies.google;

import java.util.HashMap;
import java.util.Map;

/**
 * Bulls and Cows
 * You are playing the Bulls and Cows game with your friend.
 * <p>
 * You write down a secret number and ask your friend to guess what the number is. When your friend makes a guess, you provide a hint with the following info:
 * <p>
 * The number of "bulls", which are digits in the guess that are in the correct position.
 * The number of "cows", which are digits in the guess that are in your secret number but are located in the wrong position. Specifically, the non-bull digits in the guess that could be rearranged such that they become bulls.
 * Given the secret number secret and your friend's guess guess, return the hint for your friend's guess.
 * <p>
 * The hint should be formatted as "xAyB", where x is the number of bulls and y is the number of cows. Note that both secret and guess may contain duplicate digits.
 * <p>
 * Example 1:
 * <p>
 * Input: secret = "1807", guess = "7810"
 * Output: "1A3B"
 * Explanation: Bulls are connected with a '|' and cows are underlined:
 * "1807"
 * |
 * "7810"
 * Example 2:
 * <p>
 * Input: secret = "1123", guess = "0111"
 * Output: "1A1B"
 * Explanation: Bulls are connected with a '|' and cows are underlined:
 * "1123"        "1123"
 * |      or     |
 * "0111"        "0111"
 * Note that only one of the two unmatched 1s is counted as a cow since the non-bull digits can only be rearranged to allow one 1 to be a bull.
 * Example 3:
 * <p>
 * Input: secret = "1", guess = "0"
 * Output: "0A0B"
 * Example 4:
 * <p>
 * Input: secret = "1", guess = "1"
 * Output: "1A0B"
 * <p>
 * Constraints:
 * <p>
 * 1 <= secret.length, guess.length <= 1000
 * secret.length == guess.length
 * secret and guess consist of digits only.
 */
public class HintGame {

    public String getHint(String secret, String guess) {
        int A = 0;
        int n = secret.length();
        char[] sChars = secret.toCharArray();
        char[] gChars = guess.toCharArray();
        Map<Character, Integer> potentials = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (sChars[i] == gChars[i]) A++;
            else potentials.put(sChars[i], potentials.getOrDefault(sChars[i], 0) + 1);
        }
        int B = 0;
        for (int i = 0; i < n; i++) {
            if (sChars[i] != gChars[i] && potentials.getOrDefault(gChars[i], 0) > 0) {
                B++;
                potentials.put(gChars[i], potentials.get(gChars[i]) - 1);
            }
        }
        return String.format("%dA%dB", A, B);
    }

    public static void main(String[] args) {
        HintGame h = new HintGame();
        System.out.println(h.getHint("1807", "7810"));
        System.out.println(h.getHint("1123", "0111"));
        System.out.println(h.getHint("1", "0"));
        System.out.println(h.getHint("1", "1"));
    }

}
