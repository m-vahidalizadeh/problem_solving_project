package leetcode.companies.google;

/**
 * Peak Index in a Mountain Array
 * Let's call an array arr a mountain if the following properties hold:
 * <p>
 * arr.length >= 3
 * There exists some i with 0 < i < arr.length - 1 such that:
 * arr[0] < arr[1] < ... arr[i-1] < arr[i]
 * arr[i] > arr[i+1] > ... > arr[arr.length - 1]
 * Given an integer array arr that is guaranteed to be a mountain, return any i such that arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1].
 * <p>
 * Example 1:
 * <p>
 * Input: arr = [0,1,0]
 * Output: 1
 * Example 2:
 * <p>
 * Input: arr = [0,2,1,0]
 * Output: 1
 * Example 3:
 * <p>
 * Input: arr = [0,10,5,2]
 * Output: 1
 * Example 4:
 * <p>
 * Input: arr = [3,4,5,1]
 * Output: 2
 * Example 5:
 * <p>
 * Input: arr = [24,69,100,99,79,78,67,36,26,19]
 * Output: 2
 * <p>
 * Constraints:
 * <p>
 * 3 <= arr.length <= 104
 * 0 <= arr[i] <= 106
 * arr is guaranteed to be a mountain array.
 */
public class PeakIndexInAMountainArray {

    public int peakIndexInMountainArray(int[] arr) {
        int n = arr.length;
        int[] l = new int[n];
        int[] r = new int[n];
        for (int i = 0; i < n; i++) {
            l[i] = Math.max(i == 0 ? arr[i] : arr[i - 1], arr[i]);
        }
        for (int i = n - 1; i >= 0; i--) {
            r[i] = Math.max(i == n - 1 ? arr[i] : arr[i + 1], arr[i]);
        }
        for (int i = 0; i < n; i++) {
            if (l[i] == arr[i] && arr[i] == r[i]) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        PeakIndexInAMountainArray p = new PeakIndexInAMountainArray();
        System.out.println(p.peakIndexInMountainArray(new int[]{0, 1, 0}));
        System.out.println(p.peakIndexInMountainArray(new int[]{0, 2, 1, 0}));
        System.out.println(p.peakIndexInMountainArray(new int[]{0, 10, 5, 2}));
        System.out.println(p.peakIndexInMountainArray(new int[]{3, 4, 5, 1}));
        System.out.println(p.peakIndexInMountainArray(new int[]{24, 69, 100, 99, 79, 78, 67, 36, 26, 19}));
    }

}
