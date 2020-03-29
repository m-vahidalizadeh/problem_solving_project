import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupThePeople {

    public static void main(String[] args) {
        GroupThePeople groupThePeople = new GroupThePeople();
        int[] groupSizes1 = {3, 3, 3, 3, 3, 1, 3};
        System.out.println(groupThePeople.groupThePeople(groupSizes1));
        int[] groupSizes2 = {2, 1, 3, 3, 3, 2};
        System.out.println(groupThePeople.groupThePeople(groupSizes2));
    }

    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer, List<Integer>> groups = new HashMap<>();
        int n = groupSizes.length;
        for (int i = 0; i < n; i++) {
            int groupSizeI = groupSizes[i];
            if (groups.containsKey(groupSizeI)) {
                List<Integer> currentList = groups.get(groupSizeI);
                if (currentList.size() < groupSizeI) {
                    currentList.add(i);
                    groups.put(groupSizeI, currentList);
                } else {
                    result.add(currentList);
                    List<Integer> newList = new ArrayList<>();
                    newList.add(i);
                    groups.put(groupSizeI, newList);
                }
            } else {
                List<Integer> newList = new ArrayList<>();
                newList.add(i);
                groups.put(groupSizeI, newList);
            }
        }
        result.addAll(groups.values());
        return result;
    }

}
