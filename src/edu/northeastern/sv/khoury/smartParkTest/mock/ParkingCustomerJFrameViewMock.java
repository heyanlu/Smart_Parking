package edu.northeastern.sv.khoury.smartParkTest.mock;

import edu.northeastern.sv.khoury.smartParkTest.controller.Feature;
import edu.northeastern.sv.khoury.smartParkTest.model.VehicleType;
import edu.northeastern.sv.khoury.smartParkTest.view.IParkingCustomerView;
import javax.swing.*;
import java.awt.*;
import java.time.Duration;

public class ParkingCustomerJFrameViewMock extends JFrame implements IParkingCustomerView {
  private JButton parkVehicleButton;
  private JButton processPaymentButton;
  private JButton leaveParkingLotButton;
  private JButton exitButton;

  private String licensePlateInput;
  private VehicleType vehicleType;
  private String input;

  private Duration lastDisplayedDuration;


  public ParkingCustomerJFrameViewMock(String caption) {
    super(caption);

    setSize(500, 300);
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new FlowLayout());

    parkVehicleButton = new JButton("Park edu.northeastern.sv.khoury.smartPark.model.Vehicle");
    parkVehicleButton.setActionCommand("Park edu.northeastern.sv.khoury.smartPark.model.Vehicle Button");
    this.add(parkVehicleButton);

    processPaymentButton = new JButton("Process Payment");
    processPaymentButton.setActionCommand("Process Payment Button");
    this.add(processPaymentButton);

    leaveParkingLotButton = new JButton("Process to Leave");
    leaveParkingLotButton.setActionCommand("Process to Leave Button");
    this.add(leaveParkingLotButton);

    exitButton = new JButton("Exit");
    exitButton.setActionCommand("Exit Button");
    this.add(exitButton);

    pack();
    setVisible(true);
  }

  public void setLicensePlateInput(String licensePlateInput) {
    this.licensePlateInput = licensePlateInput;
  }

  public void setVehicleType(VehicleType vehicleType) {
    this.vehicleType = vehicleType;
  }

  public void setInput(String input) {
    this.input = input;
  }

  // Getter methods for buttons
  public JButton getParkVehicleButton() {
    return parkVehicleButton;
  }

  public JButton getProcessPaymentButton() {
    return processPaymentButton;
  }

  public JButton getLeaveParkingLotButton() {
    return leaveParkingLotButton;
  }

  public JButton getExitButton() {
    return exitButton;
  }

  @Override
  public String getLicensePlateInput() {
    return licensePlateInput;
  }

  @Override
  public void addFeatures(Feature features) {
    // Implement according to your test needs
  }

  @Override
  public void displayMessage(String message) {
    // Implement according to your test needs
  }

  @Override
  public VehicleType chooseVehicleType() {
    return vehicleType;
  }

  @Override
  public String getInput(String prompt) {
    return input;
  }

  @Override
  public void displayParkedDuration(Duration duration) {
    this.lastDisplayedDuration = duration;
  }

  public Duration getLastDisplayedDuration() {
    return lastDisplayedDuration;
  }

  @Override
  public void showOptionError() {
    // Implement according to your test needs
  }
}
