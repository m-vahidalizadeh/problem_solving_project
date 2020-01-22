public class GOGuessNumber {

    static int paid = 0;
    static int target = -2;

    /* The guess API is defined in the parent class GuessGame.
   @param num, your guess
   @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
      int guess(int num); */

    public static void main(String[] args) {
    /*
    Input: n = 10, pick = 6
    Output: 6
     */
//        System.out.println(guessNumber(1000));
        System.out.println(getMoneyAmount(2));
    }

    public static int getMoneyAmount(int n) {
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            target = i;
            paid = 0;
            guessNumber(n);
            if (paid > max) {
                max = paid;
            }
        }
        return max;
    }

    public static int guessNumber(int n) {
        return bSearch(1, n);
    }

    public static int bSearch(int begin, int end) {
        if (begin == end) {
            return begin;
        }
        int pick1 = begin + ((end - begin) / 2) - 2;
        int pick2 = begin + ((end - begin) / 2) - 1;
        int pick3 = begin + ((end - begin) / 2);
        int pick4 = begin + ((end - begin) / 2) + 1;
        int pick5 = begin + ((end - begin) / 2) + 2;

        int result1 = -2;
        int result2 = -2;
        int result3 = -2;
        int result4 = -2;
        int result5 = -2;

        if (begin <= pick1 && pick1 <= end) {
            result1 = guess(pick1);
        }
        if (begin <= pick2 && pick2 <= end) {
            result2 = guess(pick2);
        }
        if (begin <= pick3 && pick3 <= end) {
            result3 = guess(pick3);
        }
        if (begin <= pick4 && pick4 <= end) {
            result4 = guess(pick4);
        }
        if (begin <= pick5 && pick5 <= end) {
            result5 = guess(pick5);
        }

        int pick = -2;
        if (result3 != -2 && result3 != 0) {
            pick = pick3;
        } else if (result2 != -2 && result2 != 0) {
            pick = pick2;
        } else if (result4 != -2 && result4 != 0) {
            pick = pick4;
        } else if (result1 != -2 && result1 != 0) {
            pick = pick1;
        } else if (result5 != -2 && result5 != 0) {
            pick = pick5;
        }

        int result = guess(pick);

        if (result == -1) {
            paid += pick;
            if (pick - 1 == begin) {
                return begin;
            }
            return bSearch(begin, pick - 1);
        } else if (result == 0) {
            return pick;
        } else {
            paid += pick;
            if (pick + 1 == end) {
                return end;
            }
            return bSearch(pick + 1, end);
        }
    }

    public static int guess(int input) {
        if (input < target) {
            return 1;
        } else if (input == target) {
            return 0;
        } else {
            return -1;
        }
    }

}
