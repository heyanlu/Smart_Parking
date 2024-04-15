package edu.northeastern.sv.khoury.smartParkTest.mock;

import edu.northeastern.sv.khoury.smartParkTest.model.VehicleType;
import edu.northeastern.sv.khoury.smartParkTest.view.ParkingManagerJFrameView;

/**
 * This class represents a mock implementation of the parking manager view for testing purposes.
 * The mock view maintains input fields for license plates, messages to display, and a simulated vehicle type selection.
 * It overrides methods to set and retrieve vehicle type, license plate input, and displayed messages.
 */
public class ParkingManagerViewMock extends ParkingManagerJFrameView {
  private String licensePlateInput;
  private String message;
  private VehicleType vehicleType;

  /**
   * Constructs a new ParkingManagerViewMock with the specified title.
   *
   * @param title The title of the view.
   */
  public ParkingManagerViewMock(String title) {
    super(title);
  }

  @Override
  public VehicleType chooseVehicleType() {
    return vehicleType;
  }

  /**
   * Sets the vehicle type to be chosen by the user.
   *
   * @param vehicleType The vehicle type to set.
   */
  public void setVehicleType(VehicleType vehicleType) {
    this.vehicleType = vehicleType;
  }

  @Override
  public String getLicensePlateInput() {
    return licensePlateInput;
  }

  /**
   * Retrieves the license plate input entered by the user.
   *
   * @return The license plate input.
   */
  public void setLicensePlateInput(String licensePlateInput) {
    this.licensePlateInput = licensePlateInput;
  }

  @Override
  public void displayMessage(String message) {
    this.message = message;
  }

  /**
   * Retrieves the last displayed message in the view.
   *
   * @return The last displayed message.
   */
  public String getMessage() {
    return message;
  }

  @Override
  public void showOptionError() {
    System.out.println("Invalid option.");
  }

}
