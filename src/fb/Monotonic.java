package fb;

/**
 * An array is monotonic if it is either monotone increasing or monotone decreasing.
 * <p>
 * An array A is monotone increasing if for all i <= j, A[i] <= A[j].  An array A is monotone decreasing if for all i <= j, A[i] >= A[j].
 * <p>
 * Return true if and only if the given array A is monotonic.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: [1,2,2,3]
 * Output: true
 * Example 2:
 * <p>
 * Input: [6,5,4,4]
 * Output: true
 * Example 3:
 * <p>
 * Input: [1,3,2]
 * Output: false
 * Example 4:
 * <p>
 * Input: [1,2,4,5]
 * Output: true
 * Example 5:
 * <p>
 * Input: [1,1,1]
 * Output: true
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= A.length <= 50000
 * -100000 <= A[i] <= 100000
 */
public class Monotonic {

    private boolean isIncreasing(int[] A, int n) {
        for (int i = 0; i < n - 1; i++) {
            if (A[i] < A[i + 1]) {
                return true;
            } else if (A[i] > A[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public boolean isMonotonic(int[] A) {
        int n = A.length;
        if (n == 0 || n == 1)
            return true;
        boolean isIncreasing = isIncreasing(A, n);
        if (isIncreasing) {
            // Increasing tone
            for (int i = 0; i < n - 1; i++) {
                if (A[i] > A[i + 1]) {
                    return false;
                }
            }
            return true;
        } else {
            // Decreasing tone
            for (int i = n - 1; i >= 1; i--) {
                if (A[i - 1] < A[i]) {
                    return false;
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        Monotonic m = new Monotonic();
        int[] input1 = {1, 2, 2, 3};
        System.out.println(m.isMonotonic(input1));
        int[] input2 = {6, 5, 4, 4};
        System.out.println(m.isMonotonic(input2));
        int[] input3 = {1, 3, 2};
        System.out.println(m.isMonotonic(input3));
        int[] input4 = {1, 2, 4, 5};
        System.out.println(m.isMonotonic(input4));
        int[] input5 = {1, 1, 1};
        System.out.println(m.isMonotonic(input5));
    }

}
