package edu.northeastern.sv.khoury.smartParkTest.mock;

import edu.northeastern.sv.khoury.smartParkTest.controller.Feature;
import edu.northeastern.sv.khoury.smartParkTest.model.VehicleType;
import edu.northeastern.sv.khoury.smartParkTest.view.IParkingCustomerView;
import java.time.Duration;

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

  public String getMessage() {
    return message;
  }

  @Override
  public VehicleType chooseVehicleType() {
    return vehicleType;
  }

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

  public Duration getParkedDuration() {
    return parkedDuration;
  }

  @Override
  public void showOptionError() {
    // Mock method, not implemented
  }


  public String getInput() {
    return input;
  }

  public void clearInput() {
    this.input = null;
  }
}
