package leetcode.medium;

import java.util.*;

/**
 * Synonymous Sentences
 * Given a list of pairs of equivalent words synonyms and a sentence text, Return all possible synonymous sentences sorted lexicographically.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * synonyms = [["happy","joy"],["sad","sorrow"],["joy","cheerful"]],
 * text = "I am happy today but was sad yesterday"
 * Output:
 * ["I am cheerful today but was sad yesterday",
 * ​​​​​​​"I am cheerful today but was sorrow yesterday",
 * "I am happy today but was sad yesterday",
 * "I am happy today but was sorrow yesterday",
 * "I am joy today but was sad yesterday",
 * "I am joy today but was sorrow yesterday"]
 * <p>
 * Constraints:
 * <p>
 * 0 <= synonyms.length <= 10
 * synonyms[i].length == 2
 * synonyms[0] != synonyms[1]
 * All words consist of at most 10 English letters only.
 * text is a single space separated sentence of at most 10 words.
 */
public class SynonymousSentences {

    public class Union {
        PriorityQueue<String> perms;
        Map<String, String> parents;
        Map<String, String> children;

        public Union() {
            parents = new HashMap<>();
            children = new HashMap<>();
            perms = new PriorityQueue<>();
        }

        public void addRelation(String a, String b) {
            boolean containsA = parents.containsKey(a);
            boolean containsB = parents.containsKey(b);
            if (!containsA && !containsB) {
                parents.put(a, b);
                children.put(b, a);
            } else if (containsB) {
                String parentB = getParent(b);
                parents.put(parentB, a);
                children.put(a, parentB);
            } else {
                String parentA = getParent(a);
                parents.put(parentA, b);
                children.put(b, parentA);
            }
        }

        public String getParent(String x) {
            while (parents.get(x) != null) x = parents.get(x);
            return x;
        }

        public Set<String> getChain(String x) {
            x = getParent(x);
            Set<String> result = new HashSet<>();
            while (children.containsKey(x)) {
                result.add(x);
                x = children.get(x);
            }
            result.add(x);
            return result;
        }

    }

    public List<String> generateSentences(List<List<String>> synonyms, String text) {
        Union u = new Union();
        for (List<String> synList : synonyms) u.addRelation(synList.get(0), synList.get(1));
        getPerms(text, 0, u, text.length(), "");
        List<String> result = new ArrayList<>();
        while (!u.perms.isEmpty()) result.add(u.perms.poll());
        return result;
    }

    public void getPerms(String text, int i, Union u, int n, String curr) {
        while (i < n && text.charAt(i) == ' ') {
            i++;
            curr += ' ';
        }
        if (i == n) {
            u.perms.add(curr);
            return;
        }
        String nextWord = getNextWord(text, i, n);
        int m = nextWord.length();
        if (u.parents.containsKey(nextWord) || u.children.containsKey(nextWord)) {
            Set<String> syns = u.getChain(nextWord);
            for (String s : syns) {
                getPerms(text, i + m, u, n, curr + s);
            }
        } else {
            getPerms(text, i + m, u, n, curr + nextWord);
        }
    }

    private String getNextWord(String text, int i, int n) {
        int j = i;
        while (j < n && text.charAt(j) != ' ') j++;
        return text.substring(i, j);
    }

    public static void main(String[] args) {
        SynonymousSentences s = new SynonymousSentences();
        List<List<String>> synonyms = List.of(List.of("happy", "joy"), List.of("sad", "sorrow"), List.of("joy", "cheerful"));
        s.generateSentences(synonyms, "I am happy today but was sad yesterday");
    }

}
