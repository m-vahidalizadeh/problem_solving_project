package leetcode.medium;

import java.util.*;

/**
 * 1233. Remove Sub-Folders from the Filesystem
 * Given a list of folders, remove all sub-folders in those folders and return in any order the folders after removing.
 *
 * If a folder[i] is located within another folder[j], it is called a sub-folder of it.
 *
 * The format of a path is one or more concatenated strings of the form: / followed by one or more lowercase English letters. For example, /leetcode and /leetcode/problems are valid paths while an empty string and / are not.
 *
 * Example 1:
 *
 * Input: folder = ["/a","/a/b","/c/d","/c/d/e","/c/f"]
 * Output: ["/a","/c/d","/c/f"]
 * Explanation: Folders "/a/b/" is a subfolder of "/a" and "/c/d/e" is inside of folder "/c/d" in our filesystem.
 * Example 2:
 *
 * Input: folder = ["/a","/a/b/c","/a/b/d"]
 * Output: ["/a"]
 * Explanation: Folders "/a/b/c" and "/a/b/d/" will be removed because they are subfolders of "/a".
 * Example 3:
 *
 * Input: folder = ["/a/b/c","/a/b/ca","/a/b/d"]
 * Output: ["/a/b/c","/a/b/ca","/a/b/d"]
 *
 * Constraints:
 *
 * 1 <= folder.length <= 4 * 10^4
 * 2 <= folder[i].length <= 100
 * folder[i] contains only lowercase letters and '/'
 * folder[i] always starts with character '/'
 * Each folder name is unique.
 */
public class RemoveSubFolders {

    public class Trie {
        Map<String, Trie> children;
        boolean isLeaf;
        String path;

        public Trie() {
            this.children = new HashMap<>();
        }
    }

    Trie root;

    public List<String> removeSubfolders(String[] folder) {
        root = new Trie();
        Arrays.sort(folder, (a, b) -> a.length() - b.length());
        for (String input : folder) {
            if (!isSub(input)) insert(input);
        }
        Deque<Trie> stack = new ArrayDeque<>();
        List<String> res = new ArrayList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Trie curr = stack.pop();
            if (curr.isLeaf) res.add(curr.path);
            else {
                for (Trie child : curr.children.values()) stack.push(child);
            }
        }
        return res;
    }

    private boolean isSub(String input) {
        Trie curr = root;
        String[] tokens = input.split("/");
        for (String token : tokens) {
            if (!curr.children.containsKey(token)) return false;
            else {
                if (curr.children.get(token).isLeaf) return true;
            }
        }
        return false;
    }

    private void insert(String input) {
        Trie curr = root;
        String[] tokens = input.split("/");
        for (String token : tokens) {
            if (curr.children.containsKey(token)) {
                curr = curr.children.get(token);
            } else {
                Trie child = new Trie();
                curr.children.put(token, child);
                curr = child;
            }
        }
        curr.isLeaf = true;
        curr.path = input;
    }

}
