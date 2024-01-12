public class Customer extends Person {
    String type;

    Customer(String name, String surname, String username, String password, String address, String phoneNumber, String type) {
        super(name, surname, username, password, address, phoneNumber);
        this.type = type;
    }
}
