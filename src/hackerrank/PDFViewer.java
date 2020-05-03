package hackerrank;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class PDFViewer {

    static int designerPdfViewer(int[] h, String word) {
        int height = 0;
        for (char c : word.toCharArray()) {
            int cHeight = h[c - 'a'];
            if (cHeight > height) {
                height = cHeight;
            }
        }
        return height * word.length();
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        try (
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("iofiles/pdfv.out")));
                Scanner scanner = new Scanner(System.in)
        ) {
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
