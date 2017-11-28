public class Solution {
	public ArrayList<Integer> maxset(ArrayList<Integer> a) {
	    long sum = Long.MIN_VALUE;
	    int startIndex = 0;
	    int endIndex = 0;
	    int currStartIndex = 0;
	    int currEndIndex = 0;
	    long currSum = 0;
	    int i = 0;
	    int j = 0;
	    boolean begin = true;
	    boolean calculated = false;
	    while (i < a.size() && j < a.size()) {
	        if (a.get(i) >= 0 && begin) {
	            currStartIndex = i;
	            currEndIndex = i;
	            currSum += a.get(i);
	            begin = false;
	            j++;
	            calculated = true;
	        } else if (a.get(j) >= 0) {
	            currSum += a.get(j);
	            currEndIndex = j;
	            j++;
	        } else {
	            j++;
	            i = j;
	            begin = true;
	            if (currSum > sum) {
	              sum = currSum;
	              startIndex = currStartIndex;
	              endIndex = currEndIndex;
	            } else if (currSum == sum) {
	                if (currEndIndex - currStartIndex > endIndex - startIndex) {
	                    sum = currSum;
	                    startIndex = currStartIndex;
	                    endIndex = currEndIndex;
	                }
	            }
	            currSum = 0;
	        }
	    }
	    if (sum == Long.MIN_VALUE || currSum > sum) {
	        sum = currSum;
	        startIndex = currStartIndex;
	        endIndex = currEndIndex;
	    } else if (currSum == sum) {
	        if (currEndIndex - currStartIndex > endIndex - startIndex) {
	            sum = currSum;
	            startIndex = currStartIndex;
	            endIndex = currEndIndex;
	        }
	    }
	    ArrayList<Integer> result = new ArrayList<Integer>();
	    if (calculated) {
	        for (int z = startIndex; z <= endIndex; z++) {
	            result.add(a.get(z));
	        }
	    }
	    return result;
	}
}
