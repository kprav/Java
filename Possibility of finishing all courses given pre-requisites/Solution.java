public class Solution {
    
    private boolean dfs(int course, HashMap<Integer, ArrayList<Integer>> map, int[] visited) {
        if (visited[course] == -1) {
            return false;
        }
        if (visited[course] == 1) {
            return true;
        }
        visited[course] = -1;
        if (map.containsKey(course)) {
            for (Integer i : map.get(course)) {
                if (!dfs(i, map, visited)) {
                    return false;
                }
            }
        }
        visited[course] = 1;
        return true;
    }
    
    public int solve(int N, ArrayList<Integer> course, ArrayList<Integer> preReq) {
        if (course == null || preReq == null) {
            return 0;
        }
        if (course.size() != preReq.size()) {
            return 0;
        }
        if (N == 0 || course.size() == 0)  {
            return 1;
        }
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < course.size(); i++) {
            int c = course.get(i);
            ArrayList<Integer> list = map.getOrDefault(c, new ArrayList<Integer>());
            list.add(preReq.get(i));
            map.put(c, list);
        }
        int[] visited = new int[N+1];
        for (int i = 0; i < N; i++) {
            if (!dfs(i, map, visited)) {
                return 0;
            }
        }
        return 1;
    }
}
