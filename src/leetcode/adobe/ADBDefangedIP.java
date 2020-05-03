package leetcode.adobe;

public class ADBDefangedIP {

    /*
Example 1:
Input: address = "1.1.1.1"
Output: "1[.]1[.]1[.]1"

Example 2:
Input: address = "255.100.50.0"
Output: "255[.]100[.]50[.]0"

     */

    public static void main(String[] args) {
        String example1 = "1.1.1.1";
        System.out.println(defangIPaddr(example1));
        String example2 = "255.100.50.0";
        System.out.println(defangIPaddr(example2));
    }

    public static String defangIPaddr(String address) {
        return address.replaceAll("\\.", "[.]");
    }

}
