import java.util.ArrayList;
import java.util.Scanner;

class Customer {
    String name;
    String email;
    String phoneNumber;
    String address;

    public Customer() {
        // empty
    }

    public Customer(String name, String email, String phoneNumber, String address) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

class Order {
    ArrayList<Product> cartItems = new ArrayList<>();
    float payableTotal = 0;
    String paymentMethod;
    String paymentAccountNumber;
    String paymentAccountName;
    String shippingMethod;

    public void addToCart(Product product) {
        this.payableTotal += product.getUnitPrice() * product.getQuantity();
        cartItems.add(product);
    }

    public ArrayList<Product> getCartList() {
        return this.cartItems;
    }

    public float getPayableTotal() {
        return payableTotal;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(int paymentMethod) {
        switch (paymentMethod) {
            case 1 -> this.paymentMethod = "cod";
            case 2 -> this.paymentMethod = "gcash";
            case 3 -> this.paymentMethod = "maya";
            case 4 -> this.paymentMethod = "bank (mastercard, visa)";
            default -> this.paymentMethod = "n/a";
        }
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(int shippingMethod) {
        switch (shippingMethod) {
            case 1 -> this.shippingMethod = "door to door";
            case 2 -> this.shippingMethod = "pick-up from store";
            default -> this.shippingMethod = "invalid entry!";
        }
    }

    public String getPaymentAccountNumber() {
        return paymentAccountNumber;
    }

    public void setPaymentAccountNumber(String paymentAccountNumber) {
        this.paymentAccountNumber = paymentAccountNumber;
    }

    public String getPaymentAccountName() {
        return paymentAccountName;
    }

    public void setPaymentAccountName(String paymentAccountName) {
        this.paymentAccountName = paymentAccountName;
    }
}

class Inventory {
    ArrayList<Product> products = new ArrayList<>();

    public ArrayList<Product> getInventory() {
        return this.products;
    }

    public Product getItem(int id) {
        Product foundItem = null;
        for (Product item : products) {
            if (item.id == id) {
                foundItem = item;
                break;
            }
        }
        return foundItem;
    }

    public void setProduct(Product product) {
        this.products.add(product);
    }
}

class Product {
    static int ctr = 1;
    int id;
    String productName;
    float unitPrice;
    int quantity;

    public Product() {
    }

    public Product(String productName, float unitPrice, int quantity) {
        this.id = ctr++;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

class Main {
    private static Inventory inventory = new Inventory();
    private static Order order = new Order();
    private static Customer customer = new Customer();

    static void InitializeProductList() {
        inventory.setProduct(new Product(": croissant", 150.00f, 12));
        inventory.setProduct(new Product(": millefeuille", 300.00f, 25));
        inventory.setProduct(new Product(": kouign amann", 200.00f, 20));
        inventory.setProduct(new Product(": pain au chocolat", 100.00f, 9));
        inventory.setProduct(new Product(": profiteroles (set of 3)", 500.00f, 14));
        inventory.setProduct(new Product(": madeleines (set of 4)", 400.00f, 10));
        inventory.setProduct(new Product(": macarons (set of 6)", 800.00f, 7));
    }

    static void clearScreen() {
        System.out.flush();
    }

    static void renderMenu() {
        System.out.println("─*───────*───────*─ (^ w ^)/ ─*────────*────────*─\n");
        System.out.println("  pastries, pastries, and pastries! online store\n");
        System.out.println("─*───────*───────*─ (^ w ^)/ ─*────────*────────*─\n");
        System.out.println("\t1. choose your pastry!");
        System.out.println("\t2. inventory!");
        System.out.println("\t3. exit!\n");
        System.out.print("\tenter number: ");
    }

    static void displayInventory() {
        System.out.println("\tproduct list:");
        for (Product item : inventory.getInventory()) {
            System.out.println("\t" + item.getId() + "" + item.getProductName() +
                    " - price: " + item.getUnitPrice() + ", stock: " + item.getQuantity());
        }
    }

    static void updateInventory(Scanner scanner) {
        scanner.nextLine();
        System.out.println("────*────*────*────*────*────\n");
        System.out.println("    update product list!\n");
        System.out.println("────*────*────*────*────*────\n");
        System.out.print("\tchoose product id, 1 to 7: ");

        int productToEdit = scanner.nextInt();

        Product foundProduct = null;

        for (Product product : inventory.getInventory()) {
            if (product.getId() == productToEdit) {
                foundProduct = product;
                break;
            }

        }

        if (foundProduct != null) {
            System.out.print("\titem found!\n\n");
            System.out.print("\tnew product name: ");
            scanner.nextLine();
            String newName = scanner.nextLine();
            System.out.print("\tnew unit price: ");
            float newPrice = scanner.nextFloat();
            System.out.print("\tnew stock quantity: ");
            int newQty = scanner.nextInt();

            foundProduct.setProductName(": " + newName);
            foundProduct.setUnitPrice(newPrice);
            foundProduct.setQuantity(newQty);

            System.out.println("\n\tproduct updated successfully!\n");
        } else {
            System.out.println("\n\tproduct not found!\n");
        }
    }

    static void displayCartItems() {
        System.out.println("\tItems in Cart:");
        for (Product item : order.getCartList()) {
            System.out.println("\t[x] " + item.getProductName() + " - unit price: " +
                    item.getUnitPrice() + ", qty: " + item.getQuantity() + ", total: " +
                    (item.getUnitPrice() * item.getQuantity()));
        }
        System.out.println("\ttotal payable: " + order.getPayableTotal());
    }

    static void inputCustomerInformation(Scanner scanner) {
        scanner.nextLine();
        System.out.println("────*────*────*────*────*────\n");
        System.out.println("      delivery details!\n");
        System.out.println("────*────*────*────*────*────\n");

        System.out.print("\trecipient name: ");
        customer.setName(scanner.nextLine());
        System.out.print("\trecipient email: ");
        customer.setEmail(scanner.nextLine());
        System.out.print("\trecipient contact no.: ");
        customer.setPhoneNumber(scanner.nextLine());
        System.out.print("\tdelivery address: ");
        customer.setAddress(scanner.nextLine());

        System.out.print("\tyour payment method:\n");
        System.out.print("\t1. cod\n\t2. gcash\n\t3. maya\n\t4. bank\n\tenter method number: ");
        int paymentChoice = scanner.nextInt();
        order.setPaymentMethod(paymentChoice);
        scanner.nextLine();

        switch (paymentChoice) {
            case 1 -> {
                order.setPaymentAccountNumber("n/a");
                order.setPaymentAccountName("n/a");
            }
            default -> {
                System.out.print("\n\taccount number: ");
                order.setPaymentAccountNumber(scanner.nextLine());
                System.out.print("\n\taccount name: ");
                order.setPaymentAccountName(scanner.nextLine());
            }
        }

        System.out.print("\tchoose shipping method:\n");
        System.out.print("\t1. door to door\n\t2. in-store pick up\n\tenter number: ");
        order.setShippingMethod(scanner.nextInt());
    }

    static char displayConfirmation(Scanner scanner) {
        clearScreen();
        System.out.println("────*────*────*────*────*────\n");
        System.out.println("\tconfirm your order!\n");
        System.out.println("────*────*────*────*────*────\n");

        System.out.println("\tname: " + customer.getName());
        System.out.println("\temail: " + customer.getEmail());
        System.out.println("\tcontact no.: " + customer.getPhoneNumber());
        System.out.println("\taddress: " + customer.getAddress() + "\n");

        displayCartItems();

        System.out.println("\n\tpayment method: " + order.getPaymentMethod());
        System.out.println("\taccount no.: " + order.getPaymentAccountNumber());
        System.out.println("\taccount name: " + order.getPaymentAccountName());
        System.out.println("\tshipping method: " + order.getShippingMethod());

        System.out.print("\nconfirm order? enter (y/n): ");
        char confirm = scanner.next().charAt(0);

        switch (Character.toLowerCase(confirm)) {
            case 'y' -> {
                System.out.println("\nprocessing...");
                System.out.println("\tyour pastries are on the way!");
            }
            case 'n' -> System.out.println("\norder cancelled.");
            default -> System.out.println("\ninvalid! cancelling...");
        }

        return confirm;
    }

    static void addItemToCart(Scanner scanner) {
        char moreChoice;
        do {
            clearScreen();
            System.out.println("────*────*────*────*────*────\n");
            System.out.println("what would you like to order?\n");
            System.out.println("────*────*────*────*────*────\n");

            if (!order.getCartList().isEmpty()) {
                displayCartItems();
                System.out.println("\n────*────*────*────*────*────\n");
            }

            displayInventory();

            System.out.print("\nenter pastry number: ");
            int prodChoice = scanner.nextInt();
            System.out.print("how many?: ");
            int qty = scanner.nextInt();

            Product selected = inventory.getItem(prodChoice);
            Product newItem = new Product(selected.getProductName(), selected.getUnitPrice(), qty);
            order.addToCart(newItem);

            System.out.print("add more? (y/n): ");
            moreChoice = scanner.next().charAt(0);

        } while (Character.toLowerCase(moreChoice) == 'y');
    }

    static void clearOrderDetails() {
        order = new Order();
        customer = new Customer();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InitializeProductList();

        int choice;
        do {
            renderMenu();
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    char confirm;
                    do {
                        addItemToCart(scanner);
                        clearScreen();
                        inputCustomerInformation(scanner);
                        confirm = displayConfirmation(scanner);
                        clearOrderDetails();
                    } while (Character.toLowerCase(confirm) == 'n');
                }
                case 2 -> {
                    clearScreen();
                    System.out.println("inventory selected!\nenter password:");
                    String input = scanner.next();
                    if (new String("staff123").equals(input)) {
                        clearScreen();
                        updateInventory(scanner);
                        displayInventory();
                    } else {
                        clearScreen();
                        System.out.println("access denied!\n");
                        System.out.println("returning to main...\n");
                        System.out.flush();
                    }
                }
                case 3 -> System.out.println("goodbye...\n");
                default -> System.out.println("entry invalid!\n");
            }

        } while (choice != 3);

        scanner.close();
    }
}
