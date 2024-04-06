import java.time.Duration;
import java.time.LocalDateTime;
import javax.swing.*;

public class ParkingSystemView {

  public static int displayMenuAndGetOption() {
    String[] options = {"Park Vehicle", "Process Payment", "Leave Parking Lot"};
    return JOptionPane.showOptionDialog(null, "Choose an option:", "Parking System",
        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
  }

  public static void displayMessage(String message) {
    JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.INFORMATION_MESSAGE);
  }


  public static VehicleType chooseVehicleType() {
    String[] vehicleTypes = {"CAR", "TRUCK", "MOTORBIKE"};
    String selectedType = (String) JOptionPane.showInputDialog(null, "Choose vehicle type:", "Select Vehicle Type",
        JOptionPane.QUESTION_MESSAGE, null, vehicleTypes, vehicleTypes[0]);
    return VehicleType.valueOf(selectedType);
  }


  public static String getInput(String prompt) {
    return JOptionPane.showInputDialog(null, prompt);
  }

  public static void displayParkedDuration(LocalDateTime arrivalTime, LocalDateTime leaveTime) {
    Duration duration = Duration.between(arrivalTime, leaveTime);
    long hours = duration.toHours();
    long minutes = duration.toMinutes() % 60;
    long seconds = duration.getSeconds() % 60;

    String message = String.format("Parked Duration: %d hours, %d minutes, %d seconds", hours, minutes, seconds);
    JOptionPane.showMessageDialog(null, message, "Parked Duration", JOptionPane.INFORMATION_MESSAGE);
  }

}
