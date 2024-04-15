package edu.northeastern.sv.khoury.smartParkTest.mock;

import edu.northeastern.sv.khoury.smartParkTest.controller.Feature;
import edu.northeastern.sv.khoury.smartParkTest.model.VehicleType;
import edu.northeastern.sv.khoury.smartParkTest.view.IParkingManagerView;
import javax.swing.*;

/**
 * This class represents a mock manager view for the Parking Manager application.
 * The purpose of this class is to test view separately.
 * The mock view provides buttons for viewing total parking capacity, available parking capacity, vehicle details,
 * membership status, and exiting the application. It also allows setting the license plate input and vehicle type
 * for testing purposes.
 */
public class ParkingManagerJFrameViewMock extends JFrame implements IParkingManagerView {
  private JButton totalParkingCapacityButton;
  private JButton availableParkingCapacityButton;
  private JButton vehicleDetailsButton;
  private JButton membershipStatusButton;
  private JButton exitButton;

  private String licensePlateInput;
  private VehicleType vehicleType;

  private String lastDisplayedMessage;
  private String input;

  /**
   * Constructor of mock manager view for the Parking Manager application.
   * Initializes the JFrame with buttons for viewing total parking capacity, available parking capacity,
   * vehicle details, membership status, and exiting the application. Sets up the layout and visibility of the frame.
   *
   * @param caption The caption/title of the JFrame.
   */
  public ParkingManagerJFrameViewMock(String caption) {
    super(caption);

    totalParkingCapacityButton = new JButton("Total Parking Capacity");
    availableParkingCapacityButton = new JButton("Available Parking Capacity");
    vehicleDetailsButton = new JButton("Vehicle Details");
    membershipStatusButton = new JButton("Membership Status");
    exitButton = new JButton("Exit");

    totalParkingCapacityButton.addActionListener(e -> optionExecution("Total Parking Capacity"));
    availableParkingCapacityButton.addActionListener(e -> optionExecution("Available Parking Capacity"));
    vehicleDetailsButton.addActionListener(e -> optionExecution("Vehicle Details"));
    membershipStatusButton.addActionListener(e -> optionExecution("Membership Status"));
    exitButton.addActionListener(e -> optionExecution("Exit"));

    // Create a panel and add buttons to it
    JPanel panel = new JPanel();
    panel.add(new JLabel("Choose an option:"));
    panel.add(totalParkingCapacityButton);
    panel.add(availableParkingCapacityButton);
    panel.add(vehicleDetailsButton);
    panel.add(membershipStatusButton);
    panel.add(exitButton);

    getContentPane().add(panel);

    pack();
    setVisible(true);
  }

  /**
   * Sets the vehicle type for testing purposes.
   * This setter function will help to test without the calling of controller.
   *
   * @param vehicleType The vehicle type.
   */
  public void setVehicleType(VehicleType vehicleType) {
    this.vehicleType = vehicleType;
  }

  /**
   * Sets the input for testing purposes.
   *
   * @param input The input value.
   */
  public void setInput(String input) {
    this.input = input;
  }

  /**
   * Sets the license plate input for testing purposes.
   *
   * @param licensePlateInput The license plate input.
   */
  public void setLicensePlateInput(String licensePlateInput) {
    this.licensePlateInput = licensePlateInput;
  }

  @Override
  public String getLicensePlateInput() {
    return licensePlateInput;
  }

  @Override
  public String getInput(String prompt) {
    return input;
  }

  /**
   * Gets the "Total Parking Capacity" button.
   *
   * @return The "Total Parking Capacity" button.
   */
  public JButton totalParkingCapacityButton() {
    return totalParkingCapacityButton;
  }

  /**
   * Gets the "Available Parking Capacity" button.
   *
   * @return The "Available Parking Capacity" button.
   */
  public JButton availableParkingCapacityButton() {
    return availableParkingCapacityButton;
  }

  /**
   * Gets the "Vehicle Details" button.
   *
   * @return The "Vehicle Details" button.
   */
  public JButton vehicleDetailsButton() {
    return vehicleDetailsButton;
  }

  /**
   * Gets the "Membership Status" button.
   *
   * @return The "Membership Status" button.
   */
  public JButton membershipStatusButton() {
    return membershipStatusButton;
  }

  /**
   * Gets the "Exit" button.
   *
   * @return The "Exit" button.
   */
  public JButton getExitButton() {
    return exitButton;
  }

  @Override
  public void optionExecution(String option) {
  }

  @Override
  public void displayMessage(String message) {
    lastDisplayedMessage = message;
  }

  /**
   * Gets the last displayed message.
   *
   * @return The last displayed message.
   */
  public String getLastDisplayedMessage() {
    return lastDisplayedMessage;
  }

  @Override
  public VehicleType chooseVehicleType() {
    return null;
  }

  @Override
  public void showOptionError() {
  }

  @Override
  public void addFeatures(Feature features) {
  }
}
