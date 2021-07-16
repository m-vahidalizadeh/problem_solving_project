package leetcode.hard;

import java.util.Arrays;

/**
 * 828. Count Unique Characters of All Substrings of a Given String
 * Let's define a function countUniqueChars(s) that returns the number of unique characters on s.
 *
 * For example if s = "LEETCODE" then "L", "T", "C", "O", "D" are the unique characters since they appear only once in s, therefore countUniqueChars(s) = 5.
 * Given a string s, return the sum of countUniqueChars(t) where t is a substring of s.
 *
 * Notice that some substrings can be repeated so in this case you have to count the repeated ones too.
 *
 * Example 1:
 *
 * Input: s = "ABC"
 * Output: 10
 * Explanation: All possible substrings are: "A","B","C","AB","BC" and "ABC".
 * Evey substring is composed with only unique letters.
 * Sum of lengths of all substring is 1 + 1 + 1 + 2 + 2 + 3 = 10
 * Example 2:
 *
 * Input: s = "ABA"
 * Output: 8
 * Explanation: The same as example 1, except countUniqueChars("ABA") = 1.
 * Example 3:
 *
 * Input: s = "LEETCODE"
 * Output: 92
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * s consists of uppercase English letters only.
 *
 * Solution:
 * The Key of solving this problem in linear time is find the relationship between sum of countUniqueChars() for substrings ending index i and sum of countUniqueChars() for substrings ending index i-1. This article aims to leave leave no room for ambiguity​ of how this algorithm works.
 *
 * For each index i, consider all substrings ending at index i:
 *
 * s[0 : i] , ... , s[i-1 : i]  , s[i : i];
 * Comparing with all substrings ending at index i-1:
 *
 * s[0: i-1], ... , s[i-1, i-1]
 * We know the result for s[i : i] which only contains 1 character:
 *
 * countUniqueChars(s[i: i]) = 1
 * Then we want to find the relationship between:
 *
 * countUniqueChars(s[0: i])    v.s.  countUniqueChars(s[0: i-1])
 * countUniqueChars(s[1: i])    v.s.  countUniqueChars(s[1: i-1])
 * 		  ...                               ...
 * countUniqueChars(s[i-1: i])  v.s.  countUniqueChars(s[i-1: i-1])
 * Denote c = s.charAt(i), then for all substrings ending at index i-1, there could be 3 cases:
 *
 * // denote char c = s.charAt(i);
 * Case 1. s[k : i-1] contains 0 c              ==> countUniqueChars(s[k : i]) = countUniqueChars(s[k : i-1]) + 1
 * Case 2. s[k : i-1] contains 1 c              ==> countUniqueChars(s[k : i]) = countUniqueChars(s[k : i-1]) - 1
 * Case 3. s[k : i-1] contains at least 2 c     ==> countUniqueChars(s[k : i]) = countUniqueChars(s[k : i-1])
 * The reasoning is pretty starightforward:
 *
 *  Case 1.  s[k : i] has one more unique char (c itself) comparing to s[k : i-1]
 *  Case 2.  s[k : i] has one less unique char (c itself) comparing to s[k : i-1]
 *  Case 3.  any unique char in s[k : i-1] is still unique in s[k : i];
 *           c itself is not an unique char for either s[k : i-1] or s[k : i]
 * Thus we find the relation between the sum of countUniqueChars() for substrings ending index i and sum of countUniqueChars() for substrings ending index i-1.
 *
 *         sum of countUniqueChars() for index i
 *      =  sum of countUniqueChars() for index i-1  +  #(case 1)  -  #(case 2)  +  1
 * where the last 1 stands for the substring s[i : i]. The remaining things are just calculating the number of substrings in Case 1 & Case 2. We denote:
 *
 * // p : index of last appearance of c;     or -1 if not exist (c never appears)
 * // q : index of second last appearance of c;    or -1 if not exist (c never appears twice)
 * Then for s[k : i-1] whose starting point is k:
 *
 * Case 1. k in [p+1, i-1]   <==>  c appears 0 time in s[k : i-1]
 * Case 2. k in [q+1, p]     <==>  c appears 1 time in s[k : i-1]
 * Case 3. k in [0, q]       <==>  c appears at least 2 times in s[k : i-1]
 * Thus we have:
 *
 * #(Case 1)  =  (i-1) - (p+1) + 1
 *            =  i - p - 1
 * #(Case 2)  =  p - (q+1) + 1
 *            =  p - q
 * which leads to
 *
 *     sum of countUniqueChars() for index i
 *  =  sum of countUniqueChars() for index i-1  +  (i - p - 1)  -  (p - q)  +  1
 *  =  sum of countUniqueChars() for index i-1  +  (i - p - p + q)
 */
public class CountUniqueCharsInSubs {

    public int uniqueLetterString(String s) {
        int n = s.length();
        int[] lastOccur = new int[26];
        Arrays.fill(lastOccur, -1);
        int[] secondLastOccur = new int[26];
        Arrays.fill(secondLastOccur, -1);
        int count = 0;
        int res = 0;
        for (int i = 0; i < n; i++) {
            int index = s.charAt(i) - 'A';
            int p = lastOccur[index];
            int q = secondLastOccur[index];
            count += i - 2 * p + q;
            res += count;
            secondLastOccur[index] = lastOccur[index];
            lastOccur[index] = i;
        }
        return res;
    }

}
