package leetcode.companies.adobe;

/**
 * To Lower Case
 * Implement function ToLowerCase() that has a string parameter str, and returns the same string in lowercase.
 * <p>
 * Example 1:
 * <p>
 * Input: "Hello"
 * Output: "hello"
 * Example 2:
 * <p>
 * Input: "here"
 * Output: "here"
 * Example 3:
 * <p>
 * Input: "LOVELY"
 * Output: "lovely"
 */
public class ToLowercase {

    private final static int upStart = (int) 'A';
    private final static int upEnd = (int) 'Z';
    private final static int diffToLowercase = (int) 'a' - (int) 'A';

    public String toLowerCase(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c >= upStart && c <= upEnd) sb.append((char) ((int) c + diffToLowercase));
            else sb.append(c);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        ToLowercase t = new ToLowercase();
        System.out.println(t.toLowerCase("Hello"));
        System.out.println(t.toLowerCase("here"));
        System.out.println(t.toLowerCase("LOVELY"));
    }

}
