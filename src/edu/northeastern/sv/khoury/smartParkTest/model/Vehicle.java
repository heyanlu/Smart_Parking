package edu.northeastern.sv.khoury.smartParkTest.model;

import java.time.LocalDateTime;

/**
 * Interface representing a vehicle.
 */
public interface Vehicle {

  /**
   * Gets the type of the vehicle.
   *
   * @return The type of the vehicle.
   */
  VehicleType getType();

  String getLicensePlate();

  /**
   * Sets the arrival time of the vehicle.
   *
   * @param arrivalTime The time when the vehicle arrived.
   */
  void setArrivalTime(LocalDateTime arrivalTime);

  /**
   * Sets the leave time of the vehicle.
   *
   * @param now The current time representing the leave time.
   */
  void setLeaveTime(LocalDateTime now);

  /**
   * Sets the payment time of the vehicle.
   *
   * @param leaveTime The time when the vehicle left.
   */
  void setPaymentTime(LocalDateTime leaveTime);

  /**
   * Gets the arrival time of the vehicle.
   *
   * @return The arrival time of the vehicle.
   */
  LocalDateTime getArrivalTime();

  /**
   * Gets the payment time of the vehicle.
   *
   * @return The payment time of the vehicle.
   */
  LocalDateTime getPaymentTime();

  /**
   * Gets the leave time of the vehicle.
   *
   * @return The leave time of the vehicle.
   */
  LocalDateTime getLeaveTime();

  /**
   * Checks if the vehicle has membership.
   *
   * @return True if the vehicle has membership, false otherwise.
   */
  boolean isMembership();

  /**
   * Gets the parking rate for the vehicle.
   *
   * @return The parking rate for the vehicle.
   */
  float getParkingRate();

  /**
   * Gets the parking fee for the vehicle.
   *
   * @return The parking fee for the vehicle.
   */
  float getParkingFee();


  /**
   * Recharges the parking fee for the vehicle.
   * @return The recharged parking fee.
   * @throws IllegalStateException When the vehicle does not pay before process to leave.
   */
  float rechargeParkingFee() throws IllegalStateException;

  /**
   * Checks if the parking fee is paid for recharging.
   *
   * @return True if the parking fee is paid for recharging, false otherwise.
   */
  boolean isPaidRechargeParkingFee();

}
