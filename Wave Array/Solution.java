public class Solution {
	public ArrayList<Integer> wave(ArrayList<Integer> a) {
	    if (a == null || a.size() <= 1)  {
	        return a;
	    } else if (a.size() == 2) {
	        Collections.sort(a, Collections.reverseOrder());
	        return a;
	    }
	    ArrayList<Integer> b = new ArrayList<Integer>();
	    Collections.sort(a);
	    int i = 0;
	    int j = 1;
	    while (i < a.size() && j < a.size()) {
	        b.add(a.get(j));
	        b.add(a.get(i));
	        i+=2;
	        j+=2;
	    }
	    if (i == a.size() - 1) {
	        b.add(a.get(i));
	    }
	    return b;
	}
}
