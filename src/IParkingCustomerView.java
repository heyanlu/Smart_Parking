import java.time.Duration;

public interface IParkingCustomerView {

  String getLicensePlateInput();


  void addFeatures(Feature features);

  void displayMessage(String message);

  VehicleType chooseVehicleType();

  String getInput(String prompt);

  void displayParkedDuration(Duration duration);

  void showOptionError();
}