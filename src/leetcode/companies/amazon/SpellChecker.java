package leetcode.companies.amazon;

import java.util.*;

/**
 * 966. Vowel Spellchecker
 * Given a wordlist, we want to implement a spellchecker that converts a query word into a correct word.
 *
 * For a given query word, the spell checker handles two categories of spelling mistakes:
 *
 * Capitalization: If the query matches a word in the wordlist (case-insensitive), then the query word is returned with the same case as the case in the wordlist.
 * Example: wordlist = ["yellow"], query = "YellOw": correct = "yellow"
 * Example: wordlist = ["Yellow"], query = "yellow": correct = "Yellow"
 * Example: wordlist = ["yellow"], query = "yellow": correct = "yellow"
 * Vowel Errors: If after replacing the vowels ('a', 'e', 'i', 'o', 'u') of the query word with any vowel individually, it matches a word in the wordlist (case-insensitive), then the query word is returned with the same case as the match in the wordlist.
 * Example: wordlist = ["YellOw"], query = "yollow": correct = "YellOw"
 * Example: wordlist = ["YellOw"], query = "yeellow": correct = "" (no match)
 * Example: wordlist = ["YellOw"], query = "yllw": correct = "" (no match)
 * In addition, the spell checker operates under the following precedence rules:
 *
 * When the query exactly matches a word in the wordlist (case-sensitive), you should return the same word back.
 * When the query matches a word up to capitlization, you should return the first such match in the wordlist.
 * When the query matches a word up to vowel errors, you should return the first such match in the wordlist.
 * If the query has no matches in the wordlist, you should return the empty string.
 * Given some queries, return a list of words answer, where answer[i] is the correct word for query = queries[i].
 *
 * Example 1:
 *
 * Input: wordlist = ["KiTe","kite","hare","Hare"], queries = ["kite","Kite","KiTe","Hare","HARE","Hear","hear","keti","keet","keto"]
 * Output: ["kite","KiTe","KiTe","Hare","hare","","","KiTe","","KiTe"]
 *
 * Note:
 *
 * 1 <= wordlist.length <= 5000
 * 1 <= queries.length <= 5000
 * 1 <= wordlist[i].length <= 7
 * 1 <= queries[i].length <= 7
 * All strings in wordlist and queries consist only of english letters.
 */
public class SpellChecker {

    private Set<Character> vowels;
    private Set<String> perfectWords;
    private Map<String,String> capWords;
    private Map<String,String> vowelWords;

    public String[] spellchecker(String[] wordlist, String[] queries) {
        vowels = Set.of('a', 'e', 'i', 'o', 'u');
        perfectWords=new HashSet<>();
        capWords=new HashMap<>();
        vowelWords=new HashMap<>();
        for(String word:wordlist){
            perfectWords.add(word);
            String lowerWord = word.toLowerCase();
            capWords.putIfAbsent(lowerWord,word);
            vowelWords.putIfAbsent(maskVowel(lowerWord),word);
        }
        int n = queries.length;
        String[] result=new String[n];
        for(int i=0;i<n;i++){
            String q=queries[i];
            String lowerQ=q.toLowerCase();
            if(perfectWords.contains(q)) result[i]=q;
            else if(capWords.containsKey(lowerQ)) result[i]=capWords.get(lowerQ);
            else {
                String maskedVowel = maskVowel(lowerQ);
                result[i]=vowelWords.getOrDefault(maskedVowel,"");
            }
        }
        return result;
    }

    private String maskVowel(String word){
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<word.length();i++){
            char c = word.charAt(i);
            if(vowels.contains(c)) sb.append('*');
            else sb.append(c);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        SpellChecker s = new SpellChecker();
        System.out.println(Arrays.toString(s.spellchecker(new String[]{"KiTe", "kite", "hare", "Hare"},
                new String[]{"kite", "Kite", "KiTe", "Hare", "HARE", "Hear", "hear", "keti", "keet", "keto"})));
    }

}
