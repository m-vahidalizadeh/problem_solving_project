package leetcode.companies.uber;

public class UBERIndexEqualsValue {

    public static void main(String[] args) {
        /*
Example 1:
Input: [-10,-5,0,3,7]
Output: 3
Explanation:
For the given array, A[0] = -10, A[1] = -5, A[2] = 0, A[3] = 3, thus the output is 3.

Example 2:
Input: [0,2,5,8,17]
Output: 0
Explanation:
A[0] = 0, thus the output is 0.

Example 3:
Input: [-10,-5,3,4,7,9]
Output: -1
Explanation:
There is no such i that A[i] = i, thus the output is -1.
         */
//        int[] A1 = new int[]{-10, -5, 0, 3, 7};
//        int[] A2 = new int[]{0, 2, 5, 8, 17};
//        int[] A3 = new int[]{-10, -5, 3, 4, 7, 9};
        int[] A4 = new int[]{-10, -5, -2, 0, 4, 5, 6, 7, 8, 9, 10};
        UBERIndexEqualsValue uberIndexEqualsValue = new UBERIndexEqualsValue();
//        System.out.println(uberIndexEqualsValue.fixedPoint(A1));
//        System.out.println(uberIndexEqualsValue.fixedPoint(A2));
//        System.out.println(uberIndexEqualsValue.fixedPoint(A3));
        System.out.println(uberIndexEqualsValue.fixedPoint(A4));
    }

    public int fixedPoint(int[] A) {
        return binarySearch(0, A.length - 1, A);
    }

    private int binarySearch(int start, int end, int[] A) {
        if (start == end) {
            if (A[start] == start) {
                return start;
            } else {
                return -1;
            }
        }
        int mid = (start + end) / 2;
        int left = -1;
        int right = -1;
        if (mid <= A[mid] && mid - 1 >= start) {
            left = binarySearch(start, mid - 1, A);
        } else if (mid > A[mid] && mid + 1 <= end) {
            right = binarySearch(mid + 1, end, A);
        }
        if (left == -1 && mid == A[mid]) {
            return mid;
        }
        if (left != -1) {
            return left;
        }
        return right;
    }

}
