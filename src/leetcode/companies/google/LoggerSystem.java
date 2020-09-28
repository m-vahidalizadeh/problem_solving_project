package leetcode.companies.google;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Logger Rate Limiter
 * Design a logger system that receive stream of messages along with its timestamps, each message should be printed if and only if it is not printed in the last 10 seconds.
 * <p>
 * Given a message and a timestamp (in seconds granularity), return true if the message should be printed in the given timestamp, otherwise returns false.
 * <p>
 * It is possible that several messages arrive roughly at the same time.
 * <p>
 * Example:
 * <p>
 * Logger logger = new Logger();
 * <p>
 * // logging string "foo" at timestamp 1
 * logger.shouldPrintMessage(1, "foo"); returns true;
 * <p>
 * // logging string "bar" at timestamp 2
 * logger.shouldPrintMessage(2,"bar"); returns true;
 * <p>
 * // logging string "foo" at timestamp 3
 * logger.shouldPrintMessage(3,"foo"); returns false;
 * <p>
 * // logging string "bar" at timestamp 8
 * logger.shouldPrintMessage(8,"bar"); returns false;
 * <p>
 * // logging string "foo" at timestamp 10
 * logger.shouldPrintMessage(10,"foo"); returns false;
 * <p>
 * // logging string "foo" at timestamp 11
 * logger.shouldPrintMessage(11,"foo"); returns true;
 */
public class LoggerSystem extends LinkedHashMap<String, Integer> {

    public LoggerSystem() {
        super(10);
    }

    public boolean shouldPrintMessage(int timestamp, String message) {
        boolean shouldPrint = !super.containsKey(message) ||
                (super.containsKey(message) && timestamp - super.get(message) >= 10);
        if (shouldPrint) super.put(message, timestamp);
        return shouldPrint;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<String, Integer> eldest) {
        return super.size() > 10;
    }

    public static void main(String[] args) {
        LoggerSystem l = new LoggerSystem();
        System.out.println(l.shouldPrintMessage(1, "foo"));
        System.out.println(l.shouldPrintMessage(2, "bar"));
        System.out.println(l.shouldPrintMessage(3, "foo"));
        System.out.println(l.shouldPrintMessage(8, "bar"));
        System.out.println(l.shouldPrintMessage(10, "foo"));
        System.out.println(l.shouldPrintMessage(11, "foo"));
    }

}
