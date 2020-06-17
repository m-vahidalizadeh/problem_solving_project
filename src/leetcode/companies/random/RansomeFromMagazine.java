package leetcode.companies.random;

/**
 * Ransom Note
 * Given an arbitrary ransom note string and another string containing letters from all the magazines, write a function that will return true if the ransom note can be constructed from the magazines ; otherwise, it will return false.
 *
 * Each letter in the magazine string can only be used once in your ransom note.
 *
 * Example 1:
 *
 * Input: ransomNote = "a", magazine = "b"
 * Output: false
 * Example 2:
 *
 * Input: ransomNote = "aa", magazine = "ab"
 * Output: false
 * Example 3:
 *
 * Input: ransomNote = "aa", magazine = "aab"
 * Output: true
 *
 * Constraints:
 *
 * You may assume that both strings contain only lowercase letters.
 */
public class RansomeFromMagazine {

    public boolean canConstruct(String ransomNote, String magazine) {
        int[] rFreq = new int[26];
        int[] mFreq = new int[26];
        for (int i = 0; i < ransomNote.length(); i++) rFreq[ransomNote.charAt(i) - 'a'] += 1;
        for (int i = 0; i < magazine.length(); i++) mFreq[magazine.charAt(i) - 'a'] += 1;
        for (int i = 0; i < 26; i++) if (rFreq[i] > mFreq[i]) return false;
        return true;
    }

}
