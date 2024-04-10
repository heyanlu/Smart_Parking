public interface IParkingManagerView {
  void optionExecution(String option);
  String getLicensePlateInput();
  String getInput(String prompt);
  void displayMessage(String message);
  VehicleType chooseVehicleType();
  void showOptionError();
  void addFeatures(Feature features);
}


