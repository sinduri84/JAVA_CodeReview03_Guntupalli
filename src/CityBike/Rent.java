package CityBike;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static CityBike.Bike.BikeStatus;

public class Rent {
    static private int staticID = 0;
    private int rentID;
    private User user;
    private Station stationPickup;
    private Bike bike;
    private String rentStartDate;
    private String rentEndDate;
    private Station stationDropOff;
    private boolean rentStatus;
    public static HashMap<Integer, Rent> rentHashMap = new HashMap<>();
    public String date;
    private static final int rentPeriod = 15;
    static DateFormat dFormat = new SimpleDateFormat("dd.MM.yyyy");
    static Calendar calendar = Calendar.getInstance();

    //Constructor for new Rentals
    public Rent(User user, Station station, Bike bike, String rentStartDate) {
        setRentID(staticID + 1);
        staticID++;
        setUser(user);
        setStationPickup(station);
        setBike(bike);
        //Removes the bike from the bikeList of that particular station
        this.stationPickup.getBikeHashStation().remove(bike.getBikeID());
        this.bike.setBikeStatus(BikeStatus.InService);
        setRentStatus(true);
        setRentStartDate(rentStartDate);
        //Sets the User status if they currently rented a bike
        this.user.setCurrentlyRentedBike(true);
        //Adds the new rental to the rental list;
        rentHashMap.put(this.rentID, this);


    }

    public void returnBike(int rentID, Station station, String rentEndDate) {
        setRentStatus(false);
        setStationDropOff(station);
        //Adds the bike to the Drop-off Station
        this.stationDropOff.addBike(this.getBike());
        this.bike.setBikeStatus(BikeStatus.CanBeRented);
        setRentEndDate(rentEndDate);
    }

    public int getRentID() {
        return rentID;
    }

    public void setRentID(int rentID) {
        this.rentID = rentID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Station getStationPickup() {
        return stationPickup;
    }

    public void setStationPickup(Station stationPickup) {
        this.stationPickup = stationPickup;
    }

    public String getRentStartDate() {
        return rentStartDate;
    }

    public void setRentStartDate(String rentStartDate) {
        this.rentStartDate = rentStartDate;
    }

    public String getRentEndDate() {
        return rentEndDate;
    }

    public void setRentEndDate(String rentEndDate) {
        this.rentEndDate = rentEndDate;
    }

    public Station getStationDropOff() {
        return stationDropOff;
    }

    public void setStationDropOff(Station stationDropOff) {
        this.stationDropOff = stationDropOff;
    }

    public Bike getBike() {
        return bike;
    }

    public void setBike(Bike bike) {
        this.bike = bike;
    }

    public boolean isRentStatus() {
        return rentStatus;
    }

    public void setRentStatus(boolean rentStatus) {
        this.rentStatus = rentStatus;
    }

    public static void printAllRentals() {
        //Tries and catches an error if the date format is not in the specified format
        try {
            System.out.printf("%-8s %-16s %-16s %-16s %-32s %-32s %-32s %-16s %-16s %-16s %n", "Rent ID", "Bike", "User", "Pickup Station", "Drop-off Station", "Rent Status", "Bike Status", "Start Date", "Due Date", "Return Date");
            Iterator iterateRent = rentHashMap.entrySet().iterator();
            while (iterateRent.hasNext()) {
                Map.Entry<Integer, Rent> pairRent = (Map.Entry) iterateRent.next();
                Date rentStartDateFormat = dFormat.parse(pairRent.getValue().getRentStartDate());
                calendar.setTime(rentStartDateFormat);
                calendar.add(Calendar.DAY_OF_MONTH, rentPeriod);
                String rentDueDate = dFormat.format(calendar.getTime());
                Date rentDueDateDate = dFormat.parse(rentDueDate);
                String bikeStatus = "";
                if(pairRent.getValue().getRentEndDate() == null) {
                    bikeStatus = "Bike not returned yet!";
                } else {
                    Date rentEndDateFormat = dFormat.parse(pairRent.getValue().getRentEndDate());
                    if (rentEndDateFormat.before(rentDueDateDate)) {
                        bikeStatus = "Bike returned in time";
                    } else {
                        bikeStatus = "Fined for late drop-off!";
                    }
                }

                System.out.printf("%-8d %-16s %-16s %-16s %-32s %-32s %-32s %-16s %-16s %-16s %n", pairRent.getKey(), pairRent.getValue().getBike().getBikeName(),
                        pairRent.getValue().getUser().getUserName(), pairRent.getValue().getStationPickup().getStationLocation(),
                        ((pairRent.getValue().getStationDropOff() != null) ? pairRent.getValue().getStationDropOff().getStationLocation() : "Not dropped off yet!"),
                        pairRent.getValue().getBike().getBikeStatus(), bikeStatus,
                        pairRent.getValue().getRentStartDate(), rentDueDate,
                        ((pairRent.getValue().getRentEndDate() != null) ? pairRent.getValue().getRentEndDate() : "None"));

            }
        } catch (ParseException e) {
            System.out.println("Please specify the date in the dd.MM.yyyy format");
        }
    }

    public static void printRentalByUser(User user) {
        try {
            System.out.printf("%-8s %-16s %-16s %-16s %-32s %-32s %-16s %-16s %-16s %n", "Rent ID", "Bike", "User", "Pickup Station", "Drop-off Station", "Rent Status", "Start Date", "Due Date", "Return Date");
            Iterator iterateRent = rentHashMap.entrySet().iterator();
            while (iterateRent.hasNext()) {
                Map.Entry<Integer, Rent> pairRent = (Map.Entry) iterateRent.next();
                Date rentStartDateFormat = dFormat.parse(pairRent.getValue().getRentStartDate());
                calendar.setTime(rentStartDateFormat);
                calendar.add(Calendar.DAY_OF_MONTH, rentPeriod);
                String rentDueDate = dFormat.format(calendar.getTime());
                Date rentDueDateDate = dFormat.parse(rentDueDate);

                if((pairRent.getValue().getUser().getUserID() == user.getUserID()) && pairRent.getValue().getRentEndDate() == null) {
                    System.out.printf("%-8d %-16s %-16s %-16s %-32s %-32s %-16s %-16s %-16s %n", pairRent.getKey(), pairRent.getValue().getBike().getBikeName(),
                            pairRent.getValue().getUser().getUserName(), pairRent.getValue().getStationPickup().getStationLocation(),
                            ((pairRent.getValue().getStationDropOff() != null) ? pairRent.getValue().getStationDropOff().getStationLocation() : "Not dropped off yet!"),
                            pairRent.getValue().getBike().getBikeStatus(),
                            pairRent.getValue().getRentStartDate(), rentDueDate,
                            ((pairRent.getValue().getRentEndDate() != null) ? pairRent.getValue().getRentEndDate() : "None"));
                }





            }
        } catch (ParseException e) {
            System.out.println("Please specify the date in the dd.MM.yyyy format");
        }

    }
}
