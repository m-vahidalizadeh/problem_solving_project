package hackerrank;

import java.io.*;
import java.util.*;

public class BalancedBrackets {

    static String isBalanced(String s) {
        int n = s.length();
        if (n == 0) {
            return "YES";
        }
        Map<Character, Character> closedBrackets = new HashMap<>();
        closedBrackets.put('(', ')');
        closedBrackets.put('[', ']');
        closedBrackets.put('{', '}');
        Stack<Character> brackets = new Stack<>();
        char[] sChars = s.toCharArray();
        for (int i = 0; i < n; i++) {
            if (closedBrackets.containsKey(sChars[i])) {
                brackets.push(closedBrackets.get(sChars[i]));
            } else {
                if (brackets.isEmpty()) {
                    return "NO";
                }
                if (brackets.peek() == sChars[i]) {
                    brackets.pop();
                } else {
                    return "NO";
                }
            }
        }
        return brackets.isEmpty() ? "YES" : "NO";
    }

    public static void main(String[] args) throws IOException {
        try (
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("iofiles/bb.out")));
                Scanner scanner = new Scanner(System.in)
        ) {
            int t = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int tItr = 0; tItr < t; tItr++) {
                String s = scanner.nextLine();

                String result = isBalanced(s);

                bufferedWriter.write(result);
                bufferedWriter.newLine();
            }
        }
    }

}
