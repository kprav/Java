public class Solution {
	public int diffPossible(final List<Integer> a, int b) {
	    if (a == null || a.size() == 0 || a.size() == 1) {
	        return 0;
	    }
	    HashMap<Integer, Boolean> map = new HashMap<Integer, Boolean>();
	    for (Integer i : a) {
	        if (map.getOrDefault(i.intValue(), false)) {
	            if (b == 0) {
	                return 1;
	            }
	        } else {
	            map.put(i.intValue(), true);
	        }
	    }
	    if (b == 0) {
	        return 0;
	    }
	    for (Integer i : a) {
	        int num1 = i.intValue() + b;
	        int num2 = i.intValue() - b;
	        if (map.getOrDefault(num1, false) || map.getOrDefault(num2, false)) {
	            return 1;
	        }
	    }
	    return 0;
	}
}
