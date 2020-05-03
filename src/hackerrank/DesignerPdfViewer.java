package hackerrank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

// Solves Designer PDF Viewer problem.
public class DesignerPdfViewer {

    static int designerPdfViewer(int[] h, String word) {
        return word.length() * word.chars().map(c -> h[c - 'a']).max().getAsInt();
    }

    public static void main(String[] args) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("iofiles/dpv.out")));
             Scanner scanner = new Scanner(System.in)) {
            int[] h = new int[26];
            String[] hItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            for (int i = 0; i < 26; i++) {
                int hItem = Integer.parseInt(hItems[i]);
                h[i] = hItem;
            }
            String word = scanner.nextLine();
            int result = designerPdfViewer(h, word);
            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }
    }

}
