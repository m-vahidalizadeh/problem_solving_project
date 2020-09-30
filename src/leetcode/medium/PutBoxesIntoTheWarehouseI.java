package leetcode.medium;

import java.util.Arrays;

/**
 * Put Boxes Into the Warehouse I
 * You are given two arrays of positive integers, boxes and warehouse, representing the heights of some boxes of unit width and the heights of n rooms in a warehouse respectively. The warehouse's rooms are labelled from 0 to n - 1 from left to right where warehouse[i] (0-indexed) is the height of the ith room.
 * <p>
 * Boxes are put into the warehouse by the following rules:
 * <p>
 * Boxes cannot be stacked.
 * You can rearrange the insertion order of the boxes.
 * Boxes can only be pushed into the warehouse from left to right only.
 * If the height of some room in the warehouse is less than the height of a box, then that box and all other boxes behind it will be stopped before that room.
 * Return the maximum number of boxes you can put into the warehouse.
 * <p>
 * Example 1:
 * <p>
 * Input: boxes = [4,3,4,1], warehouse = [5,3,3,4,1]
 * Output: 3
 * Explanation:
 * <p>
 * We can first put the box of height 1 in room 4. Then we can put the box of height 3 in either of the 3 rooms 1, 2, or 3. Lastly, we can put one box of height 4 in room 0.
 * There is no way we can fit all 4 boxes in the warehouse.
 * Example 2:
 * <p>
 * Input: boxes = [1,2,2,3,4], warehouse = [3,4,1,2]
 * Output: 3
 * Explanation:
 * <p>
 * Notice that it's not possible to put the box of height 4 into the warehouse since it cannot pass the first room of height 3.
 * Also, for the last two rooms, 2 and 3, only boxes of height 1 can fit.
 * We can fit 3 boxes maximum as shown above. The yellow box can also be put in room 2 instead.
 * Swapping the orange and green boxes is also valid, or swapping one of them with the red box.
 * Example 3:
 * <p>
 * Input: boxes = [1,2,3], warehouse = [1,2,3,4]
 * Output: 1
 * Explanation: Since the first room in the warehouse is of height 1, we can only put boxes of height 1.
 * Example 4:
 * <p>
 * Input: boxes = [4,5,6], warehouse = [3,3,3,3,3]
 * Output: 0
 * <p>
 * Constraints:
 * <p>
 * n == warehouse.length
 * 1 <= boxes.length, warehouse.length <= 10^5
 * 1 <= boxes[i], warehouse[i] <= 10^9
 */
public class PutBoxesIntoTheWarehouseI {

    public int maxBoxesInWarehouse(int[] boxes, int[] warehouse) {
        int boxN = boxes.length;
        int warehouseN = warehouse.length;
        Arrays.sort(boxes);
        int count = 0;
        int[] lMinWarehouse = new int[warehouseN];
        lMinWarehouse[0] = warehouse[0];
        for (int i = 1; i < warehouseN; i++) lMinWarehouse[i] = Math.min(lMinWarehouse[i - 1], warehouse[i]);
        int boxIndex = 0;
        int warehouseIndex = warehouseN - 1;
        while (boxIndex < boxN && warehouseIndex >= 0) {
            if (boxes[boxIndex] <= lMinWarehouse[warehouseIndex--]) {
                boxIndex++;
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        PutBoxesIntoTheWarehouseI p = new PutBoxesIntoTheWarehouseI();
        System.out.println(p.maxBoxesInWarehouse(new int[]{4, 3, 4, 1}, new int[]{5, 3, 3, 4, 1}));
        System.out.println(p.maxBoxesInWarehouse(new int[]{1, 2, 2, 3, 4}, new int[]{3, 4, 1, 2}));
        System.out.println(p.maxBoxesInWarehouse(new int[]{1, 2, 3}, new int[]{1, 2, 3, 4}));
        System.out.println(p.maxBoxesInWarehouse(new int[]{4, 5, 6}, new int[]{3, 3, 3, 3, 3}));
    }

}
