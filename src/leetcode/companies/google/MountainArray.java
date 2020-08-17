package leetcode.companies.google;

public class MountainArray {

    public static void main(String[] args) {
        int[] input = new int[]{
                0, 2, 3, 4, 5, 2, 1, 0
        };
        System.out.println(validMountainArray(input));
        input = new int[]{
                0, 2, 3, 3, 5, 2, 1, 0
        };
        System.out.println(validMountainArray(input));
        input = new int[]{
                0, 3, 2, 1
        };
        System.out.println(validMountainArray(input));
        input = new int[]{
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9
        };
        System.out.println(validMountainArray(input));
    }

    public static boolean validMountainArray(int[] A) {
        int n = A.length;
        if (n < 3) {
            return false;
        }
        int maxIndex = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (A[i] > max) {
                maxIndex = i;
                max = A[i];
            }
        }
        if (maxIndex == n - 1 || maxIndex == 0) {
            return false;
        }
        for (int i = maxIndex; i > 0; i--) {
            if (A[i - 1] >= A[i]) {
                return false;
            }
        }
        for (int i = maxIndex; i < n - 1; i++) {
            if (A[i + 1] >= A[i]) {
                return false;
            }
        }
        return true;
    }
}
