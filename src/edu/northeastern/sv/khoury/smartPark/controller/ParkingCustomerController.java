package edu.northeastern.sv.khoury.smartPark.controller;

import edu.northeastern.sv.khoury.smartPark.model.IPaymentSystem;
import edu.northeastern.sv.khoury.smartPark.model.IParkingManager;
import edu.northeastern.sv.khoury.smartPark.model.Vehicle;
import edu.northeastern.sv.khoury.smartPark.model.VehicleType;
import edu.northeastern.sv.khoury.smartPark.view.IParkingCustomerView;
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
   * Constructor used for mainCustomer class
   * @param parkingManager The parking manager component.
   * @param paymentSystem  The payment system component.
   */
  public ParkingCustomerController(IParkingManager parkingManager, IPaymentSystem paymentSystem) {
    this.parkingManager = parkingManager;
    this.paymentSystem = paymentSystem;
  }

  /**
   * Constructor of ParkingCustomerController with parking manager, payment system, and view.
   * Constructor used for parkingCustomerControllerTest, as it takes one more parameter than main
   *
   * @param parkingManager The parking manager component.
   * @param view           The view component.
   */
  public ParkingCustomerController(IParkingManager parkingManager, IPaymentSystem paymentSystem, IParkingCustomerView view) {
    this(parkingManager, paymentSystem);
    this.view = view;
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


  @Override
  public void optionExecution(String option) {
    //To make this part of code easy to read, I separate each option into a method instead of nested them in one method.
    switch (option) {
      case "Park Vehicle Button":
        parkVehicle();
        break;
      case "Process Payment Button":
        processPayment();
        break;
      case "Process to Leave Button":
        processToLeave();
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
   * Parks a vehicle based on user input and displays corresponding messages.
   */
  private void parkVehicle() {
    //prompt the user to input vehicleType and licensePlate
    VehicleType vehicleType = view.chooseVehicleType();
    String licensePlate = view.getLicensePlateInput().toUpperCase();

    //create a vehicle object based on the user input
    Vehicle newVehicle = parkingManager.createVehicle(licensePlate, vehicleType);

    // Attempt to park the vehicle and display appropriate message
    if (parkingManager.parkVehicle(newVehicle)) {
      String newVehicleParkingPlace = parkingManager.assignParkingPlace(vehicleType);
      view.displayMessage("Vehicle parked successfully! Vehicle parking place: " + newVehicleParkingPlace);
    } else {
      view.displayMessage("Parking failed!");
    }
  }

  /**
   * Processes payment for a parked vehicle and displays appropriate messages.
   */
  private void processPayment() {
    //prompt the user to input licensePlate
    String licensePlateToPay = view.getLicensePlateInput().toUpperCase();

    //create a vehicle object based on the user input
    Vehicle vehicleToPay = parkingManager.getParkedVehicles().get(licensePlateToPay);

    //process payment based on the vehicle object and displays appropriate messages
    if (vehicleToPay != null) {
      boolean paymentSuccess = paymentSystem.processPayment(vehicleToPay);
      if (paymentSuccess) {
        float parkingFee = vehicleToPay.getParkingFee();
        view.displayMessage("Total parking fee: $ \n" + parkingFee);
        view.displayParkedDuration(Duration.between(vehicleToPay.getArrivalTime(), vehicleToPay.getPaymentTime()));
        view.displayMessage("Payment processed successfully! Please leave within 20 minutes.");
      } else {
        view.displayMessage("Payment Failed! Please Try Again.");
      }
    } else {
      view.displayMessage("Vehicle with license plate " + licensePlateToPay + " not found in the parking lot.");
    }
  }

  /**
   * Processes vehicle departure and displays appropriate messages.
   */
  private void processToLeave() {
    //prompt the user to input licensePlate
    String licensePlateToProcess = view.getLicensePlateInput().toUpperCase();

    //create a vehicle object based on the user input
    Vehicle vehicleToProcess = parkingManager.getParkedVehicles().get(licensePlateToProcess);

    //process leave based on the vehicle object and displays appropriate messages
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
  }

  @Override
  public void exitProgram() {
    System.exit(0);
  }
}