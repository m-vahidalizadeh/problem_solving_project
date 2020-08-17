package leetcode.companies.google;

import java.util.HashSet;
import java.util.Set;

/**
 * Next Closest Time
 * <p>
 * Given a time represented in the format "HH:MM", form the next closest time by reusing the current digits. There is no limit on how many times a digit can be reused.
 * <p>
 * You may assume the given input string is always valid. For example, "01:34", "12:09" are all valid. "1:34", "12:9" are all invalid.
 * <p>
 * Example 1:
 * <p>
 * Input: "19:34"
 * Output: "19:39"
 * Explanation: The next closest time choosing from digits 1, 9, 3, 4, is 19:39, which occurs 5 minutes later.  It is not 19:33, because this occurs 23 hours and 59 minutes later.
 * Example 2:
 * <p>
 * Input: "23:59"
 * Output: "22:22"
 * Explanation: The next closest time choosing from digits 2, 3, 5, 9, is 22:22. It may be assumed that the returned time is next day's time since it is smaller than the input time numerically.
 */
public class NextClosestTime {

    public String nextClosestTime(String time) {
        int totalMins = Integer.parseInt(time.substring(0, 2)) * 60
                + Integer.parseInt(time.substring(3));
        Set<Integer> validDigits = new HashSet<>();
        for (char c : time.toCharArray()) validDigits.add(c - '0');
        while (true) {
            totalMins = (totalMins + 1) % (24 * 60);
            java.util.Optional<String> nextTime = getNextTime(totalMins, validDigits);
            if (nextTime.isPresent()) return nextTime.get();
        }
    }

    private java.util.Optional<String> getNextTime(int totalMins, Set<Integer> validDigits) {
        int hours = totalMins / 60;
        int mins = totalMins % 60;
        int[] nextTime = {hours / 10, hours % 10, mins / 10, mins % 10};
        for (int digit : nextTime) if (!validDigits.contains(digit)) return java.util.Optional.empty();
        return java.util.Optional.of(String.format("%02d:%02d", hours, mins));
    }

}
