/**
 * Class representing a purchased product.
 */
public class ProductPurchased implements Billing {
    
    // Static variable to track the total number of instances.
    private static int counter = 0;

    // Instance identifier of the purchased product.
    private int id;

    // The product that was purchased.
    private Product product;

    // The quantity of the product that was purchased.
    private int quantity;

    /**
     * Constructor to create a ProductPurchased object.
     *
     * @Input:
     * product - The product that was purchased.
     * quantity - The quantity of the product that was purchased.
     * @Output:
     * @return a ProductPurchased object.
     */
    public ProductPurchased(Product product, int quantity) {
        this.id = ++counter;
        this.product = product;
        this.quantity = quantity;
        
        // Reduce the stock of the purchased product.
        this.product.decrementStock(quantity);
    }

    /**
     * Gets the identifier of the purchased product.
     *
     * @Input:
     * @Output:
     * @return The identifier of the purchased product as an integer value.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the product that was purchased.
     *
     * @Input:
     * @Output:
     * @return The purchased product.
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Gets the quantity of the product that was purchased.
     *
     * @Input:
     * @Output:
     * @return The quantity of the purchased product as an integer value.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Generates a description of the purchased product's amount.
     *
     * @Input:
     * @Output:
     * @return A formatted string containing the name of the product and the purchased quantity.
     */
    @Override
    public String purchasedAmount() {
        String sms = "Name: " + product.getName() + ", quantity: " + quantity + "\n";
        return sms;
    }
}
