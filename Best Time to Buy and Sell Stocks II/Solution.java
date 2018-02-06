public class Solution {
    // DO NOT MODIFY THE LIST. IT IS READ ONLY
    public int maxProfit(final List<Integer> stockPrices) {
        if (stockPrices == null || stockPrices.size() <= 1) {
            return 0;
        }
        int maxProfit = 0;
        for (int i = 1; i < stockPrices.size(); i++) {
            int difference = stockPrices.get(i) - stockPrices.get(i - 1);
            if (difference > 0) {
                maxProfit += difference;
            }
        }
        return maxProfit;
    }
}
