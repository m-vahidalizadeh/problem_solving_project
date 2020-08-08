package leetcode.companies.random;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Reorder Data in Log Files
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
 * <p>
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

    public static class Log implements Comparable<Log> {
        String log;
        String body;
        boolean isAlphabetic;

        public Log(String log) {
            this.log = log;
            this.body = log.substring(log.indexOf(" ") + 1);
            this.isAlphabetic = Character.isAlphabetic(this.body.charAt(0));
        }

        @Override
        public int compareTo(Log o) {
            return body.compareTo(o.body);
        }
    }

    public String[] reorderLogFiles(String[] logs) {
        int n = logs.length;
        String[] reorderedLogs = new String[n];
        int j = n - 1;
        PriorityQueue<Log> pq = new PriorityQueue<>();
        for (int i = n - 1; i >= 0; i--) {
            Log tempLog = new Log(logs[i]);
            if (tempLog.isAlphabetic) {
                pq.add(tempLog);
            } else {
                reorderedLogs[j--] = logs[i];
            }
        }
        j = 0;
        while (!pq.isEmpty()) reorderedLogs[j++] = pq.poll().log;
        return reorderedLogs;
    }

    public static void main(String[] args) {
        ReorderLogFiles r = new ReorderLogFiles();
        String[] logs = {"dig1 8 1 5 1", "let1 art can", "dig2 3 6", "let2 own kit dig", "let3 art zero"};
        System.out.println(Arrays.toString(r.reorderLogFiles(logs)));
    }

}
