package edu.northeastern.sv.khoury.smartPark.view;

import edu.northeastern.sv.khoury.smartPark.controller.Feature;
import edu.northeastern.sv.khoury.smartPark.model.VehicleType;
import java.time.Duration;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The ParkingCustomerJFrameView class implements the IParkingCustomerView interface
 * and provides a graphical user interface (GUI) for the parking customer.
 * It contains buttons for parking a vehicle, processing payment, leaving the parking lot,
 * and exiting the program.
 */
public class ParkingCustomerJFrameView extends JFrame implements IParkingCustomerView {
  private JButton parkVehicleButton;
  private JButton processPaymentButton;
  private JButton leaveParkingLotButton;
  private JButton exitButton;

  /**
   * Constructor for ParkingCustomerJFrameView.
   *
   * @param caption The title of the JFrame.
   */
  public ParkingCustomerJFrameView(String caption) {
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

  @Override
  public void addFeatures(Feature features) {
    for(ActionListener listener : parkVehicleButton.getActionListeners()) {
      parkVehicleButton.removeActionListener(listener);
    }
    for(ActionListener listener : processPaymentButton.getActionListeners()) {
      processPaymentButton.removeActionListener(listener);
    }
    for(ActionListener listener : leaveParkingLotButton.getActionListeners()) {
      leaveParkingLotButton.removeActionListener(listener);
    }
    for(ActionListener listener : exitButton.getActionListeners()) {
      exitButton.removeActionListener(listener);
    }

    parkVehicleButton.addActionListener(evt -> features.optionExecution("Park Vehicle Button"));
    processPaymentButton.addActionListener(evt -> features.optionExecution("Process Payment Button"));
    leaveParkingLotButton.addActionListener(evt -> features.optionExecution("Process to Leave Button"));
    exitButton.addActionListener(evt -> features.optionExecution("Exit Button"));
  }


  @Override
  public void displayMessage(String message) {
    JOptionPane.showMessageDialog(this, message, "Message", JOptionPane.INFORMATION_MESSAGE);
  }


  @Override
  public VehicleType chooseVehicleType() {
    String[] vehicleTypes = {"CAR", "TRUCK", "MOTORBIKE"};
    String selectedType = (String) JOptionPane.showInputDialog(this, "Choose vehicle type:", "Select edu.northeastern.sv.khoury.smartPark.model.Vehicle Type",
        JOptionPane.QUESTION_MESSAGE, null, vehicleTypes, vehicleTypes[0]);
    if (selectedType != null) {
      return VehicleType.valueOf(selectedType);
    } else {
      return null;
    }
  }


  @Override
  public String getLicensePlateInput() {
    return getInput("Enter your license plate number: ");
  }


  @Override
  public String getInput(String prompt) {
    return JOptionPane.showInputDialog(null, prompt);
  }


  @Override
  public void displayParkedDuration(Duration duration) {
    long hours = duration.toHours();
    long minutes = duration.toMinutes() % 60;
    long seconds = duration.getSeconds() % 60;

    String formattedDuration = String.format("%02d:%02d:%02d", hours, minutes, seconds);
    JOptionPane.showMessageDialog(null, "Parked Duration: " + formattedDuration, "Parked Duration", JOptionPane.INFORMATION_MESSAGE);
  }


  @Override
  public void showOptionError() {
    JOptionPane.showMessageDialog(this, "Invalid option. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
  }
}
