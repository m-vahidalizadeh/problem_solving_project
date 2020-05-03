package hackerrank;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Show available methods of a class by reflection (sorted).
 */
public class JavaReflection {

    public static void main(String[] args) {
        Class student = Student.class;
        Method[] methods = student.getMethods();

        ArrayList<String> methodList = new ArrayList<>();
        for (int i = 0; i < methods.length; i++) {
            if (student.equals(methods[i].getDeclaringClass())) {
                methodList.add(methods[i].getName());
            }
        }
        Collections.sort(methodList);
        for (String name : methodList) {
            System.out.println(name);
        }
    }

}
