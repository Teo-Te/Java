public class Bird extends Animal {
    boolean sings;

    Bird(boolean sings) {
        super("bird");
        this.sings = sings;
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Sings: " + this.sings);
    }
    
}
