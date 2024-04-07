package leetcode.companies.facebook;

import java.util.HashMap;
import java.util.Map;

/**
 *

 Code

 Testcase
 Testcase

 Test Result
 340. Longest Substring with At Most K Distinct Characters

 Given a string s and an integer k, return the length of the longest
 substring
 of s that contains at most k distinct characters.

 Example 1:

 Input: s = "eceba", k = 2
 Output: 3
 Explanation: The substring is "ece" with length 3.
 Example 2:

 Input: s = "aa", k = 1
 Output: 2
 Explanation: The substring is "aa" with length 2.

 Constraints:

 1 <= s.length <= 5 * 10^4
 0 <= k <= 50
 */
public class AtMostKDistinctChars {

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if(k==0) return 0;
        int left=0,right=0,max=0;
        Map<Character,Integer> window=new HashMap<>();
        while(right<s.length()){
            window.put(s.charAt(right),window.getOrDefault(s.charAt(right),0)+1);
            while(window.size()>k){
                window.put(s.charAt(left),window.get(s.charAt(left))-1);
                if(window.get(s.charAt(left))==0) window.remove(s.charAt(left));
                left++;
            }
            max=Math.max(max,right-left+1);
            right++;
        }
        return max;
    }

}
