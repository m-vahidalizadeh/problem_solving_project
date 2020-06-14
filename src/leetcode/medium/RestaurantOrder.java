package leetcode.medium;

import java.util.*;

/**
 * Display Table of Food Orders in a Restaurant
 * Given the array orders, which represents the orders that customers have done in a restaurant. More specifically orders[i]=[customerNamei,tableNumberi,foodItemi] where customerNamei is the name of the customer, tableNumberi is the table customer sit at, and foodItemi is the item customer orders.
 * <p>
 * Return the restaurant's “display table”. The “display table” is a table whose row entries denote how many of each food item each table ordered. The first column is the table number and the remaining columns correspond to each food item in alphabetical order. The first row should be a header whose first column is “Table”, followed by the names of the food items. Note that the customer names are not part of the table. Additionally, the rows should be sorted in numerically increasing order.
 * <p>
 * Example 1:
 * <p>
 * Input: orders = [["David","3","Ceviche"],["Corina","10","Beef Burrito"],["David","3","Fried Chicken"],["Carla","5","Water"],["Carla","5","Ceviche"],["Rous","3","Ceviche"]]
 * Output: [["Table","Beef Burrito","Ceviche","Fried Chicken","Water"],["3","0","2","1","0"],["5","0","1","0","1"],["10","1","0","0","0"]]
 * Explanation:
 * The displaying table looks like:
 * Table,Beef Burrito,Ceviche,Fried Chicken,Water
 * 3    ,0           ,2      ,1            ,0
 * 5    ,0           ,1      ,0            ,1
 * 10   ,1           ,0      ,0            ,0
 * For the table 3: David orders "Ceviche" and "Fried Chicken", and Rous orders "Ceviche".
 * For the table 5: Carla orders "Water" and "Ceviche".
 * For the table 10: Corina orders "Beef Burrito".
 * Example 2:
 * <p>
 * Input: orders = [["James","12","Fried Chicken"],["Ratesh","12","Fried Chicken"],["Amadeus","12","Fried Chicken"],["Adam","1","Canadian Waffles"],["Brianna","1","Canadian Waffles"]]
 * Output: [["Table","Canadian Waffles","Fried Chicken"],["1","2","0"],["12","0","3"]]
 * Explanation:
 * For the table 1: Adam and Brianna order "Canadian Waffles".
 * For the table 12: James, Ratesh and Amadeus order "Fried Chicken".
 * Example 3:
 * <p>
 * Input: orders = [["Laura","2","Bean Burrito"],["Jhon","2","Beef Burrito"],["Melissa","2","Soda"]]
 * Output: [["Table","Bean Burrito","Beef Burrito","Soda"],["2","1","1","1"]]
 * <p>
 * Constraints:
 * <p>
 * 1 <= orders.length <= 5 * 10^4
 * orders[i].length == 3
 * 1 <= customerNamei.length, foodItemi.length <= 20
 * customerNamei and foodItemi consist of lowercase and uppercase English letters and the space character.
 * tableNumberi is a valid integer between 1 and 500.
 */
public class RestaurantOrder {

    public List<List<String>> displayTable(List<List<String>> orders) {
        Set<String> foodSet = new HashSet<>();
        Set<Integer> tableSet = new HashSet<>();
        Map<Integer, Map<String, Integer>> tables = new HashMap<>();
        for (List<String> order : orders) {
            int table = Integer.parseInt(order.get(1));
            String food = order.get(2);
            tableSet.add(table);
            foodSet.add(food);
            updatesTables(tables, table, food);
        }
        PriorityQueue<Integer> tablePQ = new PriorityQueue<>(tableSet);
        List<Integer> tableList = new ArrayList<>();
        while (!tablePQ.isEmpty()) tableList.add(tablePQ.poll());
        PriorityQueue<String> foodPQ = new PriorityQueue<>(foodSet);
        List<String> foodList = new ArrayList<>();
        while (!foodPQ.isEmpty()) foodList.add(foodPQ.poll());
        List<List<String>> result = new ArrayList<>();
        List<String> headerList = new ArrayList<>();
        headerList.add("Table");
        headerList.addAll(foodList);
        result.add(headerList);
        for (int currTable : tableList) {
            Map<String, Integer> currEntry = tables.get(currTable);
            List<String> row = new ArrayList<>();
            row.add(String.valueOf(currTable));
            for (String foodR : foodList) row.add(String.valueOf(currEntry.getOrDefault(foodR, 0)));
            result.add(row);
        }
        return result;
    }

    private void updatesTables(Map<Integer, Map<String, Integer>> tables, int table, String food) {
        if (tables.containsKey(table)) {
            Map<String, Integer> tableEntry = tables.get(table);
            tableEntry.put(food, tableEntry.getOrDefault(food, 0) + 1);
            tables.put(table, tableEntry);
        } else {
            Map<String, Integer> tableEntry = new HashMap<>() {{
                put(food, 1);
            }};
            tables.put(table, tableEntry);
        }
    }

    public static void main(String[] args) {
        RestaurantOrder r = new RestaurantOrder();
        List<List<String>> orders = List.of(List.of("David", "3", "Ceviche"), List.of("Corina", "10", "Beef Burrito"),
                List.of("David", "3", "Fried Chicken"), List.of("Carla", "5", "Water"),
                List.of("Carla", "5", "Ceviche"), List.of("Rous", "3", "Ceviche"));
        System.out.println(r.displayTable(orders));
    }

}
