package src.model;
public class Gym {
    String chain;
    int number;
    String hours;
    int numCourts;
    String address;
    int id;

    public Gym(int id, String chain, int number, String hours, int numCourts, String address) {
        this.id = id;
        this.chain = chain;
        this.number = number;
        this.hours = hours;
        this.numCourts = numCourts;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getChain() {
        return chain;
    }

    public String getAddress() {
        return address;
    }

    public String displayInfo(){
        return "Gym Chain: " + chain + "\nGym Number: " + number + "Hours: " + hours + "\nNumber of Courts: " + numCourts + "\nAddress: " + address;
    }
}
