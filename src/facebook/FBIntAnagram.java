package facebook;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class FBIntAnagram {

    public static void main(String[] args) {
        FBIntAnagram fbIntAnagram = new FBIntAnagram();
        long startTime = System.nanoTime();
        System.out.println(fbIntAnagram.isAnagram("geeksforgeeksforgeeksgeeksgeeksforgeeksgeeksforgeeksforgeeksgeeksgeeksforgeeks",
                "forgeeksgeeksforgeeksgeeksgeeksforgeeksgeeksforgeeksforgeeksgeeksgeeksforgeeks"));
        long elapsedTime = System.nanoTime() - startTime;
        System.out.println("Total execution time (heuristic): " + elapsedTime);

        startTime = System.nanoTime();
        System.out.println(fbIntAnagram.isAnagram2("geeksforgeeksforgeeksgeeksgeeksforgeeksgeeksforgeeksforgeeksgeeksgeeksforgeeks",
                "forgeeksgeeksforgeeksgeeksgeeksforgeeksgeeksforgeeksforgeeksgeeksgeeksforgeeks"));
        elapsedTime = System.nanoTime() - startTime;
        System.out.println("Total execution time (randomness): " + elapsedTime);

        startTime = System.nanoTime();
        System.out.println(fbIntAnagram.isAnagram3("geeksforgeeksforgeeksgeeksgeeksforgeeksgeeksforgeeksforgeeksgeeksgeeksforgeeks",
                "forgeeksgeeksforgeeksgeeksgeeksforgeeksgeeksforgeeksforgeeksgeeksgeeksforgeeks"));
        elapsedTime = System.nanoTime() - startTime;
        System.out.println("Total execution time (simple): " + elapsedTime);
    }

    public boolean isAnagram(String s, String t) {
        if (s.equals(t)) {
            return true;
        }
        int n1 = s.length();
        int n2 = t.length();
        if (n1 != n2) {
            return false;
        }
        if (n1 == 0) {
            return true;
        }
        int sumS = 0;
        int sumT = 0;
        Map<Character, Integer> frequenciesS1 = new HashMap<>();
        Map<Character, Integer> frequenciesS2 = new HashMap<>();
        for (int i = 0; i < n1; i++) {
            char sChar = s.charAt(i);
            char tChar = t.charAt(i);
            updateFrequencies(frequenciesS1, sChar);
            updateFrequencies(frequenciesS2, tChar);
            sumS += sChar;
            sumT += tChar;
        }
        if (sumS != sumT) {
            return false;
        }
        for (Map.Entry<Character, Integer> e : frequenciesS1.entrySet()) {
            char c1 = e.getKey();
            int f1 = e.getValue();
            if (!frequenciesS2.containsKey(c1) || (f1 != frequenciesS2.get(c1))) {
                return false;
            }
        }
        return true;
    }

    private void updateFrequencies(Map<Character, Integer> frequencies, Character newChar) {
        if (frequencies.containsKey(newChar)) {
            frequencies.put(newChar, frequencies.get(newChar) + 1);
        } else {
            frequencies.put(newChar, 1);
        }
    }

    public boolean isAnagram2(String s, String t) {
        if (s.equals(t)) {
            return true;
        }
        int n1 = s.length();
        int n2 = t.length();
        if (n1 != n2) {
            return false;
        }
        if (n1 == 0) {
            return true;
        }
        if (n1 == 1) {
            return s.charAt(0) == t.charAt(0);
        }
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        boolean[] checked = new boolean[n1];
        Arrays.sort(sChars);
        Arrays.sort(tChars);
        for (int i = 0; i < n1; i++) {
            if (checked[i]) {
                continue;
            }
            if (sChars[i] != tChars[i]) {
                return false;
            }
            checked[i] = true;
            Random random = new Random();
            int randomInt = random.nextInt(n1 - 1);
            if (sChars[randomInt] != tChars[randomInt]) {
                return false;
            }
            checked[randomInt] = true;
        }
        return true;
    }

    public boolean isAnagram3(String s, String t) {
        if (s.equals(t)) {
            return true;
        }
        int n1 = s.length();
        int n2 = t.length();
        if (n1 != n2) {
            return false;
        }
        if (n1 == 0) {
            return true;
        }
        if (n1 == 1) {
            return s.charAt(0) == t.charAt(0);
        }
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        Arrays.sort(sChars);
        Arrays.sort(tChars);
        for (int i = 0; i < n1; i++) {
            if (sChars[i] != tChars[i]) {
                return false;
            }
        }
        return true;
    }

}
