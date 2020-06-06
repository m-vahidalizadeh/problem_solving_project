package leetcode.companies.uber;

import java.util.*;

/**
 * Employee Importance
 * You are given a data structure of employee information, which includes the employee's unique id, his importance value and his direct subordinates' id.
 * <p>
 * For example, employee 1 is the leader of employee 2, and employee 2 is the leader of employee 3. They have importance value 15, 10 and 5, respectively. Then employee 1 has a data structure like [1, 15, [2]], and employee 2 has [2, 10, [3]], and employee 3 has [3, 5, []]. Note that although employee 3 is also a subordinate of employee 1, the relationship is not direct.
 * <p>
 * Now given the employee information of a company, and an employee id, you need to return the total importance value of this employee and all his subordinates.
 * <p>
 * Example 1:
 * <p>
 * Input: [[1, 5, [2, 3]], [2, 3, []], [3, 3, []]], 1
 * Output: 11
 * Explanation:
 * Employee 1 has importance value 5, and he has two direct subordinates: employee 2 and employee 3. They both have importance value 3. So the total importance value of employee 1 is 5 + 3 + 3 = 11.
 * <p>
 * Note:
 * <p>
 * One employee has at most one direct leader and may have several subordinates.
 * The maximum number of employees won't exceed 2000.
 */
public class EmployeeImportance {

    public static class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;

        public Employee(int id, int importance, List<Integer> subordinates) {
            this.id = id;
            this.importance = importance;
            this.subordinates = subordinates;
        }
    }

    public int getImportance(List<Employee> employees, int id) {
        Map<Integer, Integer> importanceMap = new HashMap<>();
        Map<Integer, List<Integer>> subordinatesMap = new HashMap<>();
        for (Employee e : employees) {
            importanceMap.put(e.id, e.importance);
            subordinatesMap.put(e.id, e.subordinates);
        }
        int sum = importanceMap.get(id);
        Queue<Integer> subsQ = new LinkedList<>(subordinatesMap.get(id));
        while (!subsQ.isEmpty()) {
            int currSub = subsQ.poll();
            sum += importanceMap.get(currSub);
            subsQ.addAll(subordinatesMap.get(currSub));
        }
        return sum;
    }

    public static void main(String[] args) {
        EmployeeImportance e = new EmployeeImportance();
        Employee employee1 = new Employee(1, 5, List.of(2, 3));
        Employee employee2 = new Employee(2, 3, Collections.emptyList());
        Employee employee3 = new Employee(3, 3, Collections.emptyList());
        List<Employee> employees = List.of(employee1, employee2, employee3);
        System.out.println(e.getImportance(employees, 1));
    }

}
