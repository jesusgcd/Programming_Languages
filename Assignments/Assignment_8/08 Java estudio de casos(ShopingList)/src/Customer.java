/**
 * Class representing a customer, extending User and implementing Billing interface.
 */
public class Customer extends User implements Billing {

    // Static variable to keep track of the total number of customers.
    private static int amountCustomers = 0;

    // Instance variable to store the shopping list of the customer.
    private LengShoppingList lengClient;

    /**
     * Constructor for creating a Customer object.
     *
     * @Input:
     * cedula - The identification number of the              customer.
     * firstName - The first name of the customer.
     * lastName - The last name of the customer.
     * address - The address of the customer.
     * @Output:
     * @return a Customer object.
     */
    public Customer(int cedula, String firstName, String lastName, String address) {
        super(cedula, firstName, lastName, address);
        amountCustomers++;
        this.lengClient = new 
        LengShoppingList(
            
        );
    }

    /**
     * Checks if the customer's shopping list is empty.
     *
     * @Input:
     * @Output:
     * @return Prints whether the list is empty or not.
     */
    public void empty() {
        int size = lengClient.getSize();
        System.out.print("The list of " + getFirstName() + " " + getLastName() );
        if (size == 0) {
            System.out.println(" is empty");
        } else {
            System.out.println(" is not empty");
        }
    }

    /**
     * Counts the number of products in the customer's shopping list.
     *
     * @Input:
     * @Output:
     * @return Prints the total number of products in the list.
     */
    public void countProduct() {
        int size = lengClient.getSize();
        System.out.println("Total products: " + size + " of " + getFirstName() + " " + getLastName() + "\n");
    }

    /**
     * Calculates and prints the total value of the purchased items in the customer's shopping list.
     *
     * @Input:
     * @Output:
     * @return Prints the total purchased amount.
     */
    public void totalPurchased() {
        float total = lengClient.totalPurchased();
        System.out.println("Total purchased: " + total + " of " + getFirstName() + " " + getLastName() + "\n");
    }

    /**
     * Displays the customer's shopping list.
     *
     * @Input:
     * @Output:
     * @return Prints the list of products.
     */
    public void displayList() {
        String sms = "";
        sms += purchasedAmount();
        if (sms.equals("")) {
            sms = "The list of " + getFirstName() + " " + getLastName() + " is empty";
        } else {
            sms = "The list of " + getFirstName() + " " + getLastName() + " is: \n" + sms;
        }
        System.out.println(sms);
    }

    /**
     * Retrieves the total number of customers.
     *
     * 
     * @Input:
     * @Output:
     * @return Prints the total number of customers.
     */
    public static void getAmountCustomers() {
        System.out.println("Total customers: " + amountCustomers);
    }

    /**
     * Adds a product to the customer's shopping list.
     *
     * @Input:
     * quantity - The quantity of the product to be added.
     * product - The product to be added to the shopping list.
     * @Output:
     * @return Prints a confirmation message.
     */
    public void addProduct(int quantity, Product product) {
        lengClient.addProduct(quantity, product);
        System.out.println("Product added successfully");
    }

    /**
     * Calculates the purchased amount by summing up the values of items in the shopping list.
     *
     * @Input:
     * @Output:
     * @return a string representation of the purchased amount.
     */
    @Override
    public String purchasedAmount() {
        String sms = "";
        sms += lengClient.purchasedAmount();
        return sms;
    }

    /**
     * Generates a string representation of the customer's information.
     *
     * @Input:
     * @Output:
     * @return a string representation of the customer.
     */
    @Override
    public String toString() {
        String sms ="";
        sms += "Customer: " + getFirstName() + " " + getLastName() + "\n" +
                "Cedula: " + getCedula() + "\n" +
                "Address: " + getAddress() + "\n";
        return sms;
    }
}
