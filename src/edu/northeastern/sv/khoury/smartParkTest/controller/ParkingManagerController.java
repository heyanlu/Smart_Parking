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

  public void optionExecution(String option) {
    switch (option) {
      case "Total Parking Capacity Button":
        VehicleType vehicleTypeTotalCapacity = view.chooseVehicleType();
        int totalCapacity = parkingManager.getTotalCapacity(vehicleTypeTotalCapacity);
        view.displayMessage("Total Parking Capacity: " + totalCapacity);
        break;
      case "Available Parking Capacity Button":
        VehicleType vehicleTypeAvailable = view.chooseVehicleType();
        int availableSpaces = parkingManager.getAvailableSpaces(vehicleTypeAvailable);
        view.displayMessage("Available Parking Spaces: " + availableSpaces);
        break;
      case "Vehicle Details Button":
        String parkedVehicleLicensePlate = view.getLicensePlateInput();
        Vehicle parkedVehicle = (Vehicle) parkingManager.getParkedVehicles().get(parkedVehicleLicensePlate.toUpperCase());
        if (parkedVehicle != null) {
          view.displayMessage("Vehicle Details:");
          view.displayMessage(parkedVehicle.toString());
        } else {
          view.displayMessage("Vehicle not found.");
        }
        break;
      case "Membership Status Button":
        String vehicleMembershipLicensePlate = view.getLicensePlateInput();
        boolean isMember = parkingManager.getMembershipSystem().isMembership(vehicleMembershipLicensePlate.toUpperCase());
        if (isMember) {
          view.displayMessage("Vehicle is a member.");
        } else {
          view.displayMessage("Vehicle is not a member.");
        }
        break;
      case "Exit Button":
        exitProgram();
        break;
      default:
        view.showOptionError();
    }
  }

  public void exitProgram() {
    System.exit(0);
  }
}
