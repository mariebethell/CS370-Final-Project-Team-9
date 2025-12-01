package src.model;
public class Gym {
    String chain;
    int number;
    String hours;
    int numCourts;
    String address;

    public Gym(String chain, int number, String hours, int numCourts, String address) {
        this.chain = chain;
        this.number = number;
        this.hours = hours;
        this.numCourts = numCourts;
        this.address = address;
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
