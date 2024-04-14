package edu.northeastern.sv.khoury.smartParkTest.view;

import edu.northeastern.sv.khoury.smartParkTest.controller.Feature;
import edu.northeastern.sv.khoury.smartParkTest.model.VehicleType;
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