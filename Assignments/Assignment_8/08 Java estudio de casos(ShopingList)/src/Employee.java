/**
 * Class representing an employee, extending User.
 */
public class Employee extyends User {

    // Static variable to keep track of the total number of employees.
    private static int amountEmployees = 0;

    // Instance variable to store the department of the employee.
    private String department;

    /**
     * Constructor for creating an Employee object.
     *
     * @Input:
     * cedula - The identification number of the employee.
     * firstName - The first name of the employee.
     * lastName - The last name of the employee.
     * address - The address of the emploee.
     * department - The department in which the employee works.
     * @Output:
     * @return an Employee object.
     */
    public Employee(int cedula, String firstName, String lastName, String address, String department) {
        super(cedula, firstName, lastName, address);
        this.department = department;
        amountEmployees++;
    }

    /**
     * Retrieves the total number of employees.
     *
     * @Input:
     * @Output:
     * @return Prints the total number of employees.
     */
    public static void getAmountEmployees() {
        System.out.println("Total employees: " + amountEmployees);
    }

    /**
     * Retrieves the department of the employee.
     *
     * @Input:
     * @Output:
     * @return the department of the employee as a string.
     */
    public String getDepartment() {
        return this.department;
    }

    /**
     * Adds a specified quantity of a product to the stock.
     *
     * @Input:
     * product - The product to be added to the stock.
     * quantity - The quantity of the product to be added.
     * @Output:
     * @return Prints the product name, quantity added and total stock.
     */
    public void addProductStock(Product product, int quantity) {
        product.setQuantityStock(product.getQuantityStock() + quantity);
        System.out.println("Product: " + product.getName() + " added to stock"  +
                "Quantity: " + quantity  +
                " Total stock: " + product.getQuantityStock() + "\n");
    }

    /**
     * Generates a string representation of the employee's information.
     *
     * @Input:
     * @Output:
     * @return a string representation of the employee.
     */
    @Override
    public String toString() {
        String sms = "Employee: " + getFirstName() + " " + getLastName() + "\n" +
                "Cedula: " + getCedula() + "\n" +
                "Address: " + getAddress() + "\n";
        return sms;
    }
}
