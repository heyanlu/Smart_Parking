package edu.northeastern.sv.khoury.smartParkTest.mock;

import edu.northeastern.sv.khoury.smartParkTest.controller.Feature;
import edu.northeastern.sv.khoury.smartParkTest.model.VehicleType;
import edu.northeastern.sv.khoury.smartParkTest.view.IParkingCustomerView;
import javax.swing.*;
import java.awt.*;
import java.time.Duration;

/**
 * This class represents a mock customer view for the Parking Manager application.
 * The purpose of mock view is to test view and controller part separately.
 * The mock view provides buttons for parking a vehicle, processing payment, leaving the parking lot, and exiting
 * the application. It also allows setting the license plate input, vehicle type, and input for testing purposes.
 */
public class ParkingCustomerJFrameViewMock extends JFrame implements IParkingCustomerView {
  private JButton parkVehicleButton;
  private JButton processPaymentButton;
  private JButton leaveParkingLotButton;
  private JButton exitButton;
  private String licensePlateInput;
  private VehicleType vehicleType;
  private String input;
  private Duration lastDisplayedDuration;


  /**
   * Constructs a mock customer view for the Parking Manager application.
   * Initializes the JFrame with buttons for parking a vehicle, processing payment, leaving the parking lot,
   * and exiting the application. Sets up the layout and visibility of the frame.
   *
   * @param caption The caption/title of the JFrame.
   */
  public ParkingCustomerJFrameViewMock(String caption) {
    super(caption);

    setSize(500, 300);
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new FlowLayout());

    parkVehicleButton = new JButton("Park Vehicle");
    parkVehicleButton.setActionCommand("Park Vehicle Button");
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

  /**
   * Sets the license plate input for testing purposes.
   * This setter function will help to test ParkingCustomerJFrameView without the calling of controller.
   *
   * @param licensePlateInput The license plate input.
   */
  public void setLicensePlateInput(String licensePlateInput) {
    this.licensePlateInput = licensePlateInput;
  }

  /**
   * Sets the vehicle type for testing purposes.
   * This setter function will help to test ParkingCustomerJFrameView without the calling of controller.
   *
   * @param vehicleType The vehicle type.
   */
  public void setVehicleType(VehicleType vehicleType) {
    this.vehicleType = vehicleType;
  }

  /**
   * Sets the input for testing purposes.
   * This setter function will help to test ParkingCustomerJFrameView without the calling of controller.
   *
   * @param input The input value.
   */
  public void setInput(String input) {
    this.input = input;
  }

  /**
   * Gets the "Park Vehicle" button.
   * This getter function will help to test ParkingCustomerJFrameView without the calling of controller.
   *
   * @return The "Park Vehicle" button.
   */
  public JButton getParkVehicleButton() {
    return parkVehicleButton;
  }

  /**
   * Gets the "Process Payment" button.
   * This getter function will help to test ParkingCustomerJFrameView without the calling of controller.
   *
   * @return The "Process Payment" button.
   */
  public JButton getProcessPaymentButton() {
    return processPaymentButton;
  }

  /**
   * Gets the "Process to Leave" button.
   * This getter function will help to test ParkingCustomerJFrameView without the calling of controller.
   *
   * @return The "Process to Leave" button.
   */
  public JButton getLeaveParkingLotButton() {
    return leaveParkingLotButton;
  }

  /**
   * Gets the "Exit" button.
   * This getter function will help to test ParkingCustomerJFrameView without the calling of controller.
   *
   * @return The "Exit" button.
   */
  public JButton getExitButton() {
    return exitButton;
  }

  @Override
  public String getLicensePlateInput() {
    return licensePlateInput;
  }

  @Override
  public void addFeatures(Feature features) {
  }

  @Override
  public void displayMessage(String message) {
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

  /**
   * Gets the last displayed duration.
   *
   * @return The last displayed duration.
   */
  public Duration getLastDisplayedDuration() {
    return lastDisplayedDuration;
  }

  @Override
  public void showOptionError() {
  }
}
