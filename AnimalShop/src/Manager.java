public class Manager extends Person {
    private int internalPhoneNumber;
    private int pin;
    private int discountDog;
    private int discountCat;
    private int discountBird;

    public Manager(String name, String surname, String username, String password, String address, String phoneNumber,
                   int internalPhoneNumber, int pin) {
        super(name, surname, username, password, address, phoneNumber);
        this.internalPhoneNumber = validateInternalPhoneNumber(internalPhoneNumber);
        this.pin = validatePin(pin);
    }

    public int getDogDiscount() {
        return discountDog;
    }

    public void setDogDiscount(int discount) {
        this.discountDog = validateDiscount(discount);
    }

    public int getCatDiscount() {
        return discountCat;
    }

    public void setCatDiscount(int discount) {
        this.discountCat = validateDiscount(discount);
    }

    public int getBirdDiscount() {
        return discountBird;
    }

    public void setBirdDiscount(int discount) {
        this.discountBird = validateDiscount(discount);
    }

    private int validateInternalPhoneNumber(int internalPhoneNumber) {
        if (internalPhoneNumber < 0) {
            System.out.println("Internal phone number cannot be negative");
            return 0; // Default value
        }
        return internalPhoneNumber;
    }

    private int validatePin(int pin) {
        if (pin < 1000 || pin > 9999) {
            System.out.println("PIN must be a 4-digit number");
            return 0; 
        }
        return pin;
    }

    private int validateDiscount(int discount) {
        if (discount < 0 || discount > 100) {
            System.out.println("Discount must be between 0 and 100");
            return 0; 
        }
        // Add more validation if needed
        return discount;
    }
    // Return 0 is just a default value, you can return other values if needed
    // But I think it fulfills the requirement of the task
}