package leetcode.companies.adobe;

/**
 * Pairs of Songs With Total Durations Divisible by 60
 * In a list of songs, the i-th song has a duration of time[i] seconds.
 * <p>
 * Return the number of pairs of songs for which their total duration in seconds is divisible by 60.  Formally, we want the number of indices i, j such that i < j with (time[i] + time[j]) % 60 == 0.
 * <p>
 * Example 1:
 * <p>
 * Input: [30,20,150,100,40]
 * Output: 3
 * Explanation: Three pairs have a total duration divisible by 60:
 * (time[0] = 30, time[2] = 150): total duration 180
 * (time[1] = 20, time[3] = 100): total duration 120
 * (time[1] = 20, time[4] = 40): total duration 60
 * Example 2:
 * <p>
 * Input: [60,60,60]
 * Output: 3
 * Explanation: All three pairs have a total duration of 120, which is divisible by 60.
 * <p>
 * Note:
 * <p>
 * 1 <= time.length <= 60000
 * 1 <= time[i] <= 500
 */
public class SongsTimeDivisableBy60 {

    public int numPairsDivisibleBy60(int[] time) {
        int n = time.length;
        if (n == 0) return 0;
        int counter = 0;
        int[] rem = new int[60];
        for (int i = 0; i < n; i++) {
            int tempRem = time[i] % 60;
            counter += rem[(60 - tempRem) % 60];
            rem[tempRem]++;
        }
        return counter;
    }

    public static void main(String[] args) {
        for (int i = 0; i <= 16; i++) System.out.print(60 * i + ",");
        System.out.println();
        SongsTimeDivisableBy60 s = new SongsTimeDivisableBy60();
        int[] time1 = {30, 20, 150, 100, 40};
        System.out.println(s.numPairsDivisibleBy60(time1));
        int[] time2 = {60, 60, 60};
        System.out.println(s.numPairsDivisibleBy60(time2));
    }

}
