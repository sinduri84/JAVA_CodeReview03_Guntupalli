package CityBike;

import java.util.*;

import static CityBike.Bike.bikeHash;
import static CityBike.Bike.printAllBikes;
import static CityBike.Rent.*;
import static CityBike.Station.printAllStations;
import static CityBike.Station.stationHashMap;
import static CityBike.User.printAllUsers;
import static CityBike.User.userHashMap;


public class Main {
    public static void main(String[] args) {


        //Creates Bike objects;
        Bike bike1 = new Bike("Red");
        Bike bike2 = new Bike("Blue");
        Bike bike3 = new Bike("White");
        Bike bike4 = new Bike("Green");
        Bike bike5 = new Bike("Black");
        Bike bike6 = new Bike("Yellow");
        Bike bike7 = new Bike("Red");
        Bike bike8 = new Bike("Green");
        Bike bike9 = new Bike("Violet");
        Bike bike10 = new Bike("Black");
        Bike bike11 = new Bike("Black");
        Bike bike12 = new Bike("Red");
        Bike bike13 = new Bike("Blue");

        //Creates new Station Objects
        Station station1 = new Station("Karlsplatz");
        Station station2 = new Station("Stephensplatz");
        Station station3 = new Station("Flanagans");

        //Adds bikes to the Stations
        station1.addBike(bike1);
        station1.addBike(bike2);
        station1.addBike(bike3);
        station2.addBike(bike4);
        station2.addBike(bike5);
        station2.addBike(bike6);
        station2.addBike(bike7);
        station3.addBike(bike8);
        station3.addBike(bike9);
        station3.addBike(bike10);
        station3.addBike(bike11);
        station3.addBike(bike12);
        station3.addBike(bike13);

        //Creates new Users
        User user1 = new User("Minerva", "Fladeboe");
        User user2 = new User("Ahalya", "Guntupalli");
        User user3 = new User("Hella", "Kancharla");
        User user4 = new User("Subbu", "Rallapalli");
        User user5 = new User("Luna", "Gun");

        //Creates new Rent Objects and Calls the method to return bikes to the specified station
        Rent rent1 = new Rent(user1, station1, bike1, "19.02.2019");
        rent1.returnBike(1, station2, "25.02.2019");

        Rent rent2 = new Rent(user2, station2, bike4, "20.05.2019");

        Rent rent3 = new Rent(user3, station3, bike8, "20.07.2019");
        rent3.returnBike(3, station1, "28.07.2019");

        Rent rent4 = new Rent(user4, station2, bike6, "20.04.2019");
        rent4.returnBike(4, station3, "15.05.2019");

        Rent rent5 = new Rent(user1, station3, bike6, "20.09.2019");

        Rent rent6 = new Rent(user4, station3, bike10, "20.08.2019");
        rent6.returnBike(6, station2, "15.09.2019");


        //Input options
        Scanner inputNumber = new Scanner(System.in);
        Scanner inputText = new Scanner(System.in);

        //Starts a while loop through different options as an administrator or as a user;
        boolean input1 = true;
        while (input1) {
            boolean input2 = true;
            System.out.print("\nPress 1 if you are the Administrator" +
                    "\nPress 2 if you are a User" +
                    "\nPress 3 to exit" +
                    "\nOption: ");
            //Checks if the input value is an integer
            if (inputNumber.hasNextInt()) {
                int inputOption1 = inputNumber.nextInt();
                if (inputOption1 == 1) {
                    //Enters as an administrator
                    while (input2) {
                        System.out.print("\nPress 11 if you want to add a new bike" +
                                "\nPress 12 if you want to add a new bike to a station" +
                                "\nPress 13 if you want to add a new station" +
                                "\nPress 14 if you want to see all user details" +
                                "\nPress 15 to update the status of a bike" +
                                "\nPress 16 to see all rental details" +
                                "\nPress 17 to exit" +
                                "\nOption: ");
                        if (inputNumber.hasNextInt()) {
                            int inputOption2 = inputNumber.nextInt();
                            switch (inputOption2) {
                                //Adds new Bike;
                                case 11:
                                    System.out.print("Enter the color of the new bike: ");
                                    String inputText11 = inputText.nextLine();
                                    Bike bike = new Bike(inputText11);
                                    System.out.println("New Bike Created");
                                    printAllBikes();
                                    break;
                                //Adds new Bike to a specified Station;
                                case 12:
                                    printAllStations();
                                    System.out.print("Enter the color of the new bike: ");
                                    String inputText12 = inputText.nextLine();
                                    System.out.print("Enter the station id to add the new bike: ");
                                    if (inputNumber.hasNextInt()) {
                                        int inputNumber12 = inputNumber.nextInt();
                                        if (stationHashMap.containsKey(inputNumber12)) {
                                            stationHashMap.get(inputNumber12).addBike(new Bike(inputText12));
                                        }
                                    } else {
                                        System.out.println("Please select the appropriate integer!");
                                    }
                                    break;
                                //Adds a new Station;
                                case 13:
                                    System.out.print("Enter the location of the new station: ");
                                    String inputText13 = inputText.nextLine();
                                    Station station = new Station(inputText13);
                                    break;
                                //Prints all users and their details
                                case 14:
                                    printAllUsers();
                                    break;
                                //Updates the status of a bike and based on the status, updates its position in Stations;
                                case 15:
                                    printAllBikes();
                                    System.out.print("Enter the bike id to update the status: ");
                                    if (inputNumber.hasNextInt()) {
                                        int inputNumber15 = inputNumber.nextInt();
                                        if (bikeHash.containsKey(inputNumber15)) {
                                            System.out.println("Current Status: " + bikeHash.get(inputNumber15).getBikeStatus());
                                            System.out.println("What status do you want to change it to? Choose between \"CanBeRented, CanNotBeRented, InService, Discarded\" ");
                                            String inputText15 = inputText.nextLine();
                                            //Checks to make sure the input text is one of the choices of the Bike status enum values and then updates the status;
                                            for (Bike.BikeStatus bikeS : Bike.BikeStatus.values()) {
                                                if (bikeS.name().equals(inputText15)) {
                                                    bikeHash.get(inputNumber15).setBikeStatus(Bike.BikeStatus.valueOf(inputText15));
                                                    System.out.println("Status Updated");
                                                    //If the Status has been changed to something other than "CanBeRented", the bike will be removed from the Station - BikeHashMap;
                                                    if (bikeHash.get(inputNumber15).getBikeStatus() != Bike.BikeStatus.CanBeRented) {
                                                        Iterator iterate = stationHashMap.entrySet().iterator();
                                                        while (iterate.hasNext()) {
                                                            Map.Entry<Integer, Station> pair = (Map.Entry) iterate.next();
                                                            if (pair.getValue().getBikeHashStation().containsKey(inputNumber15)) {
                                                                pair.getValue().getBikeHashStation().remove(inputNumber15);
                                                            }
                                                        }
                                                    }
                                                    printAllBikes();
                                                }
                                            }
                                            printAllStations();
                                        }
                                    }
                                    break;
                                //Prints all Rental Details;
                                case 16:
                                    printAllRentals();
                                    break;
                                //Quit Options
                                case 17:
                                    input2 = false;
                                    break;
                                default:
                                    System.out.println("Please choose one of the options");
                                    break;
                            }
                        } else {
                            System.out.println("Please choose an integer!");
                        }
                    }
                    input1 = false;
                } else if (inputOption1 == 2) {
                    //Starts in the user section;
                    while (input2) {
                        boolean input3 = true;
                        System.out.print("\nPress 21 if you want to register as a new user" +
                                "\nPress 22 if you want to sign in" +
                                "\nOption: ");
                        if (inputNumber.hasNextInt()) {
                            int inputOption3 = inputNumber.nextInt();
                            int userId = 0;
                            switch (inputOption3) {
                                //Registers and automatically signs in as a new user;
                                case 21:
                                    System.out.print("Enter your First Name: ");
                                    String inputText211 = inputText.nextLine();
                                    System.out.print("Enter your Last Name: ");
                                    String inputText212 = inputText.nextLine();
                                    User user = new User(inputText211, inputText212);
                                    System.out.println("User Created Successfully and Automatically Logged in.");
                                    userId = user.getUserID();
                                    break;
                                //Signs in as a new user;
                                case 22:
                                    System.out.print("Enter your User Id: ");
                                    int inputNumber21 = inputNumber.nextInt();
                                    if (userHashMap.containsKey(inputNumber21)) {
                                        userId = inputNumber21;
                                    }
                                    break;
                                default:
                                    System.out.println("Press 21 or 22!");
                                    break;
                            }
                            //After successful registration or sign-in, options to rent or return a bike;
                            if (userId != 0) {
                                System.out.print("\nPress 23 to rent a Bike!" +
                                        "\nPress 24 if you want return a Bike" +
                                        "\nOption: ");
                                if (inputNumber.hasNextInt()) {
                                    int inputOption4 = inputNumber.nextInt();

                                    switch (inputOption4) {
                                        //Rents a bike;
                                        case 23:
                                            printAllStations();
                                            System.out.print("Enter the Station Id you want to rent a Bike from: ");
                                            int inputStationId = inputNumber.nextInt();

                                            if (stationHashMap.containsKey(inputStationId)) {
                                                if (stationHashMap.get(inputStationId).getBikeHashStation() == null) {
                                                    System.out.printf("%-16d %-16s %-16s %-16s %-16s %-16s %n", stationHashMap.get(inputStationId).getStationID(), stationHashMap.get(inputStationId).getStationLocation(), "None", "None", "None", "None");
                                                } else {
                                                    Iterator iterateBike = stationHashMap.get(inputStationId).getBikeHashStation().entrySet().iterator();
                                                    while (iterateBike.hasNext()) {
                                                        Map.Entry<Integer, Bike> pairBike = (Map.Entry) iterateBike.next();
                                                        System.out.printf("%-16d %-16s %-16d %-16s %-16s %-16s %n", stationHashMap.get(inputStationId).getStationID(), stationHashMap.get(inputStationId).getStationLocation(), pairBike.getKey(), pairBike.getValue().getBikeName(), pairBike.getValue().getBikeColor(), pairBike.getValue().getBikeStatus());

                                                    }
                                                }
                                                System.out.print("Enter the Bike Id you want to rent: ");
                                                int inputBikeId = inputNumber.nextInt();
                                                Iterator<Map.Entry<Integer, Bike>> itrBike = stationHashMap.get(inputStationId).getBikeHashStation().entrySet().iterator();
                                                while (itrBike.hasNext()) {
                                                    Map.Entry<Integer, Bike> entryBike = itrBike.next();
                                                    if (entryBike.getKey() == inputBikeId) {
                                                        System.out.print("Enter the start date of your rental only in the format of dd.MM.yyyy: ");
                                                        String rentNewStartDate = inputText.nextLine();
                                                        Rent rent = new Rent(userHashMap.get(userId), stationHashMap.get(inputStationId), entryBike.getValue(), rentNewStartDate, itrBike);
                                                        printAllStations();
                                                        System.out.println("Rental Started");
                                                        printAllRentals();
                                                    }
                                                }
                                            } else {
                                                System.out.println("Please select one of the available stations!");
                                            }
                                            break;
                                        //Returns Bike;
                                        case 24:
                                            Iterator iterateRent = rentHashMap.entrySet().iterator();
                                            String noReturn = "";
                                            while (iterateRent.hasNext()) {
                                                Map.Entry<Integer, Rent> pairRent = (Map.Entry) iterateRent.next();
                                                if ((pairRent.getValue().getUser().getUserID() == userId) &&
                                                        pairRent.getValue().getRentEndDate() == null) {
                                                    printRentalByUser(userHashMap.get(userId));
                                                    try {

                                                        System.out.print("Enter the Rent Id: ");
                                                        int inputNumber24Rent = inputNumber.nextInt();
                                                        System.out.print("Enter the Station Id you want to return the Bike to: ");
                                                        int inputNumber24Station = inputNumber.nextInt();
                                                        System.out.print("Enter the end date of your rental only in the format of dd.MM.yyyy: ");
                                                        String rentNewEndDate = inputText.nextLine();
                                                        rentHashMap.get(inputNumber24Rent).returnBike(inputNumber24Rent, stationHashMap.get(inputNumber24Station), rentNewEndDate);
                                                        printAllRentals();
                                                    } catch (Exception e) {
                                                        System.out.println("Please select the right values in the right format!");
                                                    }
                                                }
                                            }
                                            break;
                                        default:
                                            System.out.println("Press 21 or 22!");
                                            break;
                                    }
                                }
                            }
                        } else {
                            System.out.println("Please enter an integer!");
                            input2 = false;
                        }
                        input2 = false;
                    }
                    input1 = false;
                } else if (inputOption1 == 3) {
                    input1 = false;
                } else {
                    System.out.println("Please choose Option 1 or Option 2\n");
                }
            } else {
                System.out.println("Please choose an integer option!");
            }

            //Closes the Scanner Inputs;
            inputNumber.close();
            inputText.close();
        }
    }
}
