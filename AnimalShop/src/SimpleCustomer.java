public class SimpleCustomer extends Customer {
    private int giftPoints;

    SimpleCustomer(String name, String surname, String username, String password, String address, String phoneNumber,
            String type) {
        super(name, surname, username, password, address, phoneNumber, "SimpleCustomer");
        this.giftPoints = 0;
        
    }
    
    public int getGiftPoints() {
        return giftPoints;
    }
}
