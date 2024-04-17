package edu.northeastern.sv.khoury.smartParkTest.view;

import edu.northeastern.sv.khoury.smartParkTest.controller.Feature;
import edu.northeastern.sv.khoury.smartParkTest.model.VehicleType;

/**
 * This interface defines the methods required for a parking manager view.
 */
public interface IParkingManagerView {
  /**
   * Prompts the user to input their vehicle's license plate.
   * License Plate could include characters and numbers.
   *
   * @return The license plate input by the user.
   */
  String getLicensePlateInput();

  /**
   * Adds features to the parking view.
   * Feature is a event listener, it will prompt manager to choose different button: get total capacity,
   * get available capacity, membership status, vehicle details, exit.
   *
   * @param features The feature to be added.
   */
  void addFeatures(Feature features);

  /**
   * Prompts the user to input data with the specified prompt, like license plate numbers.
   *
   * @param prompt The prompt displayed to the user.
   * @return The input provided by the user.
   */
  String getInput(String prompt);

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
   * Displays an error message for invalid options.
   */
  void showOptionError();

}


