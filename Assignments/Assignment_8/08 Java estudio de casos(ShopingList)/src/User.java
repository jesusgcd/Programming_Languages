/**
 * Abstract class representing a user.
 */
public abstract class User {
    
    // The national ID (cedula) of the user.
    private int cedula;

    // The first name of the user.
    private String firstName;

    // The last name of the user.
    private String lastName;

    // The address of the user.
    private String address;

    /**
     * Constructor to create a User object.
     *
     * @Input:
     * cedula - The national ID (cedula) of the user.
     * firstName - The first name of the user.
     * lastName - The last name of the user.
     * address - The address of the user.
     * @Output:
     * @return a User object.
     */
    public User(int cedula, String firstName, String lastName, String address) {
        this.cedula = cedula;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    /**
     * Abstract method to provide a string representation of the user.
     *
     * @Input:
     * @Output:
     * @return A string representation of the user.
     */
    public abstract String toString();

    /**
     * Gets the national ID (cedula) of the user.
     *
     * @Input:
     * @Output:
     * @return The national ID (cedula) of the user as an integer value.
     */
    public int getCedula() {
        return cedula;
    }

    /**
     * Gets the first name of the user.
     *
     * @Input:
     * @Output:
     * @return The first name of the user as a string.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the last name of the user.
     *
     * @Input:
     * @Output:
     * @return The last name of the user as a string.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets the address of the user.
     *
     * @Input:
     * @Output:
     * @return The address of the user as a string.
     */
    public String getAddress(){
        return address;
    }
}
