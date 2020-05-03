package hackerrank;

import java.util.Scanner;

public class XORStrings {

    public static String stringsXOR(String s, String t) {
        String res = new String("");
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == t.charAt(i))
                res += "0";
            else
                res += "1";
        }

        return res;
    }

    public static void main(String[] args) {
        try (
                Scanner scanner = new Scanner(System.in)
        ) {
            String s, t;
            s = scanner.nextLine();
            t = scanner.nextLine();
            System.out.println(stringsXOR(s, t));
        }
    }

}
