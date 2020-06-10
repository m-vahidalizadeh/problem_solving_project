package leetcode.medium;

import java.util.*;

/**
 * Queue Reconstruction by Height
 * Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k), where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h. Write an algorithm to reconstruct the queue.
 * <p>
 * Note:
 * The number of people is less than 1,100.
 * <p>
 * Example
 * <p>
 * Input:
 * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 * <p>
 * Output:
 * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 */
public class QueueReconstructionByHeight {

    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (a, b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);
        List<int[]> rList = new LinkedList<>();
        for (int[] p : people) rList.add(p[1], p);
        return rList.toArray(new int[people.length][2]);
    }

    public static void main(String[] args) {
        QueueReconstructionByHeight q = new QueueReconstructionByHeight();
        int[][] people = {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
        printPeople(q.reconstructQueue(people));
    }

    private static void printPeople(int[][] people){
        for(int[] p:people) System.out.print("["+p[0]+","+p[1]+"] ");
    }

}
