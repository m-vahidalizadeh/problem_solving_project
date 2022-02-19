package leetcode.hard;

import java.util.*;

/**
 * 332. Reconstruct Itinerary
 * You are given a list of airline tickets where tickets[i] = [fromi, toi] represent the departure and the arrival airports of one flight. Reconstruct the itinerary in order and return it.
 *
 * All of the tickets belong to a man who departs from "JFK", thus, the itinerary must begin with "JFK". If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string.
 *
 * For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
 * You may assume all tickets form at least one valid itinerary. You must use all the tickets once and only once.
 *
 * Example 1:
 *
 * Input: tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
 * Output: ["JFK","MUC","LHR","SFO","SJC"]
 * Example 2:
 *
 * Input: tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
 * Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"] but it is larger in lexical order.
 *
 * Constraints:
 *
 * 1 <= tickets.length <= 300
 * tickets[i].length == 2
 * fromi.length == 3
 * toi.length == 3
 * fromi and toi consist of uppercase English letters.
 * fromi != toi
 */
public class ReconstructItinerary {

    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> ticketsMap = new HashMap<>(); // Find the destinations of source in O(1)
        LinkedList<String> path = new LinkedList<>(); // A LL so we can add to the beginning with O(1)
        for (List<String> ticket : tickets) // Make a map for efficient access
            ticketsMap.computeIfAbsent(ticket.get(0), x -> new PriorityQueue<>()).add(ticket.get(1)); // source->PQ[destinations]
        dfs("JFK", ticketsMap, path); // Start from JFK
        return path;
    }

    private void dfs(String airport, Map<String, PriorityQueue<String>> targets, LinkedList<String> path) {
        while (targets.containsKey(airport) && !targets.get(airport).isEmpty()) // If there is any destination from this source
            dfs(targets.get(airport).poll(), targets, path); // Visit the dest and remove from the destinations of the source
        path.addFirst(airport); // Add the curr airport at the beginning of the path: JFK, rec(...)
    }

}
