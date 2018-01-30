public class Solution {
	public void solve(ArrayList<ArrayList<Character>> board) {
	    int numRows = board.size();
	    int numCols = board.get(0).size();
	    for (int i = 0; i < numRows; i++) {
	        if (board.get(i).get(0) == 'O') {
	            dfs(board, i, 0);
	        }
	        if (board.get(i).get(numCols - 1) == 'O') {
	            dfs(board, i, numCols - 1);
	        }
	    }
	    for (int j = 0; j < numCols; j++) {
	        if (board.get(0).get(j) == 'O') {
	            dfs(board, 0, j);
	        }
	        if (board.get(numRows - 1).get(j) == 'O') {
	            dfs(board, numRows - 1, j);
	        }
	    }
	    for (int i = 0; i < numRows; i++) {
	        for (int j = 0; j < numCols; j++) {
	            if (board.get(i).get(j) == 'O') {
	                board.get(i).set(j, 'X');
	            } else if (board.get(i).get(j) == '#') {
	                board.get(i).set(j, 'O');
	            }
	        }
	    }
	}
	
	private void dfs(ArrayList<ArrayList<Character>> board, int i, int j) {
	    if (i < 0 || i >= board.size() || j < 0 || j >= board.get(0).size()) {
	        return;
	    }
	    if (board.get(i).get(j) != 'O') {
	        return;
	    }
	    board.get(i).set(j, '#');
	    dfs(board, i, j - 1);
	    dfs(board, i, j + 1);
	    dfs(board, i - 1, j);
	    dfs(board, i + 1, j);
	}
}
