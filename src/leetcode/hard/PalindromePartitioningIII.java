package leetcode.hard;

/**
 * 1278. Palindrome Partitioning III
 * You are given a string s containing lowercase letters and an integer k. You need to :
 *
 * First, change some characters of s to other lowercase English letters.
 * Then divide s into k non-empty disjoint substrings such that each substring is palindrome.
 * Return the minimal number of characters that you need to change to divide the string.
 *
 * Example 1:
 *
 * Input: s = "abc", k = 2
 * Output: 1
 * Explanation: You can split the string into "ab" and "c", and change 1 character in "ab" to make it palindrome.
 * Example 2:
 *
 * Input: s = "aabbc", k = 3
 * Output: 0
 * Explanation: You can split the string into "aa", "bb" and "c", all of them are palindrome.
 * Example 3:
 *
 * Input: s = "leetcode", k = 8
 * Output: 0
 *
 * Constraints:
 *
 * 1 <= k <= s.length <= 100.
 * s only contains lowercase English letters.
 */
public class PalindromePartitioningIII {

    public int palindromePartition(String s, int k) {
        Integer[][] dp=new Integer[k+1][s.length()+1];
        return rec(s.toCharArray(),k,0,dp);
    }

    private int rec(char[] chars,int k,int index,Integer[][] dp){
        if(dp[k][index]!=null) return dp[k][index];
        if(k==1) return minChanges(chars,index,chars.length-1);
        if(k>chars.length-index) return Integer.MAX_VALUE/2;
        int min=Integer.MAX_VALUE;
        for(int i=0;i<chars.length;i++){
            int left=minChanges(chars,index,i);
            int right=rec(chars,k-1,i+1,dp);
            min=Math.min(min,left+right);
        }
        dp[k][index]=min;
        return min;
    }

    private int minChanges(char[] chars,int s,int e){
        int count=0;
        while(s<e){
            if(chars[s++]!=chars[e--]) count++;
        }
        return count;
    }

}
