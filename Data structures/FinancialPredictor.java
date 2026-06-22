public class FinancialPredictor {
    public static double calculateFutureValue(double presentValue, double growthRate, int years) {
        if (years <= 0) {
            return presentValue;
        }
        return calculateFutureValue(presentValue, growthRate, years - 1) * (1 + growthRate);
    }

    public static void main(String[] args) {
        double principal = 10000.0;
        double rate = 0.07;
        int period = 10;

        double futureValue = calculateFutureValue(principal, rate, period);

        System.out.printf("Initial Investment: $%,.2f%n", principal);
        System.out.printf("Growth Rate: %.1f%%%n", rate * 100);
        System.out.printf("Value after %d years: $%,.2f%n", period, futureValue);
    }
}
