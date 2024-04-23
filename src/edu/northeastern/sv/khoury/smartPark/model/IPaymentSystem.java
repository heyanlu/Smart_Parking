package edu.northeastern.sv.khoury.smartPark.model;

import java.util.Map;

/**
 * Interface of the payment system
 */
public interface IPaymentSystem {
  /**
   * Retrieves a hashmap of the paid vehicle where the license plate is the key and Vehicle is corresponding value.
   *
   * @return A hashmap containing the paid vehicles.
   */
  Map<String, Vehicle> getPaidVehicles();

  /**
   * Retrieves the total parking fee collected by the parking system
   *
   * @return total parking fee collected.
   */
  float getTotalParkingFees();

  /**
   * Processes to pay for the given vehicle.
   * My payment logic would be: if vehicle is process Payment, it will set up a temporary
   * paymentTime. If the payment is successful, it will return true, otherwise, it will
   * return false, and reset the paymentTime to null.
   *
   * @param vehicle The vehicle for which payment is to be processed.
   * @return True if the payment is successfully processed, false otherwise.
   */
  boolean processPayment(Vehicle vehicle);

}
