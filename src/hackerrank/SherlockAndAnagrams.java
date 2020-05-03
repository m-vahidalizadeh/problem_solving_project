package hackerrank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Solves the Sherlock and anagrams problem.
 */
public class SherlockAndAnagrams {

    static int sherlockAndAnagrams(String s) {
        int n = s.length();
        int total = 0;
        List<String> permutations = new ArrayList();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n + 1; j++) {
                permutations.add(s.substring(i, j));
            }
        }
        int sizeOfPermutations = permutations.size();
        for (int i = 0; i < sizeOfPermutations; i++) {
            for (int j = i + 1; j < sizeOfPermutations; j++) {
                String permutationI = permutations.get(i);
                String permutationJ = permutations.get(j);
                int permutationIN = permutationI.length();
                int permutationJN = permutationJ.length();
                if (i != j && permutationIN == permutationJN && areAnagram(permutations.get(i).toCharArray(), permutations.get(j).toCharArray())) {
                    total++;
                }
            }
        }
        return total;
    }

    static boolean areAnagram(char[] str1, char[] str2) {
        // Get lenghts of both strings
        int n1 = str1.length;
//        int n2 = str2.length;

        // If length of both strings is not same,
        // then they cannot be anagram
//        if (n1 != n2)
//            return false;

        // Sort both strings
        Arrays.sort(str1);
        Arrays.sort(str2);

        // Compare sorted strings
        for (int i = 0; i < n1; i++)
            if (str1[i] != str2[i])
                return false;

        return true;
    }

    public static void main(String[] args) throws IOException {
        try (
                BufferedWriter bufferedWriter =
                        new BufferedWriter(new FileWriter(new File("iofiles/saa.out")));
                Scanner scanner = new Scanner(System.in)
        ) {
            int q = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int qItr = 0; qItr < q; qItr++) {
                String s = scanner.nextLine();

                int result = sherlockAndAnagrams(s);

                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            }
        }
    }

}
