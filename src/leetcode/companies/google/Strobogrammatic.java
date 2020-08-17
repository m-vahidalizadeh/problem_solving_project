package leetcode.companies.google;

public class Strobogrammatic {

    public static void main(String[] args) {
        /*
Example 1:
Input:  "69"
Output: true

Example 2:
Input:  "88"
Output: true

Example 3:
Input:  "962"
Output: false
         */
        Strobogrammatic strobogrammatic = new Strobogrammatic();
        System.out.println(strobogrammatic.isStrobogrammatic("69"));
        System.out.println(strobogrammatic.isStrobogrammatic("88"));
        System.out.println(strobogrammatic.isStrobogrammatic("962"));
    }

    public boolean isStrobogrammatic(String num) {
        char[] numChars = num.toCharArray();
        int n = numChars.length;
        if (n % 2 != 0) {
            // Even
            for (int i = 0; i < n / 2; i++) {
                boolean roundPass = false;
                int a = Character.getNumericValue(numChars[i]);
                int b = Character.getNumericValue(numChars[n - 1 - i]);
                if (a == b && (a == 0 || a == 1 || a == 8)) {
                    roundPass = true;
                } else if ((a == 6 && b == 9) || (a == 9 && b == 6)) {
                    roundPass = true;
                }
                if (!roundPass) {
                    return false;
                }
            }
            // Check n/2
            int eNDivTwo = Character.getNumericValue(numChars[n / 2]);
            if (eNDivTwo == 0 || eNDivTwo == 1 || eNDivTwo == 8) {
                return true;
            } else {
                return false;
            }
        } else {
            // Odd
            for (int i = 0; i < n / 2; i++) {
                boolean roundPass = false;
                int a = Character.getNumericValue(numChars[i]);
                int b = Character.getNumericValue(numChars[n - 1 - i]);
                if (a == b && (a == 0 || a == 1 || a == 8)) {
                    roundPass = true;
                } else if ((a == 6 && b == 9) || (a == 9 && b == 6)) {
                    roundPass = true;
                }
                if (!roundPass) {
                    return false;
                }
            }
            return true;
        }
    }

}
