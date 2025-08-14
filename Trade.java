import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
/**
 * Trade
 * this class represents a single stock trade, including stock code,
 * number of shares, buy/sell prices, investment term, and simulated daily prices.
 */
public class Trade {
    /** the stock code for this trade */
    private String stockCode;
    /** the number of shares traded */
    private int shares;
    /** the price at which the stock was bought */
    private double buyPrice;
    /** the price at which the stock was sold */
    private double sellPrice;
    /** the investment duration in days */
    private int investmentTerm; 
    /** the stock price on the last simulated day */
    private double lastDayPrice;

    /**
     * Creates an empty trade. Initialize it by calling {@link #buy(String, int, int)}.
     */
    public Trade() {
        // this.stockCode = Objects.requireNonNull(stockCode, "stockCode cannot be null");
        // if (shares <= 0) throw new IllegalArgumentException("shares must be > 0");
        // this.shares = shares;
        // this.investmentTerm = Math.max(0, investmentTerm);
        // this.buyPrice = generateBuyPrice();
        // for (int i = 0; i < investmentTerm; i++) {
        //     this.sellPrice = generateNextPrice();
        //     System.out.println(sellPrice);
        // }
    }
    /**
     * Initializes this trade with the given stock code, share count, and term.
     * Generates a random buy price and simulates daily prices to produce a sell price.
     * If term is {@code 0}, sell price equals buy price.
     *
     * @param stockCode the stock ticker; must not be {@code null}
     * @param shares    number of shares; must be {@code > 0}
     * @param investmentTerm number of simulated days; if negative, treated as {@code 0}
     * @throws NullPointerException     if {@code stockCode} is {@code null}
     * @throws IllegalArgumentException if {@code shares <= 0}
    */
    public void buy(String stockCode, int shares, int investmentTerm) {
        this.stockCode = Objects.requireNonNull(stockCode, "stockCode cannot be null");
        if (shares <= 0) throw new IllegalArgumentException("shares must be > 0");
        this.shares = shares;
        this.investmentTerm = Math.max(0, investmentTerm);
        this.buyPrice = generateBuyPrice();

        if (investmentTerm == 0) {
            this.sellPrice = this.buyPrice;

        } else {
            for (int i = 0; i < investmentTerm; i++) {
                generateNextPrice();
                this.sellPrice = this.lastDayPrice;
            }
        }
    }
    
    /**
     * generateBuyPrice
     * generates a random buy price between 50 and 150, rounded to two decimal places.
     * also updates the lastDayPrice to the generated buy price.
     *
     * @return the generated buy price
     */
    private double generateBuyPrice() {
        double buy = Math.round((50 + Math.random() * 100) * 100.0) / 100.0;
        this.lastDayPrice = buy; 
        return buy;
    }

    /**
     * generateNextPrice
     * simulates the next day's stock price with a 50% chance of increasing or decreasing.
     * the price change is up to ±15% of the last day's price, rounded to two decimal places.
     * updates lastDayPrice accordingly.
     */
    private void generateNextPrice() {
        
        double p = 0.5;
        double winOrLose = ThreadLocalRandom.current().nextDouble() < p ? 1 : 0;

        this.lastDayPrice = winOrLose == 1 ?
        Math.round((this.lastDayPrice + (Math.random() * 0.15) * this.lastDayPrice) * 100.0) / 100.0: 
        Math.round((this.lastDayPrice - (Math.random() * 0.15) * this.lastDayPrice) * 100.0) / 100.0;

        System.out.println(this.lastDayPrice);
                 
    }
    
    /**
     * riskWarning
     * checks if the investment amount (buyPrice × shares) meets or exceeds a threshold.
     *
     * @param threshold the threshold value to compare against
     * @return true if investment amount ≥ threshold, otherwise false
     */
    public boolean riskWarning(double threshold) {
        return buyPrice * shares >= threshold;
    }

    /**
     * viewStockCode
     * displays the stock code of this trade to the standard output.
     */
    public void viewStockCode() {
        System.out.println("Stock Code: " + stockCode);
    }

    /**
     * calculateProfitOrLoss
     * calculates the profit or loss of this trade.
     * computed as (sellPrice − buyPrice) × shares.
     *
     * @return the profit or loss value
     */
    public double calculateProfitOrLoss() {
        return (sellPrice - buyPrice) * shares;
    }
    /**
     * getStockCode
     * returns the stock code of this trade.
     *
     * @return the stock code
     */
    public String getStockCode() { return stockCode; }
    /**
     * getShares
     * returns the number of shares in this trade.
     *
     * @return the number of shares
     */
    public int getShares() { return shares; }
    /**
     * getBuyPrice
     * returns the buy price of this trade.
     *
     * @return the buy price
     */
    public double getBuyPrice() { return buyPrice; }
    /**
     * getSellPrice
     * returns the sell price of this trade.
     *
     * @return the sell price
     */
    public double getSellPrice() { return sellPrice; }
    /**
     * getInvestmentTerm
     * returns the investment term (in days) for this trade.
     *
     * @return the investment term in days
     */
    public int getInvestmentTerm() { return investmentTerm; }
}
