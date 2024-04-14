package edu.northeastern.sv.khoury.smartParkTest.mock;

import edu.northeastern.sv.khoury.smartParkTest.model.VehicleType;
import edu.northeastern.sv.khoury.smartParkTest.view.ParkingManagerJFrameView;

public class ParkingManagerViewMock extends ParkingManagerJFrameView {
  private String licensePlateInput;
  private String message;
  private VehicleType vehicleType;


  public ParkingManagerViewMock(String title) {
    super(title);
  }

  @Override
  public VehicleType chooseVehicleType() {
    return vehicleType;
  }

  public void setVehicleType(VehicleType vehicleType) {
    this.vehicleType = vehicleType;
  }

  @Override
  public String getLicensePlateInput() {
    return licensePlateInput;
  }

  public void setLicensePlateInput(String licensePlateInput) {
    this.licensePlateInput = licensePlateInput;
  }

  @Override
  public void displayMessage(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  @Override
  public void showOptionError() {
    System.out.println("Invalid option.");
  }


}
