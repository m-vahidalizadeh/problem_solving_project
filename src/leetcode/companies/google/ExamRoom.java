package leetcode.companies.google;

import java.util.TreeSet;

/**
 * 855. Exam Room
 * There is an exam room with n seats in a single row labeled from 0 to n - 1.
 *
 * When a student enters the room, they must sit in the seat that maximizes the distance to the closest person. If there are multiple such seats, they sit in the seat with the lowest number. If no one is in the room, then the student sits at seat number 0.
 *
 * Design a class that simulates the mentioned exam room.
 *
 * Implement the ExamRoom class:
 *
 * ExamRoom(int n) Initializes the object of the exam room with the number of the seats n.
 * int seat() Returns the label of the seat at which the next student will set.
 * void leave(int p) Indicates that the student sitting at seat p will leave the room. It is guaranteed that there will be a student sitting at seat p.
 *
 * Example 1:
 *
 * Input
 * ["ExamRoom", "seat", "seat", "seat", "seat", "leave", "seat"]
 * [[10], [], [], [], [], [4], []]
 * Output
 * [null, 0, 9, 4, 2, null, 5]
 *
 * Explanation
 * ExamRoom examRoom = new ExamRoom(10);
 * examRoom.seat(); // return 0, no one is in the room, then the student sits at seat number 0.
 * examRoom.seat(); // return 9, the student sits at the last seat number 9.
 * examRoom.seat(); // return 4, the student sits at the last seat number 4.
 * examRoom.seat(); // return 2, the student sits at the last seat number 2.
 * examRoom.leave(4);
 * examRoom.seat(); // return 5, the student sits at the last seat number 5.
 *
 * Constraints:
 *
 * 1 <= n <= 109
 * It is guaranteed that there is a student sitting at seat p.
 * At most 104 calls will be made to seat and leave.
 */
public class ExamRoom {

    TreeSet<Integer> sortedFilled;
    int n;

    public ExamRoom(int n) {
        this.sortedFilled = new TreeSet<>(); // A sorted set
        this.n = n;
    }

    public int seat() {
        if (sortedFilled.isEmpty()) { // For the first seat, we return 0
            sortedFilled.add(0);
            return 0;
        }
        int maxDist = sortedFilled.first(); // First one is [0,sortedFilled.first] or sortedFilled.first-0
        int seatId = 0;
        int pre = maxDist;
        for (int curr : sortedFilled) {
            int currDist = (curr - pre) / 2;
            if (maxDist < currDist) { // We want the max distance
                maxDist = currDist;
                seatId = (curr + pre) / 2; // The seat is in the middle of curr and pre
            }
            pre = curr; // curr is pre for the next round
        }
        if (n - 1 - pre > maxDist) // Here pre is the last item. We are checking to see if we should select seat n-1 or not.
            seatId = n - 1;
        sortedFilled.add(seatId); // Mark the seat as filled
        return seatId;
    }

    public void leave(int p) {
        sortedFilled.remove(p); // Just remove the sead ID: p from the sorted filled set.
    }

}
