package leetcode.companies.google;

import java.util.Set;
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
public class SitRoom {

    Set<Integer> sortedSet;
    int n;

    public SitRoom(int n) {
        this.n = n;
        sortedSet = new TreeSet<>();
    }

    public int seat() {
        int seatId = findSeat(sortedSet);
        sortedSet.add(seatId);
        return seatId;
    }

    public void leave(int p) {
        sortedSet.remove(p);
    }

    private int findSeat(Set<Integer> sortedSet) {
        if (sortedSet.isEmpty()) { // If no one in the room, sit in seat 0.
            return 0;
        }
        int maxDiff = -1;
        int seadId = -1;
        int i = 0;
        int firstElement = -1;
        int lastElement = -1;
        int prev = -1;
        for (int item : sortedSet) {
            if (i == 0) firstElement = item;
            if (i == sortedSet.size() - 1) lastElement = item;
            if (i >= 1) {
                int currDiff = (item - prev) / 2;
                if (maxDiff < currDiff) {
                    maxDiff = currDiff;
                    seadId = (item + prev) / 2;
                }
            }
            prev = item;
            i++;
        }
        if (maxDiff <= firstElement) {
            maxDiff = firstElement;
            seadId = 0;
        }
        if (maxDiff < n - 1 - lastElement) {
            seadId = n - 1;
        }
        return seadId;
    }

}
