public class Cashier extends Person {
    int internalPhoneNumber;
    int salary;

    Cashier(String name, String surname, String username, String password, String address, String phoneNumber, int internalPhoneNumber, int salary) {
        super(name, surname, username, password, address, phoneNumber);
        this.internalPhoneNumber = internalPhoneNumber;
        this.salary = salary;
    }
    
}
