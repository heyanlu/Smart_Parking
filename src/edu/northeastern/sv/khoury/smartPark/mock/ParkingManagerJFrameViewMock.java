package edu.northeastern.sv.khoury.smartPark.mock;

import edu.northeastern.sv.khoury.smartPark.controller.Feature;
import edu.northeastern.sv.khoury.smartPark.model.VehicleType;
import edu.northeastern.sv.khoury.smartPark.view.IParkingManagerView;

/**
 * This class represents a mock manager view for the Parking Manager application.
 * The purpose of this class is to test view separately.
 * The mock view provides buttons for viewing total parking capacity, available parking capacity, vehicle details,
 * membership status, and exiting the application. It also allows setting the license plate input and vehicle type
 * for testing purposes.
 */
public class ParkingManagerJFrameViewMock implements IParkingManagerView {
  private StringBuilder log;

  public ParkingManagerJFrameViewMock(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void addFeatures(Feature features) {
    log.append("Mock addFeatures called.");
  }

  @Override
  public String getLicensePlateInput() {
    log.append("Mock getLicensePlateInput called.");
    return "";
  }

  @Override
  public String getInput(String prompt) {
    log.append("Mock getInput called.");
    return "";
  }

  @Override
  public void displayMessage(String message) {
    log.append("Mock displayMessage called.");
  }

  @Override
  public VehicleType chooseVehicleType() {
    log.append("Mock chooseVehicleType called.");
    return null;
    }


  @Override
  public void showOptionError() {
    log.append("Mock showOptionError called.");
  }

}