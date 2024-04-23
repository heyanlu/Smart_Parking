package edu.northeastern.sv.khoury.smartPark.model;

import java.util.Map;
import java.util.function.Predicate;

/**
 * Interface of parking system containing operations for managing parking lot
 */
public interface IParkingManager {

  /**
   * Get the membership system of the program.
   * The membership system is a hashmap where the license plate serves as the key, while the membership status,
   * including type, start time, and end time, acts as the corresponding value.
   *
   * @return The membership system.
   */
  MembershipSystem getMembershipSystem();

  /**
   * Get the hashmap of the parked vehicles in the parking lot where the license plate is the key, the vehicle is
   * the corresponding value.
   *
   * @return The map of parked vehicles.
   */
  Map<String, Vehicle> getParkedVehicles();

  /**
   * Get the total capacity of the parking lot for the specified vehicle type.
   *
   * @param vehicleType The type of vehicle (Car, Truck, Motorbike).
   * @return The total capacity of parking spaces for the specified vehicle type.
   */
  int getTotalCapacity(VehicleType vehicleType);

  /**
   * Get the number of occupied parking spaces for the specified vehicle type.
   *
   * @param vehicleType  The type of vehicle (Car, Truck, Motorbike).
   * @return The total capacity of parking spaces for the specified vehicle type.
   */
  int getOccupiedSpaces(VehicleType vehicleType);

  /**
   * Get the number of available parking spaces for the specified vehicle type.
   *
   * @param vehicleType The type of vehicle (Car, Truck, Motorbike).
   * @return The number of available parking spaces for the specified vehicle type.
   */
  int getAvailableSpaces(VehicleType vehicleType);

  /**
   * Checks if the given vehicle is a member vehicle.
   *
   * @param vehicle The vehicle to check membership for.
   * @return True if the vehicle is a member vehicle, false otherwise.
   */
  boolean isMemberVehicle(Vehicle vehicle);

  /**
   * Creates a new Vehicle object based on the given license plate and vehicle type.
   *
   * @param licensePlate The licensePlate of vehicle.
   * @param type The type of vehicle (Car, Truck, Motorbike).
   * @return A new Vehicle object.
   */
  Vehicle createVehicle(String licensePlate, VehicleType type);

  /**
   * Parks the specified vehicle in the parking lot.
   * If a vehicle is parked in the parking lot, it will be updated to parkedVehicles list and the corresponding
   * occupied space will increase by 1.
   *
   * @param vehicle The vehicle to park.
   * @return True if the vehicle is successfully parked, false otherwise.
   */
  boolean parkVehicle(Vehicle vehicle);

  /**
   * Checks if a vehicle with the specified license plate is currently parked.
   *
   * @param licensePlate The license plate of the vehicle.
   * @return True if a vehicle with the specified license plate is parked, false otherwise.
   */
  boolean isVehicleParked(String licensePlate);

  /**
   * Assigns a parking place for the vehicle upon successful parking.
   * The parking place number is composed of the capital letter of the vehicle type,
   * the current occupied capacity, and an incrementing number.
   *
   * @param vehicleType The vehicle type to assign a parking place to.
   * @return The assigned parking place number.
   */
  String assignParkingPlace(VehicleType vehicleType);


  /**
   * Processes the vehicle to leave the parking lot.
   * When the vehicle process to leave, the leave time will be updated and the system will check if
   * the vehicle has paid all the parking fee. If it does, then gate will open for this vehicle.
   *
   * @param vehicle The vehicle to process.
   * @return True if the vehicle is successfully processed to leave, false otherwise.
   * @throws IllegalStateException If the vehicle is not currently parked.
   */
  boolean processToLeave(Vehicle vehicle) throws IllegalStateException;

  /**
   * Opens the gate to allow the specified vehicle to leave the parking lot.
   * Also remove the vehicle from the parkedVehicles list, occupiedSpaces list and paidVehicles list.
   *
   * @param vehicle The vehicle to allow to leave.
   */
  void openGate(Vehicle vehicle);

  /**
   * Retrieves a map of vehicles that satisfy the given predicate.
   *
   * @param predicate The predicate to filter vehicles.
   * @return A map of vehicles that satisfy the given predicate.
   */
  Map<String, Vehicle> getVehicles(Predicate<Vehicle> predicate);

  /**
   * Counts the number of vehicles that satisfy the given predicate.
   *
   * @param test The predicate to test vehicles.
   * @return The number of vehicles that satisfy the given predicate.
   */
  int count(Predicate<Vehicle> test);
}
