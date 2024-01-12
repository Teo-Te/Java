public class App {
    public static void main(String[] args) throws Exception {
       // I couldn't completely understand the cashier part so 
       // I just skipped it, the order details are displayed even without the cashier part
       
       // Create a PetStore instance
       PetStore petStore = new PetStore();

       // Create a Manager instance
       Manager manager = new Manager("Arteo", "Fejzo", "arteo", "1234",
        "Gramsh", "0691234567", 1234, 6666);
       // As per the input validations, They were only made for the Manager class
       // Same validations could be made for other classes but I didn't do it
       // Because I thought for the task it was enough

        manager.setBirdDiscount(10);
        manager.setCatDiscount(20);
        manager.setDogDiscount(30);
       // Set the manager for the PetStore
       petStore.addManager(manager);

       Manager manager2 = new Manager("Teo", "Fejzo", "teo", "1234",
        "Gramsh", "0691234567", 1234, 6666);

        manager2.setBirdDiscount(30);
        manager2.setCatDiscount(10);
        manager2.setDogDiscount(70);
       // Set another manager for the PetStore
       petStore.addManager(manager2);

       // Create a SalesOrder instance
       SalesOrder salesOrder = new SalesOrder();
       SalesOrder salesOrder2 = new SalesOrder();

       // Create customers
       Customer customer = new LoyalCustomer("Filan", "Fisteku", "hellobye", "1234",
        "Tirana", "0691234567", "LoyalCustomer");
       salesOrder.setCustomer(customer);

       Customer customer2 = new EmployeeCustomer("Tjetri", "Tjetra", "hajde", "1234",
        "Tirana", "0691234567", "EmployeeCustomer");
       salesOrder2.setCustomer(customer2);
       // I also couldn't understand why the customers needed to implement Person
       // Why would a customer have a password and a username? but I did it anyway
    
       // Add some animals to the sales order
       Animal cat = new Cat("black", false);
       Animal dog = new Dog("Jari", "Shqiperi");
       Animal bird = new Bird(true);

       salesOrder.addAnimal(cat);
       salesOrder.addAnimal(dog);
       salesOrder.addAnimal(bird);
       // Add some animals to the second sales order for the second customer
       salesOrder2.addAnimal(cat);
       salesOrder2.addAnimal(dog);
       salesOrder2.addAnimal(bird);

       // Set the manager for the sales order
       salesOrder.setManager(manager);
       salesOrder2.setManager(manager2);

       // Calculate discounts for the sales order
       salesOrder.calculateDiscount();
       salesOrder2.calculateDiscount();

       // Display order details
       System.out.println(salesOrder.getDescription());
       System.out.println(salesOrder2.getDescription());
       System.out.println("Amount to Pay: " + salesOrder.getAmountToPay());
       System.out.println("Amount to Pay for second case: " + salesOrder2.getAmountToPay());

       // Add the orders to the PetStore
       petStore.addOrder(salesOrder);
       petStore.addOrder(salesOrder2);

       // Perform end-of-day operations
       // Note: The customer points are awareded before the discount is applied
       petStore.endOfDayOperations();
    }
}
