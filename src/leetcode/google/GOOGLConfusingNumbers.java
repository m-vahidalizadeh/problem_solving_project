package leetcode.google;

public class GOOGLConfusingNumbers {

    public static void main(String[] args) {
        int N = 8101;
        GOOGLConfusingNumbers googlConfusingNumbers = new GOOGLConfusingNumbers();
        System.out.println(googlConfusingNumbers.confusingNumber(N));
    }

    public boolean confusingNumber(int N) {
        if (N == 0) {
            return false;
        }
        String originalNumber = "";
        int input = N;
        while (input > 0) {
            int remainder = input % 10;
            originalNumber = remainder + originalNumber;
            input = input / 10;
        }
        char[] charDigits = originalNumber.toCharArray();
        int[] digits = new int[charDigits.length];
        int[] rotatedDigits = new int[charDigits.length];
        for (int i = 0; i < charDigits.length; i++) {
            digits[i] = Character.getNumericValue(charDigits[i]);
            if (digits[i] == 2 ||
                    digits[i] == 3 ||
                    digits[i] == 4 ||
                    digits[i] == 5 ||
                    digits[i] == 7) {
                return false;
            }
            if (digits[i] == 6) {
                rotatedDigits[charDigits.length - 1 - i] = 9;
            } else if (digits[i] == 9) {
                rotatedDigits[charDigits.length - 1 - i] = 6;
            } else {
                rotatedDigits[charDigits.length - 1 - i] = digits[i];
            }
        }
        boolean originalEqualsRotated = true;
        for (int i = 0; i < digits.length; i++) {
            if (digits[i] != rotatedDigits[i]) {
                originalEqualsRotated = false;
                break;
            }
        }
        if (originalEqualsRotated) {
            return false;
        }
        return true;
    }

}
