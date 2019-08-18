package assignment.home.com.ehailingapp.model;

/**
 * {@link Taxi} represents an available taxi that the user can see.
 * It contains a taxi name and estimated arrival time.
 */
public class Taxi {

    private String stationName;
    private int arrivalTime;

    public Taxi(String stationName, int arrivalTime) {
        this.stationName = stationName;
        this.arrivalTime = arrivalTime;
    }

    public String getStationName() {
        return stationName;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }
}
