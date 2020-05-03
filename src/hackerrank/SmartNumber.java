package hackerrank;

import java.util.Scanner;

public class SmartNumber {

    public static boolean isSmartNumber(int num) {
        int val = (int) Math.sqrt(num);
        if (num / (double) val == (double) val || num == 1)
            return true;
        return false;
    }

    public static void main(String[] args) {

        try (
                Scanner in = new Scanner(System.in)
        ) {
            int test_cases;
            test_cases = in.nextInt();
            int num;
            for (int i = 0; i < test_cases; i++) {
                num = in.nextInt();
                boolean ans = isSmartNumber(num);
                if (ans) {
                    System.out.println("YES");
                } else System.out.println("NO");
            }
        }
    }

}
