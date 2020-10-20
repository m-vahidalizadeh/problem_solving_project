package leetcode.companies.amazon;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 937. Reorder Data in Log Files
 * You have an array of logs.  Each log is a space delimited string of words.
 * <p>
 * For each log, the first word in each log is an alphanumeric identifier.  Then, either:
 * <p>
 * Each word after the identifier will consist only of lowercase letters, or;
 * Each word after the identifier will consist only of digits.
 * We will call these two varieties of logs letter-logs and digit-logs.  It is guaranteed that each log has at least one word after its identifier.
 * <p>
 * Reorder the logs so that all of the letter-logs come before any digit-log.  The letter-logs are ordered lexicographically ignoring identifier, with the identifier used in case of ties.  The digit-logs should be put in their original order.
 * <p>
 * Return the final order of the logs.
 * <p>
 * Example 1:
 * Input: logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]
 * Output: ["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"]
 * <p>
 * Constraints:
 * <p>
 * 0 <= logs.length <= 100
 * 3 <= logs[i].length <= 100
 * logs[i] is guaranteed to have an identifier, and a word after the identifier.
 */
public class ReorderLogFiles {

    public class Log {
        String info;
        String key;
        String val;
        boolean isDigitLog;

        public Log(String info) {
            this.info = info;
            int index = info.indexOf(' ');
            this.key = info.substring(0, index);
            this.val = info.substring(index + 1);
            this.isDigitLog = Character.isDigit(this.val.charAt(0));
        }
    }

    public String[] reorderLogFiles(String[] logs) {
        List<Log> numberLogs = new ArrayList<>();
        PriorityQueue<Log> letterLogs = new PriorityQueue<>((a, b) -> a.val.compareTo(b.val) == 0 ? a.key.compareTo(b.key) : a.val.compareTo(b.val));
        for (String info : logs) {
            Log log = new Log(info);
            if (log.isDigitLog) numberLogs.add(log);
            else letterLogs.add(log);
        }
        String[] result = new String[numberLogs.size() + letterLogs.size()];
        int i = 0;
        while (!letterLogs.isEmpty()) result[i++] = letterLogs.poll().info;
        for (Log log : numberLogs) result[i++] = log.info;
        return result;
    }

}
