package fb;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static base.Utils.printArray;

/**
 * Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.
 * <p>
 * Return the intersection of these two interval lists.
 * <p>
 * (Formally, a closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.  The intersection of two closed intervals is a set of real numbers that is either empty, or can be represented as a closed interval.  For example, the intersection of [1, 3] and [2, 4] is [2, 3].)
 * <p>
 * Example 1:
 * Input: A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
 * Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
 * Reminder: The inputs and the desired output are lists of Interval objects, and not arrays or lists.
 * <p>
 * Note:
 * <p>
 * 0 <= A.length < 1000
 * 0 <= B.length < 1000
 * 0 <= A[i].start, A[i].end, B[i].start, B[i].end < 10^9
 * NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
 */
public class IntervalsIntersections {

    class Interval {
        int start;
        int finish;

        Interval(int start, int finish) {
            this.start = start;
            this.finish = finish;
        }
    }

    public int[][] intervalIntersection(int[][] A, int[][] B) {
        int AI = 0;
        int BI = 0;
        int AN = A.length;
        int BN = B.length;
        List<Interval> resultList = new ArrayList<>();
        while (AI < AN && BI < BN) {
            int AIS = A[AI][0];
            int AIF = A[AI][1];
            int BIS = B[BI][0];
            int BIF = B[BI][1];
            if (AIS > BIF) {
                BI++;
            } else if (BIS > AIF) {
                AI++;
            } else if (AIS == BIS) {
                if (AIF < BIF) {
                    resultList.add(new Interval(AIS, AIF));
                    AI++;
                } else if (AIF > BIF) {
                    resultList.add(new Interval(BIS, BIF));
                    BI++;
                } else {
                    resultList.add(new Interval(BIS, BIF));
                    BI++;
                    AI++;
                }
            } else if (AIS < BIS && BIS <= AIF) {
                if (AIF == BIF) {
                    AI++;
                    BI++;
                    resultList.add(new Interval(BIS, BIF));
                } else if (AIF < BIF) {
                    AI++;
                    resultList.add(new Interval(BIS, AIF));
                } else {
                    BI++;
                    resultList.add(new Interval(BIS, BIF));
                }
            } else if (AIS > BIS && AIS <= BIF) {
                if (AIF == BIF) {
                    AI++;
                    BI++;
                    resultList.add(new Interval(AIS, BIF));
                } else if (AIF < BIF) {
                    AI++;
                    resultList.add(new Interval(AIS, AIF));
                } else {
                    BI++;
                    resultList.add(new Interval(AIS, BIF));
                }
            } else {
                AI++;
                BI++;
            }
        }
        int n = resultList.size();
        int[][] result = new int[n][2];
        Iterator<Interval> it = resultList.iterator();
        int index = 0;
        while (it.hasNext()) {
            Interval interval = it.next();
            result[index][0] = interval.start;
            result[index][1] = interval.finish;
            index++;
        }
        return result;
    }

    public static void main(String[] args) {
        IntervalsIntersections i = new IntervalsIntersections();
//        int[][] A = {{0, 2}, {5, 10}, {13, 23}, {24, 25}};
//        int[][] B = {{1, 5}, {8, 12}, {15, 24}, {25, 26}};
//        printArray(i.intervalIntersection(A, B));
//        int[][] A1 = {{5, 10}};
//        int[][] B1 = {{5, 6}};
//        printArray(i.intervalIntersection(A1, B1));
        int[][] A = {{3, 5}, {9, 20}};
        int[][] B = {{4, 5}, {7, 10}, {11, 12}, {14, 15}, {16, 20}};
        printArray(i.intervalIntersection(A, B));
    }

}
