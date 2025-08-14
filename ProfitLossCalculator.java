import java.util.List;

public class ProfitLossCalculator {
    // UML: ProfitLossCalculator

    /** the buy price of the last trade */
    private double buyPrice;         
    /** the sell price of the last trade */ 
    private double sellPrice;         
    /** the total amount spent on buying shares */
    private double totalBoughtAmount; 
    /** the total amount received from selling shares */
    private double totalSoldAmount;   
    /** the total profit or loss from all trades */
    private double totalProfit; 

    /**
     * calculateProfitOrLoss
     * this method calculates the profit or loss for a single trade
     * it takes a Trade object as input and returns the profit or loss amount
     * it uses the Trade's buyPrice and sellPrice to calculate the profit or loss
     * if the trade is null, it returns 0.0
     * @param trade the Trade object containing buy and sell prices
     * @return the profit
      */ 
    public double calculateProfitOrLoss(Trade trade) {
        if (trade == null) return 0.0;
        this.buyPrice  = trade.getBuyPrice();
        this.sellPrice = trade.getSellPrice();
        return trade.calculateProfitOrLoss();
    }

    /**
     * computeTotalProfit
     * this method computes the total profit or loss from a list of trades
     * it iterates through each trade, calculates the bought and sold amounts,
     * and updates the total profit accordingly
     * it also updates the buyPrice and sellPrice for the last trade in the list
     * if the trades list is null, it returns 0.0
     * @param trades a list of Trade objects
     * @return the total profit or loss from all trades
     */
    public double computeTotalProfit(List<Trade> trades) {
        totalBoughtAmount = 0.0;
        totalSoldAmount   = 0.0;
        totalProfit       = 0.0;
        if (trades == null) return 0.0;
        for (Trade t : trades) {
            double bought = t.getBuyPrice()  * t.getShares();
            double sold   = t.getSellPrice() * t.getShares();
            totalBoughtAmount += bought;
            totalSoldAmount   += sold;
            totalProfit       += (sold - bought);
            /* update the buyPrice and sellPrice for the last trade
            this is useful for displaying the last trade's buy and sell prices
            */ 
            buyPrice  = t.getBuyPrice();
            sellPrice = t.getSellPrice();
        }
        return totalProfit;
    }
     /**
     * getBuyPrice
     * this method returns the buy price of the last processed trade.
     *
     * @return the buy price
     */
    public double getBuyPrice() { return buyPrice; }
    /**
     * getSellPrice
     * this method returns the sell price of the last processed trade.
     *
     * @return the sell price
     */
    public double getSellPrice() { return sellPrice; }
    /**
     * getTotalBoughtAmount
     * this method returns the total amount spent on buying shares across all trades.
     *
     * @return the total bought amount
     */
    public double getTotalBoughtAmount() { return totalBoughtAmount; }
    /**
     * getTotalSoldAmount
     * this method returns the total amount received from selling shares across all trades.
     *
     * @return the total sold amount
     */
    public double getTotalSoldAmount() { return totalSoldAmount; }
    /**
     * getTotalProfit
     * this method returns the total profit or loss from all processed trades.
     *
     * @return the total profit or loss
     */
    public double getTotalProfit() { return totalProfit; }
}

