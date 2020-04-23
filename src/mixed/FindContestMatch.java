package mixed;

import java.util.*;

public class FindContestMatch {

    public static void main(String[] args) {
        FindContestMatch findContestMatch = new FindContestMatch();
        System.out.println(findContestMatch.findContestMatch(16));
    }

    public String findContestMatch(int n) {
        if (n == 2)
            return mix(1, 2);
        if (n == 4)
            return mix(mix(1, 4), mix(2, 3));
        int k = n / 2;
        Stack<String> mainStack = new Stack<>();
        Queue<String> mainQueue = new LinkedList<>();
        // Initial round
        for (int i = 1; i <= k; i++) {
            String currentPair = mix(i, (n - i + 1));
            mainQueue.add(currentPair);
            mainStack.push(currentPair);
        }
        k = k / 2;
        while (k > 1) {
            // Other rounds
            Stack<String> tempS = new Stack<>();
            Queue<String> tempQ = new LinkedList<>();
            for (int i = 1; i <= k; i++) {
                String currentPair = mix(mainQueue.poll(), mainStack.pop());
                tempQ.add(currentPair);
                tempS.add(currentPair);
            }
            mainQueue = tempQ;
            mainStack = tempS;
            k = k / 2;
        }
        // Last round// Last round
        return mix(mainQueue.poll(), mainStack.pop());
    }

    private String mix(int i1, int i2) {
        return mix(String.valueOf(i1), String.valueOf(i2));
    }

    private String mix(String s1, String s2) {
        return "(" + s1 + "," + s2 + ")";
    }

}
