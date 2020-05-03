package hackerrank;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

/**
 * Show a double as US, India, China and France currencies.
 */
public class JavaCurrencyFormatter {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new File("iofiles/jcf.in"));) {
            double payment = scanner.nextDouble();
            scanner.close();
            NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
            System.out.println("US: " + currencyFormatter.format(payment));
            currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("en", "in"));
            System.out.println("India: " + currencyFormatter.format(payment));
            DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getCurrencyInstance(Locale.CHINA);
            System.out.println("China: " + decimalFormat.format(payment));
            decimalFormat = (DecimalFormat) NumberFormat.getCurrencyInstance(Locale.FRANCE);
            System.out.println("France: " + decimalFormat.format(payment));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
