public class Animal {
    static int incrementalId = 0;
    int id;
    String type;
    
    Animal(String type) {
        this.id = incrementalId++;
        this.type = type;
    }

    public void printInfo() {
        System.out.println("Animal type: " + this.type);
    }

    public Object getType() {
        return null;
    }
}
