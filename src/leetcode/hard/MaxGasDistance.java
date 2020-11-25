package leetcode.hard;

/**
 * 774. Minimize Max Distance to Gas Station
 * On a horizontal number line, we have gas stations at positions stations[0], stations[1], ..., stations[N-1], where N = stations.length.
 *
 * Now, we add K more gas stations so that D, the maximum distance between adjacent gas stations, is minimized.
 *
 * Return the smallest possible value of D.
 *
 * Example:
 *
 * Input: stations = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], K = 9
 * Output: 0.500000
 * Note:
 *
 * stations.length will be an integer in range [10, 2000].
 * stations[i] will be an integer in range [0, 10^8].
 * K will be an integer in range [1, 10^6].
 * Answers within 10^-6 of the true value will be accepted as correct.
 */
public class MaxGasDistance {

    public double minmaxGasDist(int[] stations, int K) {
        double left = 0;
        double right = Math.pow(10, 8);
        double range = Math.pow(10, -6);
        int n = stations.length;
        while (range < right - left) {
            double mid = (left + right) / 2;
            if (binarySearch(mid, stations, K, n)) right = mid;
            else left = mid;
        }
        return left;
    }

    private boolean binarySearch(double d, int[] stations, int k, int n) {
        int numGasStations = 0;
        for (int i = 1; i < n; i++) numGasStations +=
                (int) ((stations[i] - stations[i - 1]) / d); // 0 if the distance is < d otherwise we use gas stations
        return numGasStations <= k;
    }

    public static void main(String[] args) {
        MaxGasDistance maxGasDistance = new MaxGasDistance();
        System.out.println(maxGasDistance.minmaxGasDist(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 9));
        System.out.println(maxGasDistance.minmaxGasDist(new int[]{10, 19, 25, 27, 56, 63, 70, 87, 96, 97}, 3));
    }

}
