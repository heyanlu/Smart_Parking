package edu.northeastern.sv.khoury.smartParkTest.mock;

import edu.northeastern.sv.khoury.smartParkTest.controller.Feature;
import edu.northeastern.sv.khoury.smartParkTest.model.VehicleType;
import edu.northeastern.sv.khoury.smartParkTest.view.IParkingCustomerView;
import java.time.Duration;

/**
 * This class represents a mock customer view for the Parking Manager application,
 * The mock view allows setting and retrieving license plate input, displaying messages, choosing vehicle types,
 * getting user input, displaying parked duration, and clearing input for testing purposes.
 */
public class ParkingCustomerViewMock implements IParkingCustomerView {

  private String licensePlateInput;
  private String message;
  private VehicleType vehicleType;
  private Duration parkedDuration;
  private String inputPrompt;
  private String input;

  @Override
  public String getLicensePlateInput() {
    return licensePlateInput;
  }

  /**
   * Sets the license plate input for testing purposes.
   * This setter function will help to test without the calling of controller.
   *
   * @param licensePlateInput The license plate input.
   */
  public void setLicensePlateInput(String licensePlateInput) {
    this.licensePlateInput = licensePlateInput;
  }

  @Override
  public void addFeatures(Feature features) {
    // Mock method, not implemented
  }

  @Override
  public void displayMessage(String message) {
    this.message = message;
  }

  /**
   * Gets the message.
   *
   * @return The message to be displayed.
   */
  public String getMessage() {
    return message;
  }

  @Override
  public VehicleType chooseVehicleType() {
    return vehicleType;
  }

  /**
   * Sets the vehicle type for testing purposes.
   *
   * @param vehicleType The vehicle type to set.
   */
  public void setVehicleType(VehicleType vehicleType) {
    this.vehicleType = vehicleType;
  }

  @Override
  public String getInput(String prompt) {
    this.inputPrompt = prompt;
    return input;
  }

  @Override
  public void displayParkedDuration(Duration duration) {
    this.parkedDuration = duration;
  }

  /**
   * Gets the parked duration.
   *
   * @return The parked duration.
   */
  public Duration getParkedDuration() {
    return parkedDuration;
  }

  @Override
  public void showOptionError() {
    // Mock method, not implemented
  }

}
