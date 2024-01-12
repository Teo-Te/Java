public class Dog extends Animal {
    String name;
    String origin;

    Dog(String name, String origin) {
        super("Dog");
        this.name = name;
        this.origin = origin;
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Name: " + this.name);
        System.out.println("Origin: " + this.origin);
    }
}
