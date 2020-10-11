package leetcode.medium;

import java.util.*;

/**
 * 1244. Design A Leaderboard
 * Design a Leaderboard class, which has 3 functions:
 * <p>
 * addScore(playerId, score): Update the leaderboard by adding score to the given player's score. If there is no player with such id in the leaderboard, add him to the leaderboard with the given score.
 * top(K): Return the score sum of the top K players.
 * reset(playerId): Reset the score of the player with the given id to 0 (in other words erase it from the leaderboard). It is guaranteed that the player was added to the leaderboard before calling this function.
 * Initially, the leaderboard is empty.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * ["Leaderboard","addScore","addScore","addScore","addScore","addScore","top","reset","reset","addScore","top"]
 * [[],[1,73],[2,56],[3,39],[4,51],[5,4],[1],[1],[2],[2,51],[3]]
 * Output:
 * [null,null,null,null,null,null,73,null,null,null,141]
 * <p>
 * Explanation:
 * Leaderboard leaderboard = new Leaderboard ();
 * leaderboard.addScore(1,73);   // leaderboard = [[1,73]];
 * leaderboard.addScore(2,56);   // leaderboard = [[1,73],[2,56]];
 * leaderboard.addScore(3,39);   // leaderboard = [[1,73],[2,56],[3,39]];
 * leaderboard.addScore(4,51);   // leaderboard = [[1,73],[2,56],[3,39],[4,51]];
 * leaderboard.addScore(5,4);    // leaderboard = [[1,73],[2,56],[3,39],[4,51],[5,4]];
 * leaderboard.top(1);           // returns 73;
 * leaderboard.reset(1);         // leaderboard = [[2,56],[3,39],[4,51],[5,4]];
 * leaderboard.reset(2);         // leaderboard = [[3,39],[4,51],[5,4]];
 * leaderboard.addScore(2,51);   // leaderboard = [[2,51],[3,39],[4,51],[5,4]];
 * leaderboard.top(3);           // returns 141 = 51 + 51 + 39;
 * <p>
 * Constraints:
 * <p>
 * 1 <= playerId, K <= 10000
 * It's guaranteed that K is less than or equal to the current number of players.
 * 1 <= score <= 100
 * There will be at most 1000 function calls.
 */
public class Leaderboard {

    Map<Integer, Integer> map;
    TreeMap<Integer, Integer> treeMap;

    public Leaderboard() {
        map = new HashMap<>();
        treeMap = new TreeMap<>(Comparator.reverseOrder());
    }

    public void addScore(int playerId, int score) {
        if (map.containsKey(playerId)) {
            int existingScore = map.get(playerId);
            treeMap.put(existingScore, treeMap.get(existingScore) - 1);
            int newScore = existingScore + score;
            treeMap.put(newScore, treeMap.getOrDefault(newScore, 0) + 1);
            map.put(playerId, newScore);
        } else {
            treeMap.put(score, treeMap.getOrDefault(score, 0) + 1);
            map.put(playerId, score);
        }
    }

    public int top(int K) {
        int sum = 0;
        int added = 0;
        for (int score : treeMap.keySet()) {
            int count = treeMap.get(score);
            for (int i = 0; i < count; i++) {
                sum += score;
                added++;
                if (added == K) return sum;
            }
        }
        return sum;
    }

    public void reset(int playerId) {
        if (map.containsKey(playerId)) {
            int existingScore = map.get(playerId);
            treeMap.put(existingScore, treeMap.get(existingScore) - 1);
            treeMap.put(0, treeMap.getOrDefault(0, 0) + 1);
            map.put(playerId, 0);
        }
    }

}
