package src.model;
<<<<<<< HEAD

import java.time.LocalTime;
import java.util.List;

public class Gym {
    private String chain;
    private int number;
    private LocalTime openTime;
    private LocalTime closeTime;
    private int numCourts;
    private String address;
    private int id;

    public Gym(int id, String chain, int number, LocalTime openTime, LocalTime closeTime, int numCourts, String address) {
=======
public class Gym {
    String chain;
    int number;
    String hours;
    int numCourts;
    String address;
    int id;

    public Gym(int id, String chain, int number, String hours, int numCourts, String address) {
>>>>>>> 966495db27b754184aecea08900b0dccb83874d2
        this.id = id;
        this.chain = chain;
        this.number = number;
        this.openTime = openTime;
        this.closeTime = closeTime;
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

    public LocalTime getOpenTime() {
        return openTime;
    }

    public LocalTime getCloseTime() {
        return closeTime;
    }
}
