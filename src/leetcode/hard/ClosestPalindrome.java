package leetcode.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * 564. Find the Closest Palindrome
 * Given a string n representing an integer, return the closest integer (not including itself), which is a palindrome. If there is a tie, return the smaller one.
 *
 * The closest is defined as the absolute difference minimized between two integers.
 *
 * Example 1:
 *
 * Input: n = "123"
 * Output: "121"
 * Example 2:
 *
 * Input: n = "1"
 * Output: "0"
 * Explanation: 0 and 2 are the closest palindromes but we return the smallest which is 0.
 *
 * Constraints:
 *
 * 1 <= n.length <= 18
 * n consists of only digits.
 * n does not have leading zeros.
 * n is representing an integer in the range [1, 1018 - 1].
 */
public class ClosestPalindrome {

    public String nearestPalindromic(String n) {
        Num originalNum = new Num(n);
        int len = n.length();
        List<Num> candidates = new ArrayList<>();
        int k = (len + 1) / 2; // select the left half- if odd, we select (len/2)+1 as left and (len/2) as right, if even left=right=(len/2)
        boolean isOriginalEvenSized = len % 2 == 0;
        Num originalLeft = new Num(n.substring(0, k));
        if (len == 1) candidates.add(new Num(0)); // Consider 0 in case of 1
        for (int i = -1; i <= 1; i++) { // try to change the mid-digit by +1,0,-1, then make it palindrome
            Num newCandidate = new Num(originalLeft.num + i, isOriginalEvenSized);
            if (newCandidate.numStr.length() == len)
                candidates.add(newCandidate); // for other lengths, later we will consider 10...01 (size=len+1) and 9...9 (size=len-1)
        }
        StringBuilder sb;
        if (len > 1) { // build a candidate with length=len-1-> 9...9
            sb = new StringBuilder();
            for (int i = 0; i < len - 1; i++) sb.append('9');
            candidates.add(new Num(sb.toString()));
        }
        if (len >= 1) { // build a candidate with len=len+1-> 10...01
            sb = new StringBuilder();
            sb.append('1');
            for (int i = 0; i < len - 1; i++) sb.append('0');
            sb.append('1');
            candidates.add(new Num(sb.toString()));
        }
        long minDiff = Long.MAX_VALUE;
        Num min = new Num(-1);
        for (int i = 0; i < candidates.size(); i++) { // now see which one of the candidates is closer to the original number
            Num candidate = candidates.get(i);
            if (originalNum.num == candidate.num) continue; // it should not be the original number
            long diff = Math.abs(candidate.num - originalNum.num);
            if ((diff < minDiff) || (diff == minDiff && candidate.num < min.num)) { // pick closer one, if there is a tie, pick smaller one
                minDiff = diff;
                min = candidate;
            }
        }
        return min.numStr;
    }

    public class Num { // data structure that keeps a long number in both String and long format
        String numStr;
        long num;

        public Num(String numStr) {
            this.numStr = numStr;
            this.num = Long.parseLong(numStr);
        }

        public Num(long num) {
            this.numStr = String.valueOf(num);
            this.num = num;
        }

        public Num(long leftHalf, boolean isEven) { // enables us to build left_half+reverse(left_half)-> palindrome
            String leftHalfStr = String.valueOf(leftHalf);
            StringBuilder sb = new StringBuilder();
            sb.append(leftHalfStr);
            /*
            If the len of original number is odd, don't repeat the mid-element->left-half+reverse(left-half.sub(0,left-half.size-1))
            ex. 1 original 12345 -> left-half=123 -> right=reverse(12)=21-> output=12321
            ex. 2 original 1234 -> left-half=12 -> right=reverse(12)=21-> output=1221
             */
            String right = new StringBuilder(isEven ? leftHalfStr : leftHalfStr.substring(0, leftHalfStr.length() - 1)).reverse().toString();
            sb.append(right);
            this.numStr = sb.toString();
            this.num = Long.parseLong(this.numStr);
        }
    }

}
