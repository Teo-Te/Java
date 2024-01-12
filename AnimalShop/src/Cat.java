public class Cat extends Animal {
    String color;
    boolean bites;

    Cat(String color, boolean bites) {
        super("Cat");
        this.color = color;
        this.bites = bites;
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Color: " + this.color);
        System.out.println("Bites: " + this.bites);
    }
    
}
