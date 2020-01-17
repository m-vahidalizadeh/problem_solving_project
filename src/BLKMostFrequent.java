import java.util.*;

public class BLKMostFrequent {

    public static void main(String[] args) {
     /*
Example 1:
Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
Output: ["i", "love"]
Explanation: "i" and "love" are the two most frequent words.
    Note that "i" comes before "love" due to a lower alphabetical order.

Example 2:
Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
Output: ["the", "is", "sunny", "day"]
Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
    with the number of occurrence being 4, 3, 2 and 1 respectively.
      */
        String[] example1 = new String[]{"i", "love", "leetcode", "i", "love", "coding"};
        System.out.println(topKFrequent(example1, 2));
        String[] example2 = new String[]{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};
        System.out.println(topKFrequent(example2, 4));
        String[] example3 = new String[]{"i", "love", "leetcode", "i", "love", "coding"};
        System.out.println(topKFrequent(example3, 1));
    }

    public static void printList(List<String> elements) {
        for (String e : elements) {
            System.out.print(e + ",");
        }
        System.out.println();
    }

    public static List<String> topKFrequent(String[] words, int k) {
        int maxFreq = Integer.MIN_VALUE;
        Map<String, Integer> wordFreq = new HashMap<>();
        Map<Integer, PriorityQueue<String>> frequencies = new HashMap<>();
        int newFrequency;
        for (int i = 0; i < words.length; i++) {
            if (wordFreq.containsKey(words[i])) {
                newFrequency = wordFreq.get(words[i]) + 1;
                wordFreq.put(words[i], newFrequency);
            } else {
                newFrequency = 1;
                wordFreq.put(words[i], newFrequency);
            }
            if (newFrequency > maxFreq) {
                maxFreq = newFrequency;
            }
            if (frequencies.containsKey(newFrequency)) {
                frequencies.get(newFrequency).add(words[i]);
            } else {
                PriorityQueue pq = new PriorityQueue();
                pq.add(words[i]);
                frequencies.put(newFrequency, pq);
            }
            if (newFrequency > 1) {
                frequencies.get(newFrequency - 1).remove(words[i]);
            }
        }
        List<String> result = new LinkedList<>();
        boolean foundKMostFrequent = false;
        int foundElements = 0;
        while (!foundKMostFrequent) {
            PriorityQueue<String> tempPq = frequencies.get(maxFreq);
            while (!tempPq.isEmpty()) {
                result.add(tempPq.poll());
                foundElements++;
                if (foundElements == k) {
                    return result;
                }
            }
            frequencies.remove(maxFreq);
            maxFreq--;
            if (foundElements == k) {
                foundKMostFrequent = true;
            }
        }
        return result;
    }

}
