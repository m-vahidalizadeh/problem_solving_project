package leetcode.medium;

import java.util.Stack;

/**
 * Design Browser History
 * You have a browser of one tab where you start on the homepage and you can visit another url, get back in the history number of steps or move forward in the history number of steps.
 * <p>
 * Implement the BrowserHistory class:
 * <p>
 * BrowserHistory(string homepage) Initializes the object with the homepage of the browser.
 * void visit(string url) visits url from the current page. It clears up all the forward history.
 * string back(int steps) Move steps back in history. If you can only return x steps in the history and steps > x, you will return only x steps. Return the current url after moving back in history at most steps.
 * string forward(int steps) Move steps forward in history. If you can only forward x steps in the history and steps > x, you will forward only x steps. Return the current url after forwarding in history at most steps.
 * <p>
 * Example:
 * <p>
 * Input:
 * ["BrowserHistory","visit","visit","visit","back","back","forward","visit","forward","back","back"]
 * [["leetcode.com"],["google.com"],["facebook.com"],["youtube.com"],[1],[1],[1],["linkedin.com"],[2],[2],[7]]
 * Output:
 * [null,null,null,null,"facebook.com","google.com","facebook.com",null,"linkedin.com","google.com","leetcode.com"]
 * <p>
 * Explanation:
 * BrowserHistory browserHistory = new BrowserHistory("leetcode.com");
 * browserHistory.visit("google.com");       // You are in "leetcode.com". Visit "google.com"
 * browserHistory.visit("facebook.com");     // You are in "google.com". Visit "facebook.com"
 * browserHistory.visit("youtube.com");      // You are in "facebook.com". Visit "youtube.com"
 * browserHistory.back(1);                   // You are in "youtube.com", move back to "facebook.com" return "facebook.com"
 * browserHistory.back(1);                   // You are in "facebook.com", move back to "google.com" return "google.com"
 * browserHistory.forward(1);                // You are in "google.com", move forward to "facebook.com" return "facebook.com"
 * browserHistory.visit("linkedin.com");     // You are in "facebook.com". Visit "linkedin.com"
 * browserHistory.forward(2);                // You are in "linkedin.com", you cannot move forward any steps.
 * browserHistory.back(2);                   // You are in "linkedin.com", move back two steps to "facebook.com" then to "google.com". return "google.com"
 * browserHistory.back(7);                   // You are in "google.com", you can move back only one step to "leetcode.com". return "leetcode.com"
 * <p>
 * Constraints:
 * <p>
 * 1 <= homepage.length <= 20
 * 1 <= url.length <= 20
 * 1 <= steps <= 100
 * homepage and url consist of  '.' or lower case English letters.
 * At most 5000 calls will be made to visit, back, and forward.
 */
public class BrowserHistory {

    Stack<String> s1;
    Stack<String> s2;
    String homepage;

    public BrowserHistory(String homepage) {
        s1 = new Stack<>();
        s2 = new Stack<>();
        this.homepage = homepage;
        s1.push(homepage);
    }

    public void visit(String url) {
        s2 = new Stack<>();
        s1.push(url);
    }

    public String back(int steps) {
        String curr = s1.peek();
        while (!homepage.equals(s1.peek()) && steps >= 1) {
            s2.push(s1.pop());
            steps--;
        }
        if (homepage.equals(s1.peek())) return homepage;
        return steps > 1 ? curr : s1.peek();
    }

    public String forward(int steps) {
        while (!s2.isEmpty() && steps >= 1) {
            s1.push(s2.pop());
            steps--;
        }
        return steps > 1 ? s1.peek() : s1.peek();
    }

    public static void main(String[] args) {
        /*
Input:
["BrowserHistory","visit","visit","visit","back","back","forward","visit","forward","back","back"]
[["leetcode.com"],["google.com"],["facebook.com"],["youtube.com"],[1],[1],[1],["linkedin.com"],[2],[2],[7]]
Output:
[null,null,null,null,"facebook.com","google.com","facebook.com",null,"linkedin.com","google.com","leetcode.com"]
         */
        BrowserHistory b1 = new BrowserHistory("leetcode.com");
        b1.visit("google.com");
        b1.visit("facebook.com");
        b1.visit("youtube.com");
        System.out.println(b1.back(1));
        System.out.println(b1.back(1));
        System.out.println(b1.forward(1));
        b1.visit("linkedin.com");
        System.out.println(b1.forward(2));
        System.out.println(b1.back(2));
        System.out.println(b1.back(7));
        /*
Input:
["BrowserHistory","visit","back","back","forward","forward","visit","visit","back"]
[["zav.com"],["kni.com"],[7],[7],[5],[1],["pwrrbnw.com"],["mosohif.com"],[9]]
Output:
[null,null,"zav.com","zav.com","kni.com","kni.com",null,null,"zav.com"]
         */
        BrowserHistory b2 = new BrowserHistory("zav.com");
        b2.visit("kni.com");
        System.out.println(b2.back(7));
        System.out.println(b2.back(7));
        System.out.println(b2.forward(5));
        System.out.println(b2.forward(1));
        b2.visit("pwrrbnw.com");
        b2.visit("mosohif.com");
        System.out.println(b2.back(9));

    }

}
