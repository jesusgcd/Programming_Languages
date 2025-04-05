import java.util.ArrayList;

/**
 * Class representing a shopping list for customers, implementing the Billing interface.
 */
public class LengShoppingList implements Billing {

    // Static variable to keep track of the total number of instances.
    private static int counter = 0;

    // Instance variable to store the ID of the shopping list.
    private int id;

    // ArrayList to store the purchased products.
    private ArrayList<ProductPurchased> listPurchases;

    /**
     * Constructor for creating a LengShoppingList object.
     *
     * @Intput:
     * @Output:
     * @return a LengShoppingList object.
     */
    public LengShoppingList() {
        this.id = ++counter;
        listPurchases = new ArrayList<>();
    }

    /**
     * Adds a product to the shopping list with a specified quantity.
     *
     * @Input:
     * quantity - The quantity of the product to be added.
     * product - The product to be added to the shopping list.
     * @Output:
     * @return Prints a message indicating that the product was added successfully.
     */
    public void addProduct(int quantity, Product product) {
        listPurchases.add(new ProductPurchased(product, quantity));
        System.out.println("Product added successfully");
    }

    /**
     * Calculates and returns the total amount spent on purchased items.
     *
     * @Input:
     * @Output:
     * @return the total amount spent as a float.
     */
    public float totalPurchased() {
        float total = 0.0f;
        for (ProductPurchased productPurchased : listPurchases) {
            float tempTotal = 0;
            tempTotal = productPurchased.getProduct().getPrice() * productPurchased.getQuantity();
            if (productPurchased.getProduct().getOffer() > 0) {
                tempTotal -= tempTotal * (productPurchased.getProduct().getOffer() / 100f);
            }
            total += tempTotal;
        }
        return total;
    }

    /**
     * Retrieves the ID of the shopping list.
     *
     * @Input:
     * @Output:
     * @return the ID of the shopping list as an integer.
     */
    public int getId() {
        return id;
    }

    /**
     * Retrieves the number of products in the shopping list.
     *
     * @Input:
     * @Output:
     * @return the size of the list of purchases as an integer.
     */
    public int getSize() {
        return listPurchases.size();
    }

    /**
     * Retrieves the purchased amount information for all products.
     *
     * @Input:
     * @Output:
     * @return a concatenated string of purchased amount information.
     */
    @Override
    public String purchasedAmount() {
        String sms = "";
        for (ProductPurchased productPurchased : listPurchases) {
            sms += productPurchased.purchasedAmount();
        }   
        return sms;
    }
}
