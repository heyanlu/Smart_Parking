package edu.northeastern.sv.khoury.smartPark.model;

import java.time.LocalDateTime;

/**
 * Interface representing a vehicle.
 */
public interface Vehicle {

  /**
   * Gets the type of the vehicle.
   * A vehicle type will contain: license plate, vehicle type, arrival time, payment time, leave time, membership
   *
   * @return The type of the vehicle.
   */
  VehicleType getType();

  /**
   * Gets the license plate of a vehicle object.
   *
   * @return The license plate of a vehicle object.
   */
  String getLicensePlate();

  /**
   * Sets the arrival time of the vehicle.
   * The arrival time will be updated when parkedVehicle method is called.
   *
   * @param arrivalTime The time when the vehicle arrived.
   */
  void setArrivalTime(LocalDateTime arrivalTime);

  /**
   * Sets the leave time of the vehicle.
   * The leave time will be updated when vehicle object process to leave.
   *
   * @param leaveTime The current time representing the leave time.
   */
  void setLeaveTime(LocalDateTime leaveTime);

  /**
   * Sets the payment time of the vehicle.
   * The payment time will be updated when vehicle object process to pay.
   *
   * @param paymentTime The time when the vehicle object successfully pay the parking fee.
   */
  void setPaymentTime(LocalDateTime paymentTime);

  /**
   * Gets the arrival time of the vehicle object.
   *
   * @return The arrival time of the vehicle object.
   */
  LocalDateTime getArrivalTime();

  /**
   * Gets the payment time of the vehicle object.
   *
   * @return The payment time of the vehicle object.
   */
  LocalDateTime getPaymentTime();

  /**
   * Gets the leave time of the vehicle object.
   *
   * @return The leave time of the vehicle object.
   */
  LocalDateTime getLeaveTime();

  /**
   * Gets the parking rate for the vehicle object.
   *
   * @return The parking rate for the vehicle object.
   */
  float getParkingRate();

  /**
   * Gets the parking fee for the vehicle.
   *
   * @return The parking fee for the vehicle.
   */
  float getParkingFee();

  /**
   * Checks if the vehicle object has membership.
   *
   * @return True if the vehicle object has membership, false otherwise.
   */
  boolean isMembership();

  /**
   * Recharges the parking fee for the vehicle object.
   * Recharge parking fee will generate when the vehicle object does not leave parking lot within
   * a stipulated time after paying the parking fee.
   *
   * @return The recharged parking fee.
   * @throws IllegalStateException When the vehicle does not pay before process to leave.
   */
  float rechargeParkingFee() throws IllegalStateException;

  /**
   * Checks if the vehicle object paid for recharging fee.
   *
   * @return True if the parking fee is paid for recharging, false otherwise.
   */
  boolean isPaidRechargeParkingFee();

}
