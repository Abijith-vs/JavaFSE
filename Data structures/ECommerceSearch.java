import java.util.Arrays;
import java.util.Comparator;

class Product {
    private final String productId;
    private final String productName;
    private final String category;

    public Product(String productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Product [ID=" + productId + ", Name=" + productName + ", Category=" + category + "]";
    }
}

public class ECommerceSearch {

    public static Product binarySearch(Product[] products, String targetId) {
        int low = 0;
        int high = products.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comparison = products[mid].getProductId().compareTo(targetId);

            if (comparison == 0) {
                return products[mid];
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Product[] inventory = {
            new Product("P105", "Wireless Mouse", "Electronics"),
            new Product("P101", "Gaming Laptop", "Electronics"),
            new Product("P104", "Mechanical Keyboard", "Electronics"),
            new Product("P102", "Smart Watch", "Wearables"),
            new Product("P103", "Noise Cancelling Headphones", "Audio")
        };

        Arrays.sort(inventory, Comparator.comparing(Product::getProductId));

        System.out.println("--- Sorted Inventory Table ---");
        for (Product p : inventory) {
            System.out.println(p);
        }

        String searchTarget1 = "P103";
        System.out.println("\nSearching for Product ID: " + searchTarget1);
        Product result1 = binarySearch(inventory, searchTarget1);
        
        if (result1 != null) {
            System.out.println("Match Found! -> " + result1);
        } else {
            System.out.println("Product ID " + searchTarget1 + " does not exist.");
        }

        String searchTarget2 = "P999";
        System.out.println("\nSearching for Product ID: " + searchTarget2);
        Product result2 = binarySearch(inventory, searchTarget2);
        
        if (result2 != null) {
            System.out.println("Match Found! -> " + result2);
        } else {
            System.out.println("Product ID " + searchTarget2 + " does not exist.");
        }
    }
}
