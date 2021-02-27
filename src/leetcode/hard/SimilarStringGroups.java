package leetcode.hard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
public class SimilarStringGroups {

    public int numSimilarGroups(String[] strs) {
        Map<String, Set<String>> graph = buildGraph(strs);
        if (graph.size() == 1) return 1;
        int count = 0;
        Set<String> visited = new HashSet<>();
        for (String node : graph.keySet()) {
            if (!visited.contains(node)) {
                dfs(visited, node, graph);
                count++;
            }
        }
        return count;
    }

    private void dfs(Set<String> visited, String node, Map<String, Set<String>> graph) {
        visited.add(node);
        for (String nei : graph.get(node)) {
            if (!visited.contains(nei)) dfs(visited, nei, graph);
        }
    }

    private Map<String, Set<String>> buildGraph(String[] strs) {
        Map<String, Set<String>> graph = new HashMap<>();
        for (String str : strs) graph.put(str, new HashSet<>());
        int n = strs.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isSimilar(strs[i], strs[j])) {
                    graph.get(strs[i]).add(strs[j]);
                    graph.get(strs[j]).add(strs[i]);
                }
            }
        }
        return graph;
    }

    private boolean isSimilar(String a, String b) {
        int diff = 0;
        int l = a.length();
        for (int i = 0; i < l; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                diff++;
                if (diff > 2) return false;
            }
        }
        return true;
    }

}
