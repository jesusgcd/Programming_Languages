/**
 * Class representing a product.
 */
public class Product {
    
    // Static variable to track the total number of instances.
    private static int counter;

    // Instance identifier of the product.
    private int id;

    // Name of the product.
    private String name;

    // Price of the product.
    private float price;

    // Quantity in stock of the product.
    private int quantityStock;  

    // Percentage offer associated with the product.
    private int offer;

    // Department to which the product belongs.
    private String department;

    // Static block to initialize the counter.
    static {
        counter = 0;
    }

    /**
     * Constructor to create a Product object with offer.
     *
     * @Input:
     * name - The name of the product.
     * price - The price of the product.
     * quantityStock - The quantity in stock of the product.
     * offer - The percentage offer associated with the product.
     * department - The department to which the product belongs.
     * @Output:
     * @return a Product object.
     */
    public Product(String name, float price, int quantityStock, int offer, String department) {
        this.id = ++counter;
        this.name = name;
        this.price = price;
        this.quantityStock = quantityStock;
        this.offer = offer;
        this.department = department;
    }

    /**
     * Constructor to create a Product object without offer.
     *
     * @Input:
     * name - The name of the product.
     * price - The price of the product.
     * quantityStock - The quantity in stock of the product.
     * department - The department to which the product belongs.
     * @Output:
     * @return a Product object.
     */
    public Product(String name, float price, int quantityStock, String department) {
        this.id = ++counter;
        this.name = name;
        this.price = price;
        this.quantityStock = quantityStock;
        this.offer = 0;
        this.department = department;
    }

    /**
     * Decreases the quantity in stock of the product by a specified amount.
     *
     * @Input:
     * quantity - The quantity in stock to be decreased.
     * @Output:
     * @return modified quantity in stock.
     */
    public void decrementStock(int quantity) {
        this.quantityStock -= quantity;
    }

    /**
     * Gets the price of the product.
     *
     * @Input:
     * @Output:
     * @return The price of the product as a floating-point value.
     */
    public float getPrice() {
        return price;
    }

    /**
     * Gets the offer percentage associated with the product.
     *
     * @Input:
     * @Output:
     * @return The product's offer percentage as an integer value.
     */
    public int getOffer() {
        return offer;
    }

    /**
     * Gets the name of the product.
     *
     * @Input:
     * @Output:
     * @return The name of the product as a string.
     */
     public String getName() {
        return name;
    }

    /**
     * Gets the department to which the product belongs.
     * @Input:
     * @Output:
     * @return The product's department as a string.
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Increases the quantity in stock of the product by a specified amount.
     *
     * @Input:
     * quantity - The quantity in stock to be increased.
     * @Output:
     * @return Prints a message indicating that the product was purchased successfully.
     */
    public void buyProduct(int quantity) {
        this.quantityStock += quantity;
        System.out.println("Product purchased successfully");
    }

    /**
     * Gets the current quantity in stock of the product.
     *
     * @Input:
     * @Output:
     * @return The quantity in stock of the product as an integer value.
     */
    public int getQuantityStock() {
        return quantityStock;
    }

    /**
     * Sets the quantity in stock of the product to a specified value.
     *
     * @Input:
     * quantityStock - The value to which the quantity in stock will be set.
     * @Output:
     * @return modified quantity in stock.
     */
    public void setQuantityStock(int quantityStock) {
        this.quantityStock += quantityStock;
    }

    /**
     * Gets the identifier of the product.
     *
     * @Input:
     * @Output:
     * @return The identifier of the product as an integer value.
     */
    public int getId() {
        return id;
    }
}
