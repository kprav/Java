import java.io.*;
import java.util.*;

public class Solution {
    private int[] bfs(int node, HashMap<Integer, ArrayList<Integer>> map) {
        int[] distance = new int[map.size()];
        for (int i = 0; i < distance.length; i++) {
            distance[i] = -1;
        }
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(node);
        distance[node] = 0;
        while (!queue.isEmpty()) {
            int curr = queue.remove();
            ArrayList<Integer> list = map.get(curr);
            for (Integer i : list) {
                if (distance[i] == -1) {
                    distance[i] = distance[curr] + 1;
                    queue.add(i);
                }
            }
        }
        int[] nodeDistPair = new int[2];
        nodeDistPair[1] = Integer.MIN_VALUE;
        for (int i = 0; i < distance.length; i++) {
            if (distance[i] > nodeDistPair[1]) {
                nodeDistPair[1] = distance[i];
                nodeDistPair[0] = i;
            }
        }
        return nodeDistPair;
    }

    public int solve(ArrayList<Integer> tree) {
        if (tree == null || tree.size() == 0 || tree.size() == 1) {
            return 0;
        } else if (tree.size() == 2) {
            return 1;
        }
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < tree.size(); i++) {
            int node = tree.get(i);
            if (node == -1) {
                continue;
            }
            ArrayList<Integer> list = map.getOrDefault(node, new ArrayList<Integer>());
            list.add(i);
            map.put(node, list);
            node = i;
            list = map.getOrDefault(node, new ArrayList<Integer>());
            list.add(tree.get(i));
            map.put(i, list);
        }
        int[] nodeDistPair1 = bfs(0, map);
        int[] nodeDistPair2 = bfs(nodeDistPair1[0], map);
        return nodeDistPair2[1];
    }
}
