package edu.northeastern.sv.khoury.smartParkTest.controller;

import edu.northeastern.sv.khoury.smartParkTest.model.IParkingManager;
import edu.northeastern.sv.khoury.smartParkTest.model.Vehicle;
import edu.northeastern.sv.khoury.smartParkTest.model.VehicleType;
import edu.northeastern.sv.khoury.smartParkTest.view.IParkingManagerView;

/**
 * The ParkingManagerController class implements the Feature interface and is the controller
 * for the parking manager system.
 */
public class ParkingManagerController implements Feature{
  private IParkingManager parkingManager;
  private IParkingManagerView view;

  /**
   * Constructor for ParkingManagerController with parkingManager system.
   * Constructor used for mainManager class
   *
   * @param parkingManager The parking manager component.
   */
  public ParkingManagerController(IParkingManager parkingManager) {
    this.parkingManager = parkingManager;
  }


  /**
   * Constructor of ParkingManagerController with parking manager, payment system, and view.
   * Constructor used for parkingManagerControllerTest, as it takes one more parameter than main
   *
   * @param parkingManager The parking manager component.
   * @param view           The view component.
   */
  public ParkingManagerController(IParkingManager parkingManager, IParkingManagerView view) {
    this(parkingManager);
    this.view = view;
  }


  /**
   * Sets the view for this controller and adds itself as a feature to the view.
   *
   * @param v The parking manager view.
   */
  public void setView(IParkingManagerView v) {
    view = v;
    view.addFeatures(this);
  }

  @Override
  public void optionExecution(String option) {
    switch (option) {
      case "Total Parking Capacity Button":
        getTotalParkingCapacity();
        break;
      case "Available Parking Capacity Button":
        getAvailableParkingCapacity();
        break;
      case "Vehicle Details Button":
        displayVehicleDetails();
        break;
      case "Membership Status Button":
        checkMembershipStatus();
        break;
      case "Exit Button":
        exitProgram();
        break;
      default:
        view.showOptionError();
    }
  }

  /**
   * Retrieves and displays the total parking capacity for a given vehicle type.
   */
  private void getTotalParkingCapacity() {
    //prompt user input of vehicleType
    VehicleType vehicleTypeTotalCapacity = view.chooseVehicleType();

    int totalCapacity = parkingManager.getTotalCapacity(vehicleTypeTotalCapacity);
    view.displayMessage("Total Parking Capacity: " + totalCapacity);
  }

  /**
   * Retrieves and displays the available parking capacity for a given vehicle type.
   */
  private void getAvailableParkingCapacity() {
    //prompt user input of vehicleType
    VehicleType vehicleTypeAvailable = view.chooseVehicleType();
    int availableSpaces = parkingManager.getAvailableSpaces(vehicleTypeAvailable);
    view.displayMessage("Available Parking Spaces: " + availableSpaces);
  }

  /**
   * Displays details of a parked vehicle based on its license plate.
   */
  private void displayVehicleDetails() {
    //prompt user input of licensePlate
    String parkedVehicleLicensePlate = view.getLicensePlateInput();

    //create a vehicle object based on user input
    Vehicle parkedVehicle = parkingManager.getParkedVehicles().get(parkedVehicleLicensePlate.toUpperCase());

    //process the vehicle detail based on vehicle object and display messages.
    if (parkedVehicle != null) {
      view.displayMessage("Vehicle Details:");
      view.displayMessage(parkedVehicle.toString());
    } else {
      view.displayMessage("Vehicle not found.");
    }
  }

  /**
   * Checks the membership status of a vehicle based on its license plate.
   */
  private void checkMembershipStatus() {
    //prompt user input of licensePlate
    String vehicleMembershipLicensePlate = view.getLicensePlateInput();

    boolean isMember = parkingManager.getMembershipSystem().isMembership(vehicleMembershipLicensePlate.toUpperCase());
    if (isMember) {
      view.displayMessage("Vehicle is a member.");
    } else {
      view.displayMessage("Vehicle is not a member.");
    }
  }


  @Override
  public void exitProgram() {
    System.exit(0);
  }
}
