package leetcode.hard;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * 1320. Minimum Distance to Type a Word Using Two Fingers
 * You have a keyboard layout as shown above in the XY plane, where each English uppercase letter is located at some coordinate, for example, the letter A is located at coordinate (0,0), the letter B is located at coordinate (0,1), the letter P is located at coordinate (2,3) and the letter Z is located at coordinate (4,1).
 * <p>
 * Given the string word, return the minimum total distance to type such string using only two fingers. The distance between coordinates (x1,y1) and (x2,y2) is |x1 - x2| + |y1 - y2|.
 * <p>
 * Note that the initial positions of your two fingers are considered free so don't count towards your total distance, also your two fingers do not have to start at the first letter or the first two letters.
 * <p>
 * Example 1:
 * <p>
 * Input: word = "CAKE"
 * Output: 3
 * Explanation:
 * Using two fingers, one optimal way to type "CAKE" is:
 * Finger 1 on letter 'C' -> cost = 0
 * Finger 1 on letter 'A' -> cost = Distance from letter 'C' to letter 'A' = 2
 * Finger 2 on letter 'K' -> cost = 0
 * Finger 2 on letter 'E' -> cost = Distance from letter 'K' to letter 'E' = 1
 * Total distance = 3
 * Example 2:
 * <p>
 * Input: word = "HAPPY"
 * Output: 6
 * Explanation:
 * Using two fingers, one optimal way to type "HAPPY" is:
 * Finger 1 on letter 'H' -> cost = 0
 * Finger 1 on letter 'A' -> cost = Distance from letter 'H' to letter 'A' = 2
 * Finger 2 on letter 'P' -> cost = 0
 * Finger 2 on letter 'P' -> cost = Distance from letter 'P' to letter 'P' = 0
 * Finger 1 on letter 'Y' -> cost = Distance from letter 'A' to letter 'Y' = 4
 * Total distance = 6
 * Example 3:
 * <p>
 * Input: word = "NEW"
 * Output: 3
 * Example 4:
 * <p>
 * Input: word = "YEAR"
 * Output: 7
 * <p>
 * Constraints:
 * <p>
 * 2 <= word.length <= 300
 * Each word[i] is an English uppercase letter.
 */
public class TypeWithTwoFingers {

    Map<Character, Pair<Integer, Integer>> letterCoordinates;
    String word;
    int l;
    Map<String, Integer> cache;

    public int minimumDistance(String word) {
        buildLetterCoordinates();
        this.word = word;
        this.l = word.length();
        this.cache = new HashMap<>();
        return rec(new Pair<>(-1, -1), new Pair<>(-1, -1), 0, 0);
    }

    private int rec(Pair<Integer, Integer> finger1, Pair<Integer, Integer> finger2, int i, int currCost) {
        if (i == l) return currCost;
        String key1 = getKey(finger1, finger2, i);
        String key2 = getKey(finger2, finger1, i);
        if (cache.containsKey(key1)) return currCost + cache.get(key1);
        Pair<Integer, Integer> target = letterCoordinates.get(word.charAt(i));
        int min = Integer.MAX_VALUE;
        // Type with finger 1
        if (finger1.getKey() == -1) {
            min = Math.min(min, rec(target, finger2, i + 1, currCost));
        } else {
            min = Math.min(min, rec(target, finger2, i + 1, currCost + getDistance(finger1, target)));
        }
        // Type with finger 2
        if (finger2.getKey() == -1) {
            min = Math.min(min, rec(finger1, target, i + 1, currCost));
        } else {
            min = Math.min(min, rec(finger1, target, i + 1, currCost + getDistance(finger2, target)));
        }
        cache.put(key1, min - currCost);
        cache.put(key2, min - currCost);
        return min;
    }

    private String getKey(Pair<Integer, Integer> coordinate1, Pair<Integer, Integer> coordinate2, int i) {
        return coordinate1.getKey() + "-" +
                coordinate1.getValue() + "-" +
                coordinate2.getKey() + "-" +
                coordinate2.getValue() + "-" +
                i;
    }

    private int getDistance(Pair<Integer, Integer> coordinate1, Pair<Integer, Integer> coordinate2) {
        return Math.abs(coordinate1.getKey() - coordinate2.getKey()) + Math.abs(coordinate1.getValue() - coordinate2.getValue());
    }

    private void buildLetterCoordinates() {
        letterCoordinates = new HashMap<>();
        char letter = 'A';
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                letterCoordinates.put(letter, new Pair<>(i, j));
                if (letter == 'Z') break;
                letter++;
            }
        }
    }

    public static void main(String[] args) {
        TypeWithTwoFingers typeWithTwoFingers = new TypeWithTwoFingers();
        System.out.println(typeWithTwoFingers.minimumDistance("CAKE"));
        System.out.println(typeWithTwoFingers.minimumDistance("HAPPY"));
        System.out.println(typeWithTwoFingers.minimumDistance("NEW"));
        System.out.println(typeWithTwoFingers.minimumDistance("YEAR"));
        System.out.println(typeWithTwoFingers.minimumDistance("LSGQE"));
    }

}
