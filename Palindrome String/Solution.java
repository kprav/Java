public class Solution {
	public int isPalindrome(String a) {
	    if (a == null || a.length() == 0) {
	        return 1;
	    }
	    a = a.replaceAll("\\s+","");
	    char[] input = a.toLowerCase().toCharArray();
	    int i = 0;
	    int j = input.length - 1;
	    int isPalindrome = 1;
	    while (i < j) {
	        if (!((input[i] >= 'a' && input[i] <= 'z') || (input[i] >= '0' && input[i] <= '9'))) {
	            i++;
	            continue;
	        }
	        if (!((input[j] >= 'a' && input[j] <= 'z') || (input[j] >= '0' && input[j] <= '9'))) {
	            j--;
	            continue;
	        }
	        if (input[i] != input[j]) {
	            isPalindrome = 0;
	            break;
	        }
	        i++;
	        j--;
	    }
	    return isPalindrome;
	}
}
