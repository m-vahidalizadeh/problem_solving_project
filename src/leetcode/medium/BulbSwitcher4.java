package leetcode.medium;

/**
 * Bulb Switcher IV
 * There is a room with n bulbs, numbered from 0 to n-1, arranged in a row from left to right. Initially all the bulbs are turned off.
 * <p>
 * Your task is to obtain the configuration represented by target where target[i] is '1' if the i-th bulb is turned on and is '0' if it is turned off.
 * <p>
 * You have a switch to flip the state of the bulb, a flip operation is defined as follows:
 * <p>
 * Choose any bulb (index i) of your current configuration.
 * Flip each bulb from index i to n-1.
 * When any bulb is flipped it means that if it is 0 it changes to 1 and if it is 1 it changes to 0.
 * <p>
 * Return the minimum number of flips required to form target.
 * <p>
 * Example 1:
 * <p>
 * Input: target = "10111"
 * Output: 3
 * Explanation: Initial configuration "00000".
 * flip from the third bulb:  "00000" -> "00111"
 * flip from the first bulb:  "00111" -> "11000"
 * flip from the second bulb:  "11000" -> "10111"
 * We need at least 3 flip operations to form target.
 * Example 2:
 * <p>
 * Input: target = "101"
 * Output: 3
 * Explanation: "000" -> "111" -> "100" -> "101".
 * Example 3:
 * <p>
 * Input: target = "00000"
 * Output: 0
 * Example 4:
 * <p>
 * Input: target = "001011101"
 * Output: 5
 * <p>
 * Constraints:
 * <p>
 * 1 <= target.length <= 10^5
 * target[i] == '0' or target[i] == '1'
 */
public class BulbSwitcher4 {

    public int minFlips(String target) {
        char[] targetChars = target.toCharArray();
        int n = targetChars.length;
        int counter = 0;
        int i = 0;
        char prev = '0';
        while (i < n && targetChars[i] == '0') i++;
        for (; i < n; i++) {
            if (targetChars[i] != prev) counter++;
            prev = targetChars[i];
        }
        return counter;
    }

}
