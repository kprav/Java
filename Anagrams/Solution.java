public class Solution {
	public ArrayList<ArrayList<Integer>> anagrams(final List<String> a) {
	    ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
	    HashMap<String, ArrayList<Integer>> map = new LinkedHashMap<String, ArrayList<Integer>> ();
	    int i = 1;
	    for (String s : a) {
	        char[] inputArr = s.toCharArray();
	        Arrays.sort(inputArr);
	        String inputStr = String.valueOf(inputArr);
	        ArrayList<Integer> list = map.getOrDefault(inputStr, new ArrayList<Integer>());
	        list.add(i++);
	        map.put(inputStr, list);
	    }
	    for (ArrayList<Integer> list : map.values()) {
	        result.add(list);
	    }
	    return result;
	}
}
