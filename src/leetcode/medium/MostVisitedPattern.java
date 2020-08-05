package leetcode.medium;

import java.util.*;

/**
 * Analyze User Website Visit Pattern
 * We are given some website visits: the user with name username[i] visited the website website[i] at time timestamp[i].
 * <p>
 * A 3-sequence is a list of websites of length 3 sorted in ascending order by the time of their visits.  (The websites in a 3-sequence are not necessarily distinct.)
 * <p>
 * Find the 3-sequence visited by the largest number of users. If there is more than one solution, return the lexicographically smallest such 3-sequence.
 * <p>
 * Example 1:
 * <p>
 * Input: username = ["joe","joe","joe","james","james","james","james","mary","mary","mary"], timestamp = [1,2,3,4,5,6,7,8,9,10], website = ["home","about","career","home","cart","maps","home","home","about","career"]
 * Output: ["home","about","career"]
 * Explanation:
 * The tuples in this example are:
 * ["joe", 1, "home"]
 * ["joe", 2, "about"]
 * ["joe", 3, "career"]
 * ["james", 4, "home"]
 * ["james", 5, "cart"]
 * ["james", 6, "maps"]
 * ["james", 7, "home"]
 * ["mary", 8, "home"]
 * ["mary", 9, "about"]
 * ["mary", 10, "career"]
 * The 3-sequence ("home", "about", "career") was visited at least once by 2 users.
 * The 3-sequence ("home", "cart", "maps") was visited at least once by 1 user.
 * The 3-sequence ("home", "cart", "home") was visited at least once by 1 user.
 * The 3-sequence ("home", "maps", "home") was visited at least once by 1 user.
 * The 3-sequence ("cart", "maps", "home") was visited at least once by 1 user.
 * <p>
 * Note:
 * <p>
 * 3 <= N = username.length = timestamp.length = website.length <= 50
 * 1 <= username[i].length <= 10
 * 0 <= timestamp[i] <= 10^9
 * 1 <= website[i].length <= 10
 * Both username[i] and website[i] contain only lowercase characters.
 * It is guaranteed that there is at least one user who visited at least 3 websites.
 * No user visits two websites at the same time.
 */
public class MostVisitedPattern {

    public static class Visit {
        String username;
        int timestamp;
        String website;

        public Visit(String username, int timestamp, String website) {
            this.username = username;
            this.timestamp = timestamp;
            this.website = website;
        }
    }

    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        int n = username.length;
        List<Visit> visits = new ArrayList<>();
        for (int i = 0; i < n; i++) visits.add(new Visit(username[i], timestamp[i], website[i]));
        Collections.sort(visits, (a, b) -> a.timestamp - b.timestamp);
        HashMap<String, List<String>> userToWebs = new HashMap<>();
        for (Visit visit : visits) {
            userToWebs.putIfAbsent(visit.username, new ArrayList<>());
            userToWebs.get(visit.username).add(visit.website);
        }
        HashMap<String, Integer> seqToCount = new HashMap<>();
        int maxCount = 0;
        String maxSeq = "";
        for (Map.Entry<String, List<String>> e : userToWebs.entrySet()) {
            Set<String> userSeqs = getUserSeqs(e.getValue());
            for (String seq : userSeqs) {
                int tempCount = seqToCount.getOrDefault(seq, 0) + 1;
                seqToCount.put(seq, tempCount);
                if (
                        tempCount > maxCount
                                ||
                                tempCount == maxCount && seq.compareTo(maxSeq) < 0
                ) {
                    maxCount = tempCount;
                    maxSeq = seq;
                }
            }
        }
        String[] splits = maxSeq.split(",");
        return List.of(splits[0], splits[1], splits[2]);
    }

    private Set<String> getUserSeqs(List<String> websites) {
        Set<String> result = new HashSet<>();
        int n = websites.size();
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    result.add(websites.get(i) + "," + websites.get(j) + "," + websites.get(k));
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        MostVisitedPattern m = new MostVisitedPattern();
//        String[] username = {"joe", "joe", "joe", "james", "james", "james", "james", "mary", "mary", "mary"};
//        int[] timestamp = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
//        String[] website = {"home", "about", "career", "home", "cart", "maps", "home", "home", "about", "career"};
//        System.out.println(m.mostVisitedPattern(username, timestamp, website));
        /*
        ["u1","u1","u1","u2","u2","u2"]
[1,2,3,4,5,6]
["a","b","a","a","b","c"]
         */
        String[] username = {"u1", "u1", "u1", "u2", "u2", "u2"};
        int[] timestamp = {1, 2, 3, 4, 5, 6};
        String[] website = {"a", "b", "a", "a", "b", "c"};
        System.out.println(m.mostVisitedPattern(username, timestamp, website));
    }

}
