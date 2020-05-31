package leetcode.companies.google;

/**
 * Find And Replace in String
 * To some string S, we will perform some replacement operations that replace groups of letters with new ones (not necessarily the same size).
 * <p>
 * Each replacement operation has 3 parameters: a starting index i, a source word x and a target word y.  The rule is that if x starts at position i in the original string S, then we will replace that occurrence of x with y.  If not, we do nothing.
 * <p>
 * For example, if we have S = "abcd" and we have some replacement operation i = 2, x = "cd", y = "ffff", then because "cd" starts at position 2 in the original string S, we will replace it with "ffff".
 * <p>
 * Using another example on S = "abcd", if we have both the replacement operation i = 0, x = "ab", y = "eee", as well as another replacement operation i = 2, x = "ec", y = "ffff", this second operation does nothing because in the original string S[2] = 'c', which doesn't match x[0] = 'e'.
 * <p>
 * All these operations occur simultaneously.  It's guaranteed that there won't be any overlap in replacement: for example, S = "abc", indexes = [0, 1], sources = ["ab","bc"] is not a valid test case.
 * <p>
 * Example 1:
 * <p>
 * Input: S = "abcd", indexes = [0,2], sources = ["a","cd"], targets = ["eee","ffff"]
 * Output: "eeebffff"
 * Explanation: "a" starts at index 0 in S, so it's replaced by "eee".
 * "cd" starts at index 2 in S, so it's replaced by "ffff".
 * Example 2:
 * <p>
 * Input: S = "abcd", indexes = [0,2], sources = ["ab","ec"], targets = ["eee","ffff"]
 * Output: "eeecd"
 * Explanation: "ab" starts at index 0 in S, so it's replaced by "eee".
 * "ec" doesn't starts at index 2 in the original S, so we do nothing.
 * Notes:
 * <p>
 * 0 <= indexes.length = sources.length = targets.length <= 100
 * 0 < indexes[i] < S.length <= 1000
 * All characters in given inputs are lowercase letters.
 */
public class StringReplacements {

    public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        int n = S.length();
        Integer[] matches = new Integer[n];
        for (int i = 0; i < indexes.length; i++) {
            String currentSource = sources[i];
            int currentIndex = indexes[i];
            if (currentSource.equals(S.substring(currentIndex, currentIndex + currentSource.length())))
                matches[currentIndex] = i;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            Integer matchesI = matches[i];
            if (matchesI != null) {
                sb.append(targets[matchesI]);
                i += sources[matchesI].length() - 1;
            } else {
                sb.append(S.charAt(i));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        StringReplacements s = new StringReplacements();

//        String S = "abcd";
//        int[] indexes = {0, 2};
//        String[] sources = {"a", "cd"};
//        String[] targets = {"eee", "ffff"};
//        System.out.println(s.findReplaceString(S, indexes, sources, targets));

        String S = "abcd";
        int[] indexes = {0, 2};
        String[] sources = {"ab", "ec"};
        String[] targets = {"eee", "ffff"};
        System.out.println(s.findReplaceString(S, indexes, sources, targets));
    }

}
