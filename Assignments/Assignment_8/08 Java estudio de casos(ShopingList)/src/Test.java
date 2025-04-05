/**
 * @author: Jesus Gabriel Cordero Diaz
 * @carnet:  2020081049
 * @date: 31/08/2023
 */

public class Test {
    

    public static void main(String[] args) {
        
        // create products (String name, float price, int quantityStock, int offer, String department)
        Product product1 = new Product("Milk", 1.5f, 100, 3, "Dairy");
        Product product2 = new Product("Eggs", 0.12f, 200, 4, "Dairy");
        Product product3 = new Product("Bread", 0.8f, 150, 56, "Bakery");
        Product product4 = new Product("Butter", 1.2f, 100, 9, "Dairy");
        Product product5 = new Product("Cheese", 2.5f, 100, "Dairy");
        Product product6 = new Product("Ham", 2.5f, 100, 0, "Butcher");
        Product product7 = new Product("Chicken", 3.5f, 100, 0, "Butcher");
        Product product8 = new Product("Beef", 5.5f, 100, "Butcher");
        Product product9 = new Product("Pork", 4.5f, 100, "Butcher");
        Product product10 = new Product("Fish", 3.5f, 100, "Butcher");


        // create employees (int cedula, String firstName, String lastName, String address, String department)
        Employee employee1 = new Employee(123456789, "Jose", "CD", "Street 1", "Dairy");
    
        // create customers (int cedula, String firstName, String lastName, String address)
        Customer customer1 = new Customer(123456789, "John", "Smith", "Street 1");
        Customer customer2 = new Customer(987654321, "Jane", "Smith", "Street 2");
        
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + "\n");

        // show products info
        String[] productInfo = {product1.getName(), String.valueOf(product1.getPrice()), String.valueOf(product1.getQuantityStock()), String.valueOf(product1.getOffer()), 
            product1.getDepartment()};
        System.out.println("Product: " + String.join(", ", productInfo));
    
        System.out.println("\n" + "===============================" + "\n");

        // show employees info
        String[] employeeInfo = {String.valueOf(employee1.getCedula()), employee1.getFirstName(), employee1.getLastName(), employee1.getAddress(), employee1.getDepartment()};
        System.out.println("Employee: " + String.join(", ", employeeInfo));

        System.out.println("\n" + "===============================" + "\n");

        // show customer info
        System.out.println(customer1.toString());
        System.out.println(customer2.toString());

        System.out.println("\n" + "===============================" + "\n");
        
        // show customers empty shopping list
        customer1.empty();
        customer2.empty();

        System.out.println("\n" + "===============================" + "\n");

        // show quantity of products in shopping list
        customer1.countProduct();
        customer2.countProduct();

        System.out.println("\n" + "===============================" + "\n");

        // show total purchased
        customer1.totalPurchased();
        customer2.totalPurchased();

        System.out.println("\n" + "===============================" + "\n");

        // display shopping list
        customer1.displayList();
        customer2.displayList();

        System.out.println("\n" + "===============================" + "\n");

        //actual stock
        System.out.println("actual stock: " + product1.getQuantityStock() + " name: " + product1.getName());
        System.out.println("actual stock: " + product2.getQuantityStock() + " name: " + product2.getName());
        System.out.println("actual stock: " + product3.getQuantityStock() + " name: " + product3.getName());
        System.out.println("actual stock: " + product4.getQuantityStock() + " name: " + product4.getName());
        System.out.println("actual stock: " + product5.getQuantityStock() + " name: " + product5.getName());
        System.out.println("actual stock: " + product6.getQuantityStock() + " name: " + product6.getName());
        System.out.println("actual stock: " + product7.getQuantityStock() + " name: " + product7.getName());
        System.out.println("actual stock: " + product8.getQuantityStock() + " name: " + product8.getName());
        System.out.println("actual stock: " + product9.getQuantityStock() + " name: " + product9.getName());
        System.out.println("actual stock: " + product10.getQuantityStock() + " name: " + product10.getName());



        System.out.println("\n" + "===============================" + "\n");

        // customer 1 add products to shopping list
        customer1.addProduct(20, product1);
        customer1.addProduct(23, product2);
        customer1.addProduct(31, product3);
        customer1.addProduct(25, product4);
        customer1.addProduct(11, product5);

        System.out.println("\n" + "===============================" + "\n");

        // customer 2 add products to shopping list
        customer2.addProduct(22, product6);
        customer2.addProduct(63, product7);
        customer2.addProduct(15, product8);
        customer2.addProduct(12, product9);
        customer2.addProduct(1, product10);

        System.out.println("\n" + "===============================" + "\n");

        // stock after purchase
        System.out.println("actual stock: " + product1.getQuantityStock() + " name: " + product1.getName());
        System.out.println("actual stock: " + product2.getQuantityStock() + " name: " + product2.getName());
        System.out.println("actual stock: " + product3.getQuantityStock() + " name: " + product3.getName());
        System.out.println("actual stock: " + product4.getQuantityStock() + " name: " + product4.getName());
        System.out.println("actual stock: " + product5.getQuantityStock() + " name: " + product5.getName());
        System.out.println("actual stock: " + product6.getQuantityStock() + " name: " + product6.getName());
        System.out.println("actual stock: " + product7.getQuantityStock() + " name: " + product7.getName());
        System.out.println("actual stock: " + product8.getQuantityStock() + " name: " + product8.getName());
        System.out.println("actual stock: " + product9.getQuantityStock() + " name: " + product9.getName());
        System.out.println("actual stock: " + product10.getQuantityStock() + " name: " + product10.getName());

        System.out.println("\n" + "===============================" + "\n");

        // show quantity of products in shopping list
        customer1.countProduct();
        customer2.countProduct();

        System.out.println("\n" + "===============================" + "\n");

        // show total purchased
        customer1.totalPurchased();
        customer2.totalPurchased();

        System.out.println("\n" + "===============================" + "\n");

        // display shopping list
        customer1.displayList();
        customer2.displayList();

        System.out.println("\n" + "===============================" + "\n");

        // show amount of customers
        Customer.getAmountCustomers();

        System.out.println("\n" + "===============================" + "\n");

        // actualize stock

        System.out.println("actual stock: " + product1.getQuantityStock() + " name: " + product1.getName());
        employee1.addProductStock(product1, 100);



        System.out.println("\n" + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + "\n");
    }
}
