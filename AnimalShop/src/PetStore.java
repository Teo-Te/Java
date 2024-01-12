import java.util.ArrayList;
import java.util.HashMap;

public class PetStore {
    private ArrayList<SalesOrder> currentOrders;
    private ArrayList<Manager> managers;

    // This is the part which got my head spinning

    public PetStore() {
        this.currentOrders = new ArrayList<>();
        this.managers = new ArrayList<>();
    }

    public void addOrder(SalesOrder order) {
        currentOrders.add(order);
    }

    public void addManager(Manager manager) {
        managers.add(manager);
    }
    

    public void endOfDayOperations() {
        // Example: Compute overall sold amount, discount, and amount after discount
        int overallSoldAmount = 0;
        int overallDiscount = 0;
        int overallLoyalCustomerGiftPoints = 0;
        int overallEmployeeCustomerGiftPoints = 0;

        // Anything relted to the hashmap for the overall animal counts
        // Was not done by me, StackOverflow and ChatGPT helped me with that 
        // I did not know how to do it

        HashMap<Class<? extends Animal>, Integer> overallAnimalCounts = new HashMap<>();

        for (SalesOrder order : currentOrders) {
            overallSoldAmount += order.getAmountToPay();
            overallDiscount += order.getAmountToPay() - order.getTotal();
            // instanceof is used for testing 
            if (order.getCustomer() instanceof LoyalCustomer) {
                overallLoyalCustomerGiftPoints += ((LoyalCustomer) order.getCustomer()).getGiftPoints();
            } else if (order.getCustomer() instanceof EmployeeCustomer) {
                overallEmployeeCustomerGiftPoints += ((EmployeeCustomer) order.getCustomer()).getGiftPoints();
            } else {
                System.out.println("Customer has no gift points");
            }

            for (Animal animal : order.getAnimals()) {
                Class<? extends Animal> animalType = animal.getClass();
                overallAnimalCounts.put(animalType, overallAnimalCounts.getOrDefault(animalType, 0) + 1);
            }
        }

        double vatRate = 0.2; // As per google, Albania's VAT rate is 20%
        double overallVAT = overallSoldAmount * vatRate;

        System.out.println("Overall Sold Amount After Discount: " + overallSoldAmount);
        System.out.println("Overall Discount: " + overallDiscount);
        System.out.println("Overall Amount Before Discount: " + (overallSoldAmount - overallDiscount));
        System.out.println("Overall Loyal Customer Gift Points: " + overallLoyalCustomerGiftPoints);
        System.out.println("Overall Employee Customer Gift Points: " + overallEmployeeCustomerGiftPoints);
        System.out.println("Overall Number of Each Type of Animal Purchased:");
        for (Class<? extends Animal> animalType : overallAnimalCounts.keySet()) {
            System.out.println("   " + animalType.getSimpleName() + ": " + overallAnimalCounts.get(animalType));
        }
        System.out.println("Overall VAT Accumulated: " + overallVAT);
    }
}
