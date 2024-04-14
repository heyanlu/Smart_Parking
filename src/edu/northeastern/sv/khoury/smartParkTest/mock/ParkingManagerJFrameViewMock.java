package edu.northeastern.sv.khoury.smartParkTest.mock;

import edu.northeastern.sv.khoury.smartParkTest.controller.Feature;
import edu.northeastern.sv.khoury.smartParkTest.model.VehicleType;
import edu.northeastern.sv.khoury.smartParkTest.view.IParkingManagerView;
import javax.swing.*;
import java.awt.*;

public class ParkingManagerJFrameViewMock extends JFrame implements IParkingManagerView {
  private JButton addMembershipButton;
  private JButton removeMembershipButton;
  private JButton getMembershipButton;
  private JButton getMembershipCountButton;
  private JButton exitButton;

  private String licensePlateInput;
  private VehicleType vehicleType;
  private String input;

  public ParkingManagerJFrameViewMock(String caption) {
    super(caption);

    setSize(500, 300);
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new FlowLayout());

    addMembershipButton = new JButton("Add edu.northeastern.sv.khoury.smartPark.model.Membership");
    addMembershipButton.setActionCommand("Add edu.northeastern.sv.khoury.smartPark.model.Membership Button");
    this.add(addMembershipButton);

    removeMembershipButton = new JButton("Remove edu.northeastern.sv.khoury.smartPark.model.Membership");
    removeMembershipButton.setActionCommand("Remove edu.northeastern.sv.khoury.smartPark.model.Membership Button");
    this.add(removeMembershipButton);

    getMembershipButton = new JButton("Get edu.northeastern.sv.khoury.smartPark.model.Membership");
    getMembershipButton.setActionCommand("Get edu.northeastern.sv.khoury.smartPark.model.Membership Button");
    this.add(getMembershipButton);

    getMembershipCountButton = new JButton("Get edu.northeastern.sv.khoury.smartPark.model.Membership Count");
    getMembershipCountButton.setActionCommand("Get edu.northeastern.sv.khoury.smartPark.model.Membership Count Button");
    this.add(getMembershipCountButton);

    exitButton = new JButton("Exit");
    exitButton.setActionCommand("Exit Button");
    this.add(exitButton);

    pack();
    setVisible(true);
  }

  public void setVehicleType(VehicleType vehicleType) {
    this.vehicleType = vehicleType;
  }

  public void setInput(String input) {
    this.input = input;
  }

  public JButton getAddMembershipButton() {
    return addMembershipButton;
  }

  public JButton getRemoveMembershipButton() {
    return removeMembershipButton;
  }

  public JButton getGetMembershipButton() {
    return getMembershipButton;
  }

  public JButton getGetMembershipCountButton() {
    return getMembershipCountButton;
  }

  public JButton getExitButton() {
    return exitButton;
  }

  // Methods from edu.northeastern.sv.khoury.smartPark.view.IParkingManagerView
  @Override
  public void optionExecution(String option) {
    // Implement according to your test needs
  }

  @Override
  public String getLicensePlateInput() {
    // Implement according to your test needs
    return licensePlateInput;
  }

  @Override
  public String getInput(String prompt) {
    return input;
  }

  @Override
  public void displayMessage(String message) {
    // Implement according to your test needs
  }

  @Override
  public VehicleType chooseVehicleType() {
    // Implement according to your test needs
    return null;
  }

  @Override
  public void showOptionError() {
    // Implement according to your test needs
  }

  @Override
  public void addFeatures(Feature features) {
    // Implement according to your test needs
  }
}
