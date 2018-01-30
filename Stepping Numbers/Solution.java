public class Solution {
    private void bfs(int N, int M, int i, TreeSet<Integer> result) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(i);
        while (!queue.isEmpty()) {
            int num = queue.remove();
            if (num >= N && num <= M) {
                result.add(num);
            }
            if (num == 0 || num > M) {
                continue;
            }
            int lastDigit = num % 10;
            int leftNeighbor = (num * 10) + (lastDigit - 1);
            int rightNeighbor = (num * 10) + (lastDigit + 1);
            if (lastDigit == 9) {
                queue.add(leftNeighbor);
            } else if (lastDigit == 0) {
                queue.add(rightNeighbor);
            } else {
                queue.add(leftNeighbor);
                queue.add(rightNeighbor);
            }
        }
    }
    
    public ArrayList<Integer> stepnum(int N, int M) {
        TreeSet<Integer> result = new TreeSet<>();
        for (int i = 0; i <= 9; i++) {
            bfs(N, M, i, result);
        }
        return new ArrayList<Integer>(result);
    }
}
