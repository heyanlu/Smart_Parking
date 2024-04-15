package edu.northeastern.sv.khoury.smartParkTest.controller;

import edu.northeastern.sv.khoury.smartParkTest.model.IPaymentSystem;
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
   *
   * @param parkingManager The parking manager component.
   */
  public ParkingManagerController(IParkingManager parkingManager) {
    this.parkingManager = parkingManager;
  }

  /**
   * Sets the view for this controller and adds itself as a feature to the view.
   * @param v The parking manager view.
   */
  public void setView(IParkingManagerView v) {
    view = v;
    view.addFeatures(this);
  }

  /**
   * Execute corresponding model methods with an option
   * @param option The option to be executed.
   */
  public void optionExecution(String option) {
    switch (option) {
      case "Total Parking Capacity":
        VehicleType vehicleTypeTotalCapacity = view.chooseVehicleType();
        int totalCapacity = parkingManager.getTotalCapacity(vehicleTypeTotalCapacity);
        view.displayMessage("Total Parking Capacity: " + totalCapacity);
        break;
      case "Available Parking Capacity":
        VehicleType vehicleTypeAvailable = view.chooseVehicleType();
        int availableSpaces = parkingManager.getAvailableSpaces(vehicleTypeAvailable);
        view.displayMessage("Available Parking Spaces: " + availableSpaces);
        break;
      case "Vehicle Details":
        String parkedVehicleLicensePlate = view.getLicensePlateInput();
        Vehicle parkedVehicle = (Vehicle) parkingManager.getParkedVehicles().get(parkedVehicleLicensePlate);
        if (parkedVehicle != null) {
          view.displayMessage("Vehicle Details:");
          view.displayMessage(parkedVehicle.toString());
        } else {
          view.displayMessage("Vehicle not found.");
        }
        break;
      case "Membership Status":
        String vehicleMembershipLicensePlate = view.getLicensePlateInput();
        boolean isMember = parkingManager.getMembershipSystem().isMembership(vehicleMembershipLicensePlate);
        if (isMember) {
          view.displayMessage("Vehicle is a member.");
        } else {
          view.displayMessage("Vehicle is not a member.");
        }
        break;
      case "Recharge Parking Fee":
        String vehicleRechargeLicensePlate = view.getLicensePlateInput();
        Vehicle vehicleRecharge = (Vehicle) parkingManager.getParkedVehicles().get(vehicleRechargeLicensePlate);
        if (vehicleRecharge != null) {
          float rechargedFee = vehicleRecharge.rechargeParkingFee();
          view.displayMessage("Recharged parking fee: $" + rechargedFee);
        } else {
          view.displayMessage("Vehicle not found.");
        }
        break;
      case "Payment Status":
        String vehicleIsPaidLicensePlate = view.getLicensePlateInput();
        Vehicle vehicleIsPaid = (Vehicle) parkingManager.getParkedVehicles().get(vehicleIsPaidLicensePlate);
        if (vehicleIsPaid != null) {
          boolean isPaid = vehicleIsPaid.isPaidRechargeParkingFee();
          view.displayMessage("Is parking fee paid: " + isPaid);
        } else {
          view.displayMessage("Vehicle not found.");
        }
        break;
      case "Exit":
        exitProgram();
        break;
      default:
        view.showOptionError();
    }
  }

  /**
   * Exits the program.
   */
  public void exitProgram() {
    System.exit(0);
  }
}
