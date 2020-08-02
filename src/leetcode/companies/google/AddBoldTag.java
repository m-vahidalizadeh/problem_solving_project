package leetcode.companies.google;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

/**
 * Add Bold Tag in String
 * Given a string s and a list of strings dict, you need to add a closed pair of bold tag <b> and </b> to wrap the substrings in s that exist in dict. If two such substrings overlap, you need to wrap them together by only one pair of closed bold tag. Also, if two substrings wrapped by bold tags are consecutive, you need to combine them.
 * Example 1:
 * <p>
 * Input:
 * s = "abcxyz123"
 * dict = ["abc","123"]
 * Output:
 * "<b>abc</b>xyz<b>123</b>"
 * <p>
 * Example 2:
 * <p>
 * Input:
 * s = "aaabbcc"
 * dict = ["aaa","aab","bc"]
 * Output:
 * "<b>aaabbc</b>c"
 * <p>
 * Constraints:
 * <p>
 * The given dict won't contain duplicates, and its length won't exceed 100.
 * All the strings in input have length in range [1, 1000].
 * Note: This question is the same as 758: https://leetcode.com/problems/bold-words-in-string/
 */
public class AddBoldTag {

    public String addBoldTag(String s, String[] dict) {
//        Set<Integer> wordHashes = new HashSet<>();
//        for (String word : dict) wordHashes.add(word.hashCode());
        int n = s.length();
        boolean[] isBold = new boolean[n];
        int boldEnd = 0;
        for (int i = 0; i < n; i++) {
            for (String word : dict) {
                int wordLength = word.length();
                if (i + wordLength <= n && word.equals(s.substring(i, i + wordLength)))
//                if (i + wordLength <= n && wordHashes.contains(s.substring(i, i + wordLength).hashCode()))
                    boldEnd = Math.max(boldEnd, i + wordLength);
            }
            isBold[i] = boldEnd > i;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (isBold[i]) {
                sb.append("<b>");
                while (i < n && isBold[i]) sb.append(s.charAt(i++));
                sb.append("</b>");
                i--;
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        AddBoldTag a = new AddBoldTag();
        String[] dict1 = {"abc", "123"};
        System.out.println(a.addBoldTag("abcxyz123", dict1));
        String[] dict2 = {"aaa", "aab", "bc"};
        System.out.println(a.addBoldTag("aaabbcc", dict2));
    }

}
