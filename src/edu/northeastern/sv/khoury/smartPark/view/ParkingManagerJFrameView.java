package edu.northeastern.sv.khoury.smartPark.view;

import edu.northeastern.sv.khoury.smartPark.controller.Feature;
import edu.northeastern.sv.khoury.smartPark.model.VehicleType;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * The ParkingManagerJFrameView class implements the IParkingManagerView interface
 * and provides a graphical user interface (GUI) for the parking manager.
 * It allows the user to perform various operations such as checking total parking capacity,
 * available parking capacity, vehicle details, membership status, and exiting the program.
 */
public class ParkingManagerJFrameView extends JFrame implements IParkingManagerView {

  private JButton totalParkingCapacityButton;
  private JButton availableParkingCapacityButton;
  private JButton vehicleDetailsButton;
  private JButton membershipStatusButton;
  private JButton exitButton;

  /**
   * Constructor for ParkingManagerJFrameView.
   *
   * @param caption The title of the JFrame.
   */
  public ParkingManagerJFrameView(String caption) {
    super(caption);

    setSize(500, 300);
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new FlowLayout());

    totalParkingCapacityButton = new JButton("Total Parking Capacity");
    totalParkingCapacityButton.setActionCommand("Total Parking Capacity Button");
    this.add(totalParkingCapacityButton);

    availableParkingCapacityButton = new JButton("Available Parking Capacity");
    availableParkingCapacityButton.setActionCommand("Available Parking Capacity Button");
    this.add(availableParkingCapacityButton);

    vehicleDetailsButton = new JButton("Vehicle Details");
    vehicleDetailsButton.setActionCommand("Vehicle Details Button");
    this.add(vehicleDetailsButton);

    membershipStatusButton = new JButton("Membership Status");
    membershipStatusButton.setActionCommand("Membership Status Button");
    this.add(membershipStatusButton);

    exitButton = new JButton("Exit");
    exitButton.setActionCommand("Exit Button");
    this.add(exitButton);

    pack();
    setVisible(true);
  }


  @Override
  public void addFeatures(Feature features) {
    for(ActionListener listener : totalParkingCapacityButton.getActionListeners()) {
      totalParkingCapacityButton.removeActionListener(listener);
    }
    for(ActionListener listener : availableParkingCapacityButton.getActionListeners()) {
      availableParkingCapacityButton.removeActionListener(listener);
    }
    for(ActionListener listener : vehicleDetailsButton.getActionListeners()) {
      vehicleDetailsButton.removeActionListener(listener);
    }
    for(ActionListener listener : membershipStatusButton.getActionListeners()) {
      membershipStatusButton.removeActionListener(listener);
    }
    for(ActionListener listener : exitButton.getActionListeners()) {
      exitButton.removeActionListener(listener);
    }

    totalParkingCapacityButton.addActionListener(evt -> features.optionExecution("Total Parking Capacity Button"));
    availableParkingCapacityButton.addActionListener(evt -> features.optionExecution("Available Parking Capacity Button"));
    vehicleDetailsButton.addActionListener(evt -> features.optionExecution("Vehicle Details Button"));
    membershipStatusButton.addActionListener(evt -> features.optionExecution("Membership Status Button"));
    exitButton.addActionListener(evt -> features.optionExecution("Exit Button"));
  }

  @Override
  public String getLicensePlateInput() {
    return getInput("Enter vehicle license plate number: ");
  }

  @Override
  public String getInput(String prompt) {
    return JOptionPane.showInputDialog(null, prompt);
  }

  @Override
  public void displayMessage(String message) {
    JOptionPane.showMessageDialog(this, message, "Message", JOptionPane.INFORMATION_MESSAGE);
  }

  @Override
  public VehicleType chooseVehicleType() {
    String[] vehicleTypes = {"CAR", "TRUCK", "MOTORBIKE"};
    String selectedType = (String) JOptionPane.showInputDialog(this, "Choose vehicle type:", "Select Vehicle Type",
        JOptionPane.QUESTION_MESSAGE, null, vehicleTypes, vehicleTypes[0]);
    if (selectedType != null) {
      return VehicleType.valueOf(selectedType);
    } else {
      return null;
    }
  }

  @Override
  public void showOptionError() {
    JOptionPane.showMessageDialog(this, "Invalid option. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
  }
}
