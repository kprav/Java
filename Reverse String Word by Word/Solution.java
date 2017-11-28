public class Solution {
	public String reverseWords(String a) {
	    if (a == null || a.length() == 0) {
	        return "";
	    }
	    String[] words = a.split(" ");
	    StringBuilder result = new StringBuilder();
	    for (int i = words.length - 1; i >=0; i--) {
	        result.append(words[i]).append(" ");
	    }
	    return result.substring(0, result.length() - 1);
	}
}
