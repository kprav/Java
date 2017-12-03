public class Solution {
	public int romanToInt(String a) {
	    int num = 0;
	    char[] roman = a.toUpperCase().toCharArray();
	    Map<Character, Integer> romanToIntegerMap = new HashMap<Character, Integer>();
	    romanToIntegerMap.put('I', 1);
	    romanToIntegerMap.put('V', 5);
	    romanToIntegerMap.put('X', 10);
	    romanToIntegerMap.put('L', 50);
	    romanToIntegerMap.put('C', 100);
	    romanToIntegerMap.put('D', 500);
	    romanToIntegerMap.put('M', 1000);
	    for (int i = 0; i < roman.length; i++) {
	        if (i == roman.length - 1 || romanToIntegerMap.get(roman[i]) >= romanToIntegerMap.get(roman[i + 1])) {
	            num += romanToIntegerMap.get(roman[i]);
	        } else if (romanToIntegerMap.get(roman[i]) < romanToIntegerMap.get(roman[i + 1])) {
	            num -= romanToIntegerMap.get(roman[i]);
	        }
	    }
	    return num;
	}
}
