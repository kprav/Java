public class Solution {
    ArrayList<ArrayList<String>> result = new ArrayList<>();
    
    private void addResult(String[][] board, int N) {
        ArrayList<String> solution = new ArrayList<>();
        result.add(solution);
        for (int i = 0; i < N; i++) {
            StringBuilder row = new StringBuilder();
            for (int j = 0; j < N; j++) {
                if (board[i][j] == null) {
                    board[i][j] = ".";
                }
                row.append(board[i][j]);
            }
            solution.add(row.toString());
        }
    }
    
    private boolean isValid(String[][] board, int row, int col, int N) {
        int i, j;
        for (i = 0; i < col; i++) {
            if ("Q".equals(board[row][i])) {
                return false;
            }
        }
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if ("Q".equals(board[i][j])) {
                return false;
            }
        }
        for (i = row, j = col; i < N && j >= 0; i++, j--) {
            if ("Q".equals(board[i][j])) {
                return false;
            }
        }
        return true;
    }
    
    private void solveNQueensHelper(String[][] board, int col, int N) {
        if (col >= N) {
            addResult(board, N);
        }
        for (int i = 0; i < N; i++) {
            if (isValid(board, i, col, N)) {
                board[i][col] = "Q";
                solveNQueensHelper(board, col + 1, N);
                board[i][col] = null;
            }
        }
    }
    
    public ArrayList<ArrayList<String>> solveNQueens(int a) {
        String[][] board = new String[a][a];
        solveNQueensHelper(board, 0, a);
        return result;
    }
}
