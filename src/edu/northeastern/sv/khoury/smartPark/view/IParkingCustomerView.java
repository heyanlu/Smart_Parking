package edu.northeastern.sv.khoury.smartPark.view;

import edu.northeastern.sv.khoury.smartPark.controller.Feature;
import edu.northeastern.sv.khoury.smartPark.model.VehicleType;
import java.time.Duration;

/**
 * This interface defines the methods required for a parking customer view.
 */
public interface IParkingCustomerView {
  /**
   * Prompts the user to input their vehicle's license plate.
   * License Plate could include characters and numbers.
   *
   * @return The license plate input by the user.
   */
  String getLicensePlateInput();

  /**
   * Adds features to the parking view.
   * Feature  is a event listener, it will prompt user to choose different parking service button: park vehicle,
   * process to pay, process to pay, and exit. Then call the corresponding methods.
   *
   * @param features The feature to be added.
   */
  void addFeatures(Feature features);

  /**
   * Displays a message to the user.
   *
   * @param message The message to be displayed.
   */
  void displayMessage(String message);

  /**
   * Prompts the user to choose a vehicle type.
   *
   * @return The chosen vehicle type.
   */
  VehicleType chooseVehicleType();

  /**
   * Prompts the user to input data with the specified prompt, like license plate numbers.
   *
   * @param prompt The prompt displayed to the user.
   * @return The input provided by the user.
   */
  String getInput(String prompt);

  /**
   * Displays the parked duration of a vehicle.
   *
   * @param duration The duration for which the vehicle has been parked.
   */
  void displayParkedDuration(Duration duration);

  /**
   * Displays an error message for invalid options.
   */
  void showOptionError();
}