public class Solution {
	public ArrayList<Integer> plusOne(ArrayList<Integer> a) {
	    Iterator iter = a.iterator();
	    while (iter.hasNext()) {
	        Integer digit = (Integer) iter.next();
	        if (digit.intValue() == 0) {
	            iter.remove();
	        } else {
	            break;
	        }
	    }
	    Collections.reverse(a);
	    int carry = 1;
	    int i = 0;
	    for (; i < a.size(); i++) {
	        int digit = a.get(i);
	        int digitPlusCarry = digit + carry;
	        digit = digitPlusCarry % 10;
	        carry = digitPlusCarry / 10;
	        a.set(i, digit);
	        if (carry == 0) {
	            break;
	        }
	    }
	    if (carry != 0) {
	        a.add(carry);
	    }
	    Collections.reverse(a);
	    return a;
	}
}
