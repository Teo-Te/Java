public class EmployeeCustomer extends Customer {
    private int giftPoints;

    EmployeeCustomer(String name, String surname, String username, String password, String address, String phoneNumber,
            String type) {
        super(name, surname, username, password, address, phoneNumber, "EmployeeCustomer");
        this.giftPoints = 0;
        
    }
    
    public int getGiftPoints() {
        return giftPoints;
    }

    public void setGiftPoints(int money) {
        this.giftPoints = money*2;
    }
}
