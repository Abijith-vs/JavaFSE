public class SingletonTest {
    public static void main(String[] args) {

        logger log1 = logger.getInstance();
        logger log2 = logger.getInstance();

        log1.log("First message");
        log2.log("Second message");

        // Document wants you to VERIFY only one instance exists
        if (log1 == log2) {
            System.out.println("✅ Same instance — Singleton works!");
        } else {
            System.out.println("❌ Different instances — Singleton broken!");
        }
    }
}
