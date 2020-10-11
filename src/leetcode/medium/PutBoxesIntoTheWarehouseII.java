package leetcode.medium;

import java.util.Arrays;

/**
 * 1580. Put Boxes Into the Warehouse II
 * You are given two arrays of positive integers, boxes and warehouse, representing the heights of some boxes of unit width and the heights of n rooms in a warehouse respectively. The warehouse's rooms are labeled from 0 to n - 1 from left to right where warehouse[i] (0-indexed) is the height of the ith room.
 * <p>
 * Boxes are put into the warehouse by the following rules:
 * <p>
 * Boxes cannot be stacked.
 * You can rearrange the insertion order of the boxes.
 * Boxes can be pushed into the warehouse from either side (left or right)
 * If the height of some room in the warehouse is less than the height of a box, then that box and all other boxes behind it will be stopped before that room.
 * Return the maximum number of boxes you can put into the warehouse.
 * <p>
 * Example 1:
 * <p>
 * Input: boxes = [1,2,2,3,4], warehouse = [3,4,1,2]
 * Output: 4
 * Explanation:
 * <p>
 * We can store the boxes in the following order:
 * 1- Put the yellow box in room 2 from either the left or right side.
 * 2- Put the orange box in room 3 from the right side.
 * 3- Put the green box in room 1 from the left side.
 * 4- Put the red box in room 0 from the left side.
 * Notice that there are other valid ways to put 4 boxes such as swapping the red and green boxes or the red and orange boxes.
 * Example 2:
 * <p>
 * Input: boxes = [3,5,5,2], warehouse = [2,1,3,4,5]
 * Output: 3
 * Explanation:
 * <p>
 * It's not possible to put the two boxes of height 5 in the warehouse since there's only 1 room of height >= 5.
 * Other valid solutions are to put the green box in room 2 or to put the orange box first in room 2 before putting the green and red boxes.
 * Example 3:
 * <p>
 * Input: boxes = [1,2,3], warehouse = [1,2,3,4]
 * Output: 3
 * Example 4:
 * <p>
 * Input: boxes = [4,5,6], warehouse = [3,3,3,3,3]
 * Output: 0
 * <p>
 * Constraints:
 * <p>
 * n == warehouse.length
 * 1 <= boxes.length, warehouse.length <= 105
 * 1 <= boxes[i], warehouse[i] <= 109
 */
public class PutBoxesIntoTheWarehouseII {

    public int maxBoxesInWarehouse(int[] boxes, int[] warehouse) {
        int l = 0;
        int r = warehouse.length - 1;
        int counter = 0;
        int i = boxes.length - 1;
        Arrays.sort(boxes);
        while (i >= 0 && l <= r) { // There are boxes left and the pointers didn't pass each other.
            if (boxes[i] <= warehouse[l]) { // Let's see if we can fit from the left side.
                counter++; // We could fit it from left. One more box.
                l++; // The room in the warehouse is full now.
            } else if (boxes[i] <= warehouse[r]) { // Let's see if we can fit from right side.
                counter++; // We could fit it from right. One more box.
                r--; // The room in the warehouse is full now.
            } // If we can't fit the curr box, skip it and don't inc the counter. Go to the smaller box now.
            i--; // Go to the next smaller box. The counter was incremented if we could fit the curr box.
        }
        return counter;
    }

}
