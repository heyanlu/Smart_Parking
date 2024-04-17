package edu.northeastern.sv.khoury.smartParkTest.main;

import edu.northeastern.sv.khoury.smartParkTest.controller.ParkingCustomerController;
import edu.northeastern.sv.khoury.smartParkTest.model.ParkingManager;
import edu.northeastern.sv.khoury.smartParkTest.model.PaymentSystem;
import edu.northeastern.sv.khoury.smartParkTest.setUp.BaseSetUpTest;
import edu.northeastern.sv.khoury.smartParkTest.view.ParkingCustomerJFrameView;

/**
 * This class is the main for the Smart Park application for customers.
 * It initializes the necessary components for customer interactions with the parking system,
 * such as the parking manager, payment system, and the customer view.
 * It sets up the customer controller and associates it with the customer view.
 */
public class MainCustomer{
  /**
   * The main method for starting the Smart Park application for customers.
   * It initializes the necessary components, including the parking manager, payment system,
   * and customer view. It sets up the customer controller and associates it with the customer view.
   *
   * @param args The command-line arguments (not used).
   */
  public static void main(String[] args) {
    BaseSetUpTest baseSetUpTest = new BaseSetUpTest();
    baseSetUpTest.setUp();

    ParkingManager parkingManager = BaseSetUpTest.parkingManager;
    PaymentSystem paymentSystem = BaseSetUpTest.paymentSystem;

    ParkingCustomerJFrameView frameView = new ParkingCustomerJFrameView("Parking Manager");
    ParkingCustomerController controller = new ParkingCustomerController(parkingManager, paymentSystem);

    frameView.addFeatures(controller);
    controller.setView(frameView);
  }

}
