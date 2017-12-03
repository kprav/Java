public class Solution {
	public String addBinary(String a, String b) {
	    
	    if (a == null && b == null) {
	        return null;
	    } else if (a == null) {
	        return b;
	    } else if (b == null) {
	        return a;
	    } else if (a.length() == 0 && b.length() == 0) {
	        return "";
	    }
	    
	    if (a.length() < b.length()) {
	        String temp = a;
	        a = b;
	        b = temp;
	    }
	    
	    StringBuilder a1 = new StringBuilder(a);
	    StringBuilder b1 = new StringBuilder(b);
	    StringBuilder zeroes = new StringBuilder();
	    int lenDiff = a.length() - b.length();
	    for (int i = 0; i < lenDiff; i++) {
	        zeroes.append("0");
	    }
	    b1 = zeroes.append(b1);
	    
	    char[] num1 = a1.reverse().toString().toCharArray();
	    char[] num2 = b1.reverse().toString().toCharArray();
	    StringBuilder result = new StringBuilder();
	    int digit = 0;
	    int carry = 0;
	    
	    for (int i = 0; i < num1.length; i++) {
	        int d1 = Character.getNumericValue(num1[i]);
	        int d2 = Character.getNumericValue(num2[i]);
	        if (d1 == 0 && d2 == 0 && carry == 0) {
	            digit = 0;
	            carry = 0;
	        } else if (d1 == 0 && d2 == 0 && carry == 1) {
	            digit = 1;
	            carry = 0;
	        } else if (d1 == 0 && d2 == 1 && carry == 0) {
	            digit = 1;
	            carry = 0;
	        } else if (d1 == 0 && d2 == 1 && carry == 1) {
	            digit = 0;
	            carry = 1;
	        } else if (d1 == 1 && d2 == 0 && carry == 0) {
	            digit = 1;
	            carry = 0;
	        } else if (d1 == 1 && d2 == 0 && carry == 1) {
	            digit = 0;
	            carry = 1;
	        } else if (d1 == 1 && d2 == 1 && carry == 0) {
	            digit = 0;
	            carry = 1;
	        } else if (d1 == 1 && d2 == 1 && carry == 1) {
	            digit = 1;
	            carry = 1;
	        }
	        result.append(digit);
	    }
	    
	    if (carry != 0) {
	        result.append(carry);
	    }
	    
	    String res = result.reverse().toString();
	    
	    int i = 0;
	    for (i = 0; i < res.length(); i++) {
	        if (res.charAt(i) != '0') {
	            break;
	        }
	    }
	    
	    return res.substring(i, res.length());
	}
}
