package CityBike;

import java.util.*;

public class Station {
    static private int staticID = 0;
    private int stationID;
    private String stationLocation;
    private HashMap<Integer, Bike> bikeHashStation = new HashMap<>();
    public static HashMap<Integer, Station> stationHashMap = new HashMap<>();

    public Station(String stationLocation) {
        setStationID(staticID + 1);
        staticID++;
        this.stationLocation = stationLocation;
        stationHashMap.put(this.getStationID(), this);
    }

    public int getStationID() {
        return stationID;
    }

    public void setStationID(int stationID) {
        this.stationID = stationID;
    }

    public String getStationLocation() {
        return stationLocation;
    }

    public void setStationLocation(String stationLocation) {
        this.stationLocation = stationLocation;
    }

    public HashMap<Integer, Bike> getBikeHashStation() {
        return bikeHashStation;
    }

    public void setBikeHashStation(HashMap<Integer, Bike> bikeHashStation) {
        this.bikeHashStation = bikeHashStation;
    }

    public void addBike(Bike newBike) {
        if(newBike.getBikeStatus() == Bike.BikeStatus.CanBeRented) {
            if (this.getBikeHashStation() == null) {
                HashMap<Integer, Bike> bikeHashStation = new HashMap<>();
                bikeHashStation.put(newBike.getBikeID(), newBike);
                this.setBikeHashStation(bikeHashStation);
            }
            if (this.getBikeHashStation().size() < 5) {
                this.getBikeHashStation().put(newBike.getBikeID(), newBike);
                System.out.println("\nNew Bike added to " + this.getStationLocation() + " station.");
            } else {
                System.out.println("\nThe station, " + this.getStationLocation() + " is full and cannot have more than 5 bikes.");
            }
        } else {
            System.out.println("Bike cannot be rented.");
        }

    }

    public static void printAllStations() {
        Iterator iterate = stationHashMap.entrySet().iterator();
        System.out.println("List of Bikes and their stations: ");
        System.out.printf("%-16s %-16s %-16s %-16s %-16s %-16s %n", "Station ID", "Station Location", "Bike ID", "Bike Name", "Bike Color", "Bike Status");
        while (iterate.hasNext()) {
            Map.Entry<Integer, Station> pair = (Map.Entry) iterate.next();
            if (pair.getValue().getBikeHashStation() == null) {
                System.out.printf("%-16d %-16s %-16s %-16s %-16s %-16s %n", pair.getKey(), pair.getValue().getStationLocation(), "None", "None", "None", "None");
            } else {
                Iterator iterateBike = pair.getValue().getBikeHashStation().entrySet().iterator();
                while (iterateBike.hasNext()) {
                    Map.Entry<Integer, Bike> pairBike = (Map.Entry) iterateBike.next();
                    System.out.printf("%-16d %-16s %-16d %-16s %-16s %-16s %n", pair.getKey(), pair.getValue().getStationLocation(), pairBike.getKey(), pairBike.getValue().getBikeName(), pairBike.getValue().getBikeColor(), pairBike.getValue().getBikeStatus());

                }

            }


        }
    }


}
