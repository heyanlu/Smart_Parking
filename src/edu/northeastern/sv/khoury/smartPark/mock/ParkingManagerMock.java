package edu.northeastern.sv.khoury.smartPark.mock;

import edu.northeastern.sv.khoury.smartPark.model.IParkingManager;
import edu.northeastern.sv.khoury.smartPark.model.MembershipSystem;
import edu.northeastern.sv.khoury.smartPark.model.Vehicle;
import edu.northeastern.sv.khoury.smartPark.model.VehicleType;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

/**
 * This class is a mock class of the parking manager for testing purposes.
 * It implements the IParkingManager interface and provides mock functionality for parking management.
 * The mock manager is not suppose to go into the detail of the every class, it only checks the input and output
 * logic of this class
 */
public class ParkingManagerMock implements IParkingManager {
  private MembershipSystem membershipSystem;
  private StringBuilder log;
  private final int uniqueCode;

  /**
   * Constructor of new ParkingManagerMock with the specified membership system.
   *
   * @param membershipSystem The membership system to use.
   */
  public ParkingManagerMock(MembershipSystem membershipSystem, StringBuilder log, int uniqueCode) {
    this.membershipSystem = membershipSystem;
    this.uniqueCode = uniqueCode;
    this.log = log;
  }


  @Override
  public MembershipSystem getMembershipSystem() {
    log.append("Mock getMembershipSystem called.");
    return membershipSystem;
  }

  @Override
  public int getTotalCapacity(VehicleType vehicleType) {
    log.append("Check total capacity for vehicle. Unique Code: "  + uniqueCode +'.');
    return uniqueCode;
  }

  @Override
  public int getOccupiedSpaces(VehicleType vehicleType) {
    log.append("Check occupied capacity for vehicle. Unique Code: "  + uniqueCode +'.');
    return uniqueCode;
  }

  @Override
  public int getAvailableSpaces(VehicleType vehicleType) {
    log.append("Check available capacity for vehicle. Unique Code: " + uniqueCode +'.');
    return uniqueCode;
  }

  @Override
  public boolean isMemberVehicle(Vehicle vehicle) {
    log.append("Check vehicle membership status.");
    return false;
  }

  @Override
  public Vehicle createVehicle(String licensePlate, VehicleType vehicleType) {
    // Mock method, not implemented
    return null;
  }

  @Override
  public boolean parkVehicle(Vehicle vehicle) {
    log.append("Parking Successful! Unique Code: " + uniqueCode + "\n");
    return true;
  }

  @Override
  public boolean isVehicleParked(String licensePlate) {
    // Mock method, not implemented
    return true;
  }

  @Override
  public String assignParkingPlace(VehicleType vehicleType) {
    // Mock method, not implemented
    return "";
  }


  @Override
  public boolean processToLeave(Vehicle vehicle) throws IllegalStateException {
    log.append("Mock process to leave method.");
    return true;
  }

  @Override
  public void openGate(Vehicle vehicle) {
    // Mock method, not implemented
  }

  @Override
  public Map<String, Vehicle> getVehicles(Predicate<Vehicle> predicate) {
    // Mock method, not implemented
    return new HashMap<>();
  }

  @Override
  public Map<String, Vehicle> getParkedVehicles() {
    log.append("Mock getParkedVehicles called.");
    return new HashMap<>();
  }

  @Override
  public int count(Predicate<Vehicle> test) {
    // Mock method, not implemented
    return 0;
  }
}
