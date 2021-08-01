package leetcode.hard;

import javafx.util.Pair;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 818. Race Car
 * Your car starts at position 0 and speed +1 on an infinite number line. Your car can go into negative positions. Your car drives automatically according to a sequence of instructions 'A' (accelerate) and 'R' (reverse):
 *
 * When you get an instruction 'A', your car does the following:
 * position += speed
 * speed *= 2
 * When you get an instruction 'R', your car does the following:
 * If your speed is positive then speed = -1
 * otherwise speed = 1
 * Your position stays the same.
 * For example, after commands "AAR", your car goes to positions 0 --> 1 --> 3 --> 3, and your speed goes to 1 --> 2 --> 4 --> -1.
 *
 * Given a target position target, return the length of the shortest sequence of instructions to get there.
 *
 * Example 1:
 *
 * Input: target = 3
 * Output: 2
 * Explanation:
 * The shortest instruction sequence is "AA".
 * Your position goes from 0 --> 1 --> 3.
 * Example 2:
 *
 * Input: target = 6
 * Output: 5
 * Explanation:
 * The shortest instruction sequence is "AAARA".
 * Your position goes from 0 --> 1 --> 3 --> 7 --> 7 --> 6.
 *
 * Constraints:
 *
 * 1 <= target <= 104
 */
public class RaceCar {

    public int racecar(int target) {
        Set<Pair<Integer, Integer>> seen = new HashSet<>();
        seen.add(new Pair<>(0, 1));
        Queue<Pair<Integer, Integer>> q = new LinkedList<>();
        q.add(new Pair<>(0, 1));
        int steps = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Pair<Integer, Integer> curr = q.poll();
                int newPos = curr.getKey() + curr.getValue();
                if (newPos == target) return steps + 1;
                int newSpeed = curr.getValue() * 2;
                if (
                        (Math.abs(newSpeed) < 2 * target) &&
                                (Math.abs(newPos) < 2 * target) &&
                                !seen.contains(new Pair<Integer, Integer>(newPos, newSpeed))
                ) {
                    q.add(new Pair<>(newPos, newSpeed));
                    seen.add(new Pair<>(newPos, newSpeed));
                }
                newSpeed = curr.getValue() > 0 ? -1 : 1;
                if (!seen.contains(new Pair<Integer, Integer>(curr.getKey(), newSpeed))) {
                    q.add(new Pair<>(curr.getKey(), newSpeed));
                    seen.add(new Pair<>(curr.getKey(), newSpeed));
                }
            }
            steps++;
        }
        return -1;
    }

}
