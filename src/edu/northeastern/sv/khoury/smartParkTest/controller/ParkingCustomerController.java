package edu.northeastern.sv.khoury.smartParkTest.controller;

import edu.northeastern.sv.khoury.smartParkTest.model.IPaymentSystem;
import edu.northeastern.sv.khoury.smartParkTest.model.IParkingManager;
import edu.northeastern.sv.khoury.smartParkTest.model.Vehicle;
import edu.northeastern.sv.khoury.smartParkTest.model.VehicleType;
import edu.northeastern.sv.khoury.smartParkTest.view.IParkingCustomerView;
import java.time.Duration;

/**
 * The ParkingCustomerController class implements the Feature interface is the controller
 * for the parking customer operations.
 */
public class ParkingCustomerController implements Feature {
  private IParkingManager parkingManager;

  private IPaymentSystem paymentSystem;

  private IParkingCustomerView view;

  /**
   * Constructor of ParkingCustomerController with parking manager and payment system.
   *
   * @param parkingManager The parking manager component.
   * @param paymentSystem  The payment system component.
   */
  public ParkingCustomerController(IParkingManager parkingManager, IPaymentSystem paymentSystem) {
    this.parkingManager = parkingManager;
    this.paymentSystem = paymentSystem;
  }

  /**
   * Sets the view for this controller and adds itself as a feature to the view.
   *
   * @param v The parking customer view.
   */
  public void setView(IParkingCustomerView v) {
    view = v;
    view.addFeatures(this);
  }

  /**
   * Executes corresponding methods in model based on the provided option string.
   *
   * @param option The option to be executed.
   */
  @Override
  public void optionExecution(String option) {
    switch (option) {
      case "Park Vehicle Button":
        VehicleType vehicleType = view.chooseVehicleType();
        if (vehicleType != null) {
          String licensePlate = view.getLicensePlateInput();
          if (!licensePlate.isEmpty()) {
            Vehicle newVehicle = parkingManager.createVehicle(licensePlate, vehicleType);
            if (parkingManager.parkVehicle(newVehicle)) {
              String newVehicleParkingPlace = parkingManager.assignParkingPlace(vehicleType);
              view.displayMessage("Vehicle parked successfully! Vehicle parking place: " + newVehicleParkingPlace);
            } else {
              view.displayMessage("Parking failed!");
            }
          } else {
            view.displayMessage("License plate cannot be empty.");
          }
        } else {
          view.displayMessage("Please choose a vehicle type.");
        }
        break;
      case "Process Payment Button":
        String licensePlateToPay = view.getLicensePlateInput();
        Vehicle vehicleToPay = (Vehicle) parkingManager.getParkedVehicles().get(licensePlateToPay);
        if (vehicleToPay != null) {
          boolean paymentSuccess = paymentSystem.processPayment(vehicleToPay);
          if (paymentSuccess) {
            float parkingFee = vehicleToPay.getParkingFee();
            view.displayMessage("Total parking fee: $ \n" + parkingFee);
            view.displayParkedDuration(
                Duration.between(vehicleToPay.getArrivalTime(), vehicleToPay.getPaymentTime()));
            view.displayMessage("Payment processed successfully! Please leave within 20 minutes.");
          } else {
            view.displayMessage("Payment Failed! Please Try Again.");
          }
        } else {
          view.displayMessage("Vehicle with license plate " + licensePlateToPay + " not found in the parking lot.");
        }
        break;
      case "Process to Leave Button":
        String licensePlateToProcess = view.getLicensePlateInput();
        Vehicle vehicleToProcess = (Vehicle) parkingManager.getParkedVehicles().get(licensePlateToProcess);
        if (vehicleToProcess != null) {
          if (paymentSystem.getPaidVehicles().containsKey(licensePlateToProcess)) {
            if (parkingManager.processToLeave(vehicleToProcess)) {
              view.displayMessage("Gate opened! See you next time!");
            } else {
              float amount = vehicleToProcess.rechargeParkingFee();
              view.displayMessage("Recharge Parking fee: $" + amount);
            }
          } else {
            view.displayMessage("Vehicle with license plate " + licensePlateToProcess + " has not paid the parking fee. Please pay first!");
          }
        } else {
          view.displayMessage("Vehicle with license plate " + licensePlateToProcess + " not found in the parking lot.");
        }
        break;
      case "Exit Button":
        exitProgram();
        break;
      default:
        view.showOptionError();
        break;
    }
  }

  /**
   * Exits the program.
   */
  @Override
  public void exitProgram() {
    System.exit(0);
  }
}