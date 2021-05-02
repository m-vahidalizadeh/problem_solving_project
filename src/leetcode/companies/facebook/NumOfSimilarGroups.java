package leetcode.companies.facebook;

import java.util.HashSet;
import java.util.Set;

/**
 * 839. Similar String Groups
 * Two strings X and Y are similar if we can swap two letters (in different positions) of X, so that it equals Y. Also two strings X and Y are similar if they are equal.
 *
 * For example, "tars" and "rats" are similar (swapping at positions 0 and 2), and "rats" and "arts" are similar, but "star" is not similar to "tars", "rats", or "arts".
 *
 * Together, these form two connected groups by similarity: {"tars", "rats", "arts"} and {"star"}.  Notice that "tars" and "arts" are in the same group even though they are not similar.  Formally, each group is such that a word is in the group if and only if it is similar to at least one other word in the group.
 *
 * We are given a list strs of strings where every string in strs is an anagram of every other string in strs. How many groups are there?
 *
 * Example 1:
 *
 * Input: strs = ["tars","rats","arts","star"]
 * Output: 2
 * Example 2:
 *
 * Input: strs = ["omv","ovm"]
 * Output: 1
 *
 * Constraints:
 *
 * 1 <= strs.length <= 300
 * 1 <= strs[i].length <= 300
 * strs[i] consists of lowercase letters only.
 * All words in strs have the same length and are anagrams of each other.
 */
public class NumOfSimilarGroups {

    public class UF {
        int[] parents;
        int groupCount;

        public UF(int n) {
            parents = new int[n];
            for (int i = 0; i < n; i++) parents[i] = i;
            groupCount = n;
        }

        public void join(int i, int j) {
            int pI = find(i);
            int pJ = find(j);
            if (pI != pJ) {
                parents[pJ] = pI;
                groupCount--;
            }
        }

        public int find(int x) {
            while (x != parents[x]) x = parents[x];
            return x;
        }
    }

    public int numSimilarGroups(String[] strs) {
        int n = strs.length;
        UF uf = new UF(n);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int diff = diff(strs[i], strs[j]);
                if (diff == 0 || diff == 2) {
                    uf.join(i, j);
                }
            }
        }
        return uf.groupCount;
    }

    private int diff(String a, String b) {
        int diff = 0;
        Set<Character> aDiff = new HashSet<>();
        Set<Character> bDiff = new HashSet<>();
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                aDiff.add(a.charAt(i));
                bDiff.add(b.charAt(i));
                diff++;
                if (diff > 2) return -1;
            }
        }
        if (!(diff == 0 || diff == 2)) return -1;
        if (diff == 0) return 0;
        for (Character c : aDiff) {
            if (!bDiff.contains(c)) return -1;
        }
        return 2;
    }

    public static void main(String[] args) {
        NumOfSimilarGroups n = new NumOfSimilarGroups();
        System.out.println(n.numSimilarGroups(new String[]{"tars", "rats", "arts", "star"}));
    }

}
