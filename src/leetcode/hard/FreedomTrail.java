package leetcode.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 514. Freedom Trail
 * In the video game Fallout 4, the quest "Road to Freedom" requires players to reach a metal dial called the "Freedom Trail Ring", and use the dial to spell a specific keyword in order to open the door.
 *
 * Given a string ring, which represents the code engraved on the outer ring and another string key, which represents the keyword needs to be spelled. You need to find the minimum number of steps in order to spell all the characters in the keyword.
 *
 * Initially, the first character of the ring is aligned at 12:00 direction. You need to spell all the characters in the string key one by one by rotating the ring clockwise or anticlockwise to make each character of the string key aligned at 12:00 direction and then by pressing the center button.
 *
 * At the stage of rotating the ring to spell the key character key[i]:
 *
 * You can rotate the ring clockwise or anticlockwise one place, which counts as 1 step. The final purpose of the rotation is to align one of the string ring's characters at the 12:00 direction, where this character must equal to the character key[i].
 * If the character key[i] has been aligned at the 12:00 direction, you need to press the center button to spell, which also counts as 1 step. After the pressing, you could begin to spell the next character in the key (next stage), otherwise, you've finished all the spelling.
 * Example:
 *
 * Input: ring = "godding", key = "gd"
 * Output: 4
 * Explanation:
 * For the first key character 'g', since it is already in place, we just need 1 step to spell this character.
 * For the second key character 'd', we need to rotate the ring "godding" anticlockwise by two steps to make it become "ddinggo".
 * Also, we need 1 more step for spelling.
 * So the final output is 4.
 * Note:
 *
 * Length of both ring and key will be in range 1 to 100.
 * There are only lowercase letters in both strings and might be some duplcate characters in both strings.
 * It's guaranteed that string key could always be spelled by rotating the string ring.
 */
public class FreedomTrail {

    int rN;
    int kN;
    Map<Character, List<Integer>> indexMap;
    String ring;
    String key;
    Integer[][] cache;

    public int findRotateSteps(String ring, String key) {
        rN = ring.length();
        kN = key.length();
        indexMap = new HashMap<>();
        this.ring = ring;
        this.key = key;
        cache = new Integer[kN][rN];
        for (int i = 0; i < rN; i++) { // Record which char exists in which index
            char c = ring.charAt(i);
            indexMap.computeIfAbsent(c, x -> new ArrayList<>());
            indexMap.get(c).add(i);
        }
        return dfs(0, 0);
    }

    private int dfs(int kI, int rI) {
        if (kI == kN) return 0; // No more char to spell
        if (cache[kI][rI] != null) return cache[kI][rI]; // Use the cache
        int min = Integer.MAX_VALUE;
        for (int i : indexMap.get(key.charAt(kI))) { // Explore all different ways to spell key.charAt(kI)
            int diff = Math.abs(i - rI); // The move between i and ring index (rI)
            int cost = Math.min(diff, rN - diff) + 1; // min(clockwise,anti-clockwise)+1-> 1 is the cost to press
            min = Math.min(min, cost + dfs(kI + 1, i)); // Does current path minimize the answer?
        }
        cache[kI][rI] = min; // Add the answer to the cache
        return min; // Return the min
    }

}
