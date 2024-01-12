public class Person {
    String name;
    String surname;
    static int incrementalId = 0;
    int id;
    String username;
    String password;
    String address;
    String phoneNumber;

    Person(String name, String surname, String username, String password, String address, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
        Person.incrementalId++;
        this.id = Person.incrementalId;    
    }
}
