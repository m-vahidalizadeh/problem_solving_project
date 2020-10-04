package leetcode.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * Number of Music Playlists
 * Your music player contains N different songs and she wants to listen to L (not necessarily different) songs during your trip.  You create a playlist so that:
 * <p>
 * Every song is played at least once
 * A song can only be played again only if K other songs have been played
 * Return the number of possible playlists.  As the answer can be very large, return it modulo 10^9 + 7.
 * <p>
 * Example 1:
 * <p>
 * Input: N = 3, L = 3, K = 1
 * Output: 6
 * Explanation: There are 6 possible playlists. [1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1].
 * Example 2:
 * <p>
 * Input: N = 2, L = 3, K = 0
 * Output: 6
 * Explanation: There are 6 possible playlists. [1, 1, 2], [1, 2, 1], [2, 1, 1], [2, 2, 1], [2, 1, 2], [1, 2, 2]
 * Example 3:
 * <p>
 * Input: N = 2, L = 3, K = 1
 * Output: 2
 * Explanation: There are 2 possible playlists. [1, 2, 1], [2, 1, 2]
 * <p>
 * Note:
 * <p>
 * 0 <= K < N <= L <= 100
 */
public class NumberOfMusicPlaylists {

    public static final int FACTOR = 1_000_000_007;
    int l, n, k;
    Map<String, Long> cache;

    public int numMusicPlaylists(int N, int L, int K) {
        l = L;
        n = N;
        k = K;
        cache = new HashMap<>();
        return (int) rec(0, 0);
    }

    private long rec(int playlist, int selected) {
        if (playlist == l) return selected == n ? 1 : 0;
        String key = playlist + "," + selected;
        if (cache.containsKey(key)) return cache.get(key);
        long result = 0;
        result = ((n - selected) * rec(playlist + 1, selected + 1)) % FACTOR; // New songs
        result += (Math.max(0, selected - k) * rec(playlist + 1, selected)) % FACTOR; // reusable songs
        result = result % FACTOR;
        cache.put(key, result);
        return result;
    }

    public static void main(String[] args) {
        NumberOfMusicPlaylists n = new NumberOfMusicPlaylists();
        System.out.println(n.numMusicPlaylists(3, 3, 1));
        System.out.println(n.numMusicPlaylists(2, 3, 0));
        System.out.println(n.numMusicPlaylists(2, 3, 1));
    }

}
