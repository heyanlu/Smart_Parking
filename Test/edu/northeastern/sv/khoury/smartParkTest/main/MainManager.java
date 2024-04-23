package edu.northeastern.sv.khoury.smartParkTest.main;

import edu.northeastern.sv.khoury.smartParkTest.controller.ParkingManagerController;
import edu.northeastern.sv.khoury.smartParkTest.model.ParkingManager;
import edu.northeastern.sv.khoury.smartParkTest.setUp.BaseSetUpTest;
import edu.northeastern.sv.khoury.smartParkTest.view.ParkingManagerJFrameView;

/**
 * The main function for the Parking Manager application.
 * It initializes and starts the application by setting up the controller and GUI.
 */
//Main is located here as I want it to use baseSetUpTest that I set up for test codes.
public class MainManager {

  /**
   * The main method for the Parking Manager application.
   * It initializes and starts the application by setting up the controller and GUI.
   *
   * @param args The command line arguments.
   */
  public static void main(String[] args) {
    BaseSetUpTest baseSetUpTest = new BaseSetUpTest();
    baseSetUpTest.setUp();

    ParkingManager parkingManager = BaseSetUpTest.parkingManager;
    ParkingManagerJFrameView frameView = new ParkingManagerJFrameView("Parking Manager");

    ParkingManagerController controller = new ParkingManagerController(parkingManager);
    frameView.addFeatures(controller);

    controller.setView(frameView);
  }
}

