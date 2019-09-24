package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class BuyAndSellStock {
    @EpiTest(testDataFile = "buy_and_sell_stock.tsv")
    public static double computeMaxProfit(List<Double> prices) {
        double maxProfit = 0;
        double buyValue = -1;

        for (int i = 0; i < prices.size(); i++) {
            if (buyValue == -1 || prices.get(i) < buyValue) {
                buyValue = prices.get(i);
            } else {
                maxProfit = Math.max(maxProfit, prices.get(i) - buyValue);
            }
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "BuyAndSellStock.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
