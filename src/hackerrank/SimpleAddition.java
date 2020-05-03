package hackerrank;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * Java varargs example.
 */
public class SimpleAddition {
    static class Add {
        public int add(int... n) {
            int result = 0;

            for (int i = 0; i < n.length; i++) {
                if (i == 0) {
                    System.out.print(n[i]);
                } else {
                    System.out.print("+" + n[i]);
                }
                result += n[i];
            }
            System.out.println("=" + result);
            return result;
        }
    }

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("iofiles/sa.in"))) {
            int n1 = Integer.parseInt(br.readLine());
            int n2 = Integer.parseInt(br.readLine());
            int n3 = Integer.parseInt(br.readLine());
            int n4 = Integer.parseInt(br.readLine());
            int n5 = Integer.parseInt(br.readLine());
            int n6 = Integer.parseInt(br.readLine());
            System.out.println(n1 + ", " + n2 + ", " + n3 + ", " + n4 + ", " + n5 + ", " + n6);

            Add ob = new Add();
            ob.add(n1, n2);
            ob.add(n1, n2, n3);
            ob.add(n1, n2, n3, n4, n5);
            ob.add(n1, n2, n3, n4, n5, n6);
            Method[] methods = Add.class.getDeclaredMethods();
            Set<String> set = new HashSet<>();
            boolean overload = false;
            for (int i = 0; i < methods.length; i++) {
                if (set.contains(methods[i].getName())) {
                    overload = true;
                    break;
                }
                set.add(methods[i].getName());

            }
            if (overload) {
                throw new Exception("Overloading not allowed");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
