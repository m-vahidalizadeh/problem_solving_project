package hackerrank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaStringTokens {

    public static void main(String[] args) {
        try (
                Scanner scanner = new Scanner(System.in)
        ) {
            List<String> tokensList = new ArrayList<>();
            String s = scanner.nextLine();
            Pattern p = Pattern.compile("[A-Za-z]+");
            Matcher m = p.matcher(s);
            while (m.find()) {
                String foundToken = m.group();
                if (!"".equals(foundToken.trim())) {
                    tokensList.add(foundToken);
                }
            }
            scanner.close();
            System.out.println(tokensList.size());
            tokensList.stream().forEach(t -> System.out.println(t));
        }
    }

}
