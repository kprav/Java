public class Solution {
    
    private enum IntegerToRomanEnum {
        I(1), IV(4), V(5), IX(9), X(10), XL(40), L(50), XC(90), C(100), CD(400), D(500), CM(900), M(1000);
        int value;
        private IntegerToRomanEnum(int value) {
            this.value = value;
        }
    }
    
    public String intToRoman(int a) {
        if (a == 0) {
            return "nulla";
        }
        boolean negative = (a < 0) ? true : false;
        if (negative) {
            a = a * (-1);
        }
        StringBuilder roman = new StringBuilder();
        IntegerToRomanEnum[] romanSymbols = IntegerToRomanEnum.values();
        outerLoop:
        for (int i = romanSymbols.length - 1; i >= 0; i--) {
            while (a >= romanSymbols[i].value) {
                roman.append(romanSymbols[i]);
                a -= romanSymbols[i].value;
                if (a <= 0) {
                    break outerLoop;
                }
            }
        }
        return roman.toString();
    }
}
