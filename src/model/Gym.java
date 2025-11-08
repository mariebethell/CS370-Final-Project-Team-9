package src.model;
public class Gym {
    String chain;
    int number;
    String hours;
    int numCourts;

    public Gym(String chain, int number, String hours, int numCourts) {
        this.chain = chain;
        this.number = number;
        this.hours = hours;
        this.numCourts = numCourts;
    }

    public String getChain() {
        return chain;
    }

    public String displayInfo(){
        return "Gym Chain: " + chain + "\nGym Number: " + number + "Hours: " + hours + "\nNumber of Courts: " + numCourts;
    }
}
