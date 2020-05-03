package hackerrank;

import java.io.*;

public class SuperReducedString {

    static String superReducedString(String s) {
        int n = s.length();
        String result = "Empty String";
        for (int i = 0; i < n; i++) {
            Character tempCharS = Character.valueOf(s.charAt(i));
            if (i + 1 == n || !tempCharS.equals(Character.valueOf(s.charAt(i + 1)))) {
                if (result.equals("Empty String")) {
                    result = tempCharS.toString();
                } else {
                    result += tempCharS.toString();
                }
            } else {
                i++;
            }
        }
        if (!s.equals(result)) {
            return superReducedString(result);
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        try (
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("iofiles/srs.out")));
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        ) {
            String s = bufferedReader.readLine();
            String result = superReducedString(s);
            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }
    }
}
