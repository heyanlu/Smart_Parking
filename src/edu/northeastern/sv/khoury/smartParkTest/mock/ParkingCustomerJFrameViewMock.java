package edu.northeastern.sv.khoury.smartParkTest.mock;

import edu.northeastern.sv.khoury.smartParkTest.controller.Feature;
import edu.northeastern.sv.khoury.smartParkTest.model.VehicleType;
import edu.northeastern.sv.khoury.smartParkTest.view.IParkingCustomerView;
import java.time.Duration;

/**
 * This class provides a mock implementation of the IParkingCustomerView interface for testing purposes.
 * It does not go into the details of the methods, it checks whether the input and output can be properly
 * updated.
 */
public class ParkingCustomerJFrameViewMock implements IParkingCustomerView {
  private StringBuilder log;

  /**
   * Constructs a ParkingCustomerJFrameViewMock object with the specified log.
   *
   * @param log The StringBuilder object used for logging method calls.
   */
  public ParkingCustomerJFrameViewMock(StringBuilder log) {
    this.log = log;
  }

  @Override
  public String getLicensePlateInput() {
    log.append("Mock getLicensePlateInput called.");
    return "";
  }

  @Override
  public void addFeatures(Feature features) {
    log.append("Mock addFeatures called.");
  }

  @Override
  public String getInput(String prompt) {
    log.append("Mock getInput called.");
    return "";
  }

  @Override
  public void displayParkedDuration(Duration duration) {
    log.append("Mock displayParkedDuration called.");
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
