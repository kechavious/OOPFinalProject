import java.util.*;

public class Main {
    
    //the trades list is used to store multiple trades
    private final List<Trade> trades = new ArrayList<>();
    private final ProfitLossCalculator calculator = new ProfitLossCalculator();
    
    //the stock codes list is used to store the available stock codes
    private static final List<String> STOCK_CODES = new ArrayList<>(
        Arrays.asList("AAPL", "AMZN", "GOOG", "META", "MSFT", "NVDA", "TSLA", "BA", "BRK-B", "DIS", "GE", "HD", "NIKE", "SBUX", "NFLX")
    );
   
    //the risk threshold is used to determine when to show a risk warning
    private static final double RISK_THRESHOLD = 10_000.0;

    /**
     * Entry point of the CLI stock trading system.
     *
     * @param args command-line arguments
    */
    public static void main(String[] args) {
        new Main().run();
    }

    /**
     * Runs the interactive CLI loop: menu, input handling, and dispatch.
     * Terminates when the user chooses to exit.
     */
    private void run() {
        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n=== CLI Stock Trading System ===");
                System.out.println("1. Place Trade");
                System.out.println("2. View Summary");
                System.out.println("3. Exit");
                System.out.print("Select option: ");
                String line = sc.nextLine().trim();
                int choice = parseIntOrMinusOne(line);
                switch (choice) {
                    case 1:
                        placeTrade(sc);   // UML: placeTrade()
                        break;
                    case 2:
                        viewSummary();    // UML: viewSummary()
                        break;
                    case 3:
                        { System.out.println("Thank you for using the stock trading system. Good luck with your investments!"); return; }
                    default:
                        System.out.println("Invalid option. Try again.");
                        break;
                }
            }
        }
    }
    /**
     * Displays the list of available stock codes in alphabetical order.
     * No state changes are made.
     */
    private void viewStockCodes() {
        List<String> sorted = new ArrayList<>(STOCK_CODES);
        Collections.sort(sorted);
        System.out.println("Available stock codes (sorted): " + sorted);
    }

    /**
     * Handles the "Place Trade" flow:
     * <ul>
     *     <li>Displays available stock codes</li>
     *     <li>Prompts for stock code, shares, and investment term</li>
     *     <li>Creates a trade and stores it</li>
     *     <li>Shows risk warning if threshold exceeded</li>
     *     <li>Calculates and displays profit/loss</li>
     * </ul>
     *
     * @param sc scanner for reading user input, must not be {@code null}
     */
    private void placeTrade(Scanner sc) {
        // «include» View Stock Codes
        viewStockCodes();

        System.out.print("Enter stock code: ");
        String code = sc.nextLine().trim().toUpperCase();
        if (!STOCK_CODES.contains(code)) {
            System.out.println("Unknown code. Please choose from the list.");
            return;
        }

        System.out.print("Enter shares (positive integer): ");
        int shares = parseIntOrMinusOne(sc.nextLine().trim());
        if (shares <= 0) {
            System.out.println("Invalid shares.");
            return;
        }

        System.out.print("Enter investment term (e.g., days, non-negative): ");
        int term = parseIntOrMinusOne(sc.nextLine().trim());
        if (term < 0) {
            System.out.println("Invalid term.");
            return;
        }

        Trade trade = new Trade();
        trade.buy(code, shares, term); // UML: buy(stockCode, shares, investmentTerm)
        trades.add(trade);

        // if the investment amount is greater than or equal to the risk threshold, show a risk warning
        if (trade.riskWarning(RISK_THRESHOLD)) {
            showRiskWarning(trade); // UML: showRiskWarning()
        }

        // Sequence: Main → ProfitLossCalculator → calculateProfitOrLoss
        double pl = calculator.calculateProfitOrLoss(trade);
        displayResult(pl); // UML: displayResult(pl)
    }

    /**
     * Displays all stored trades and the total profit/loss.
     */
    private void viewSummary() {
        if (trades.isEmpty()) {
            System.out.println("No trades yet.");
            return;
        }
        double total = calculator.computeTotalProfit(trades); // UML: computeTotalProfit()
        // the option: u can see al trades one by one
        System.out.println("\n--- Trades ---");
        for (Trade t : trades) {
            System.out.printf(
                "%s | shares=%d | buy=%.2f | sell=%.2f | term=%d | P/L=%.2f%n",
                t.getStockCode(), t.getShares(), t.getBuyPrice(), t.getSellPrice(),
                t.getInvestmentTerm(), t.calculateProfitOrLoss()
            );
        }
        displaySummary(total); // UML: displaySummary(total)
    }

   /**
     * Displays a warning if the investment amount exceeds the threshold.
     * @param trade the trade to check
     */
    private void showRiskWarning(Trade trade) {
    double invested = trade.getBuyPrice() * trade.getShares();
    System.out.printf(
        "⚠ Caution: This trade involves a large investment amount of %.2f, which meets the high-value threshold (≥ %.2f). Please invest wisely.%n",
        invested, RISK_THRESHOLD
    );
}
    /**
     * Displays the profit or loss of a single trade.
     * @param pl profit or loss value
     */
    private void displayResult(double pl) {
        System.out.printf("Display Trade Result → Profit/Loss: %.2f%n", pl);
    }
     /**
     * Displays the total profit/loss summary.
     * @param total total profit or loss
     */
    private void displaySummary(double total) {
        System.out.printf("\n=== Summary ===%nTotal Profit/Loss: %.2f%n", total);
    }

    /**
     * Parses a string to an integer, returning -1 if parsing fails.
     * This is used to handle user input safely.
     * @param s the string to parse
     * @return the parsed integer or -1 if parsing fails
     */
    private static int parseIntOrMinusOne(String s) {
        try { return Integer.parseInt(s); } catch (Exception e) { return -1; }
    }
}
