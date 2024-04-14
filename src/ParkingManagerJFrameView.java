import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ParkingManagerJFrameView extends JFrame implements IParkingManagerView {

  private JButton totalParkingCapacityButton;
  private JButton availableParkingCapacityButton;
  private JButton vehicleDetailsButton;
  private JButton membershipStatusButton;
  private JButton exitButton;

  String[] options = {"Total Parking Capacity", "Available Parking Capacity", "Vehicle Details", "Membership Status", "Exit"};

  public ParkingManagerJFrameView(String caption) {
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

    totalParkingCapacityButton.addActionListener(evt -> features.optionExecution("Total Parking Capacity"));
    availableParkingCapacityButton.addActionListener(evt -> features.optionExecution("Available Parking Capacity"));
    vehicleDetailsButton.addActionListener(evt -> features.optionExecution("Vehicle Details"));
    membershipStatusButton.addActionListener(evt -> features.optionExecution("Membership Status"));
    exitButton.addActionListener(evt -> features.optionExecution("Exit"));
  }

  @Override
  public void optionExecution(String option) {

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
