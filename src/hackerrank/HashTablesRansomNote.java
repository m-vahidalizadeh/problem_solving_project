package hackerrank;

import java.util.*;

public class HashTablesRansomNote {

    // Complete the checkMagazine function below.
    static void checkMagazine(String[] magazine, String[] note) {
        Map<String, Integer> magazineHashmap = new HashMap<>();
        Arrays.stream(magazine).forEach(m -> {
            int amount = 1;
            if (magazineHashmap.containsKey(m)) {
                amount += magazineHashmap.get(m);
            }
            magazineHashmap.put(m, amount);
        });
        for (int i = 0; i < note.length; i++) {
            String noteI = note[i];
            if (magazineHashmap.containsKey(noteI)) {
                if (magazineHashmap.get(noteI) == 0) {
                    print("No");
                    return;
                } else {
                    magazineHashmap.put(noteI, magazineHashmap.get(noteI) - 1);
                }
            } else {
                print("No");
                return;
            }
        }
        print("Yes");
    }

    private static void print(String input) {
        System.out.println(input);
    }

    public static void main(String[] args) {

        try (
                Scanner scanner = new Scanner(System.in)
        ) {
            String[] mn = scanner.nextLine().split(" ");

            int m = Integer.parseInt(mn[0]);

            int n = Integer.parseInt(mn[1]);

            String[] magazine = new String[m];

            String[] magazineItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < m; i++) {
                String magazineItem = magazineItems[i];
                magazine[i] = magazineItem;
            }

            String[] note = new String[n];

            String[] noteItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                String noteItem = noteItems[i];
                note[i] = noteItem;
            }

            checkMagazine(magazine, note);
        }
    }

}
