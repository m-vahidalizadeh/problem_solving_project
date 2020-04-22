package uber;

import java.util.*;

public class UberOrganizeTickets {

    public static void main(String[] args) {
        /*
Example 1:
Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]

JFK,MUC,LHR,SFO,MUC,LHR,SFO,SJC,

Example 2:
Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
             But it is larger in lexical order.
         */
        List<List<String>> tickets = Arrays.asList(Arrays.asList("MUC", "LHR"), Arrays.asList("JFK", "MUC"),
                Arrays.asList("SFO", "SJC"), Arrays.asList("LHR", "SFO"));
        List<String> result = findItinerary(tickets);
        for (String r : result) {
            System.out.print(r + ",");
        }
    }

    public static List<String> findItinerary(List<List<String>> tickets) {
        // Tokenize the input
        HashMap<String, PriorityQueue<String>> flights = new HashMap<>();
        for (List<String> ticket : tickets) {
            PriorityQueue<String> tempPq;
            if (flights.containsKey(ticket.get(0))) {
                tempPq = flights.get(ticket.get(0));
                tempPq.add(ticket.get(1));
            } else {
                tempPq = new PriorityQueue<>();
                tempPq.add(ticket.get(1));
            }
            flights.put(ticket.get(0), tempPq);
        }
        // Build the results
        Map<String, Set<String>> resultSearch = new HashMap<>();
        List<String> result = new LinkedList<>();
        if (flights.containsKey("JFK")) {
            PriorityQueue<String> jfkFlightsPq = flights.get("JFK");
            for (String dest : jfkFlightsPq) {
                result.add("JFK");
                result.add(dest);
                if (resultSearch.containsKey("JFK")) {
                    Set<String> updatedSet = resultSearch.get("JFK");
                    updatedSet.add(dest);
                    resultSearch.put("JFK", updatedSet);
                } else {
                    Set<String> updatedSet = new HashSet<>();
                    updatedSet.add(dest);
                    resultSearch.put("JFK", updatedSet);
                }
            }
            flights.remove("JFK");
        }
        for (Map.Entry<String, PriorityQueue<String>> entry : flights.entrySet()) {
            String source = entry.getKey();
            if ("JFK".equals(source)) {
                continue;
            }
            PriorityQueue<String> dests = entry.getValue();
            for (String dest : dests) {
                if (resultSearch.containsKey(dest) && resultSearch.get(dest).contains(source)) {
                    continue;
                }
                result.add(source);
                result.add(dest);
                if (resultSearch.containsKey(source)) {
                    Set<String> updatedSet = resultSearch.get(source);
                    updatedSet.add(dest);
                    resultSearch.put(source, updatedSet);
                } else {
                    Set<String> updatedSet = new HashSet<>();
                    updatedSet.add(dest);
                    resultSearch.put(source, updatedSet);
                }
            }
        }
        return result;
    }

}
