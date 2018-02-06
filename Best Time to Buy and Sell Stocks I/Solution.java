public class Solution {
    // DO NOT MODIFY THE LIST. IT IS READ ONLY
    public int maxProfit(final List<Integer> stockPrices) {
        if (stockPrices == null || stockPrices.size() <= 1) {
            return 0;
        }
        int minPriceSoFar = stockPrices.get(0);
        int maxProfit = Integer.MIN_VALUE;
        for (int i = 1; i < stockPrices.size(); i++) {
            maxProfit = Math.max(maxProfit, stockPrices.get(i) - minPriceSoFar);
            minPriceSoFar = Math.min(minPriceSoFar, stockPrices.get(i));
        }
        return maxProfit < 0 ? 0 : maxProfit;
    }
}
