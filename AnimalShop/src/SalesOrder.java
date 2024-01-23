import java.util.ArrayList;

class SalesOrder implements Payable {
    private static int idCounter = 1;
    private int id;
    private ArrayList<Animal> animals;
    private int total;
    private int discount;
    private Customer customer;
    private Manager manager;

    public SalesOrder() {
        this.id = idCounter++;
        this.animals = new ArrayList<>();
        this.total = 0;
        this.discount = 0;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    private int calculateAnimalPrice(Animal animal) {
        if (animal instanceof Cat) {
            return 100;  
        } else if (animal instanceof Dog) {
            return 200;  
        } else if (animal instanceof Bird) {
            return 150;  
        } else {
            return 50;
        }
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
        total += calculateAnimalPrice(animal);
    }

    public void calculateDiscount() {
        // Calculate discount based on customer type
        if (customer instanceof LoyalCustomer) {
            ((LoyalCustomer) customer).setGiftPoints(total);
        } else if (customer instanceof EmployeeCustomer) {
            ((EmployeeCustomer) customer).setGiftPoints(total);
        } else {
            System.out.println("Customer type not supported");
        }

        // Apply fixed discounts for each type of animal from the manager
        for (Animal animal : animals) {
            if (animal instanceof Cat) {
                discount += manager.getCatDiscount();
            } else if (animal instanceof Dog) {
                discount += manager.getDogDiscount();
            } else if (animal instanceof Bird) {
                discount += manager.getBirdDiscount();
            }
        }
    }

    
    public int getAmountToPay() {
        return total - discount;
    }

    
    public String getDescription() {
        return "SalesOrder ID: " + id + ", Total: " + total + ", Discount: " + discount;
    }

    public int getTotal() {
        return total;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Animal[] getAnimals() {
        return animals.toArray(new Animal[animals.size()]);
    }
}