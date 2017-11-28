public class Solution {
	public ArrayList<ArrayList<Integer>> generate(int a) {
	    ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
	    for (int i = 0; i < a; i++) {
	        ArrayList<Integer> row = new ArrayList<Integer>();
	        result.add(row);
	    }
	    for (int row = 0; row < a; row++) {
	        for (int col = 0; col <= row; col++) {
	            if (col == 0 || col == row) {
	                result.get(row).add(1);
	            } else {
	                result.get(row).add(result.get(row - 1).get(col - 1) + 
	                                    result.get(row - 1).get(col));
	            }
	        }
	    }
	    return result;
	}
}
