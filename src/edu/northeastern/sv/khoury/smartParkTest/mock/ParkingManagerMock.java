package edu.northeastern.sv.khoury.smartParkTest.mock;

import edu.northeastern.sv.khoury.smartParkTest.model.Car;
import edu.northeastern.sv.khoury.smartParkTest.model.IParkingManager;
import edu.northeastern.sv.khoury.smartParkTest.model.MembershipSystem;
import edu.northeastern.sv.khoury.smartParkTest.model.Motorbike;
import edu.northeastern.sv.khoury.smartParkTest.model.Truck;
import edu.northeastern.sv.khoury.smartParkTest.model.Vehicle;
import edu.northeastern.sv.khoury.smartParkTest.model.VehicleType;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

/**
 * This class is a mock class of the parking manager for testing purposes.
 * It implements the IParkingManager interface and provides mock functionality for parking management.
 * The mock manager maintains capacity information, occupancy status, and a list of parked vehicles.
 * It also provides methods to simulate parking, vehicle creation, and parking space assignment.
 */
public class ParkingManagerMock implements IParkingManager {
  private Map<VehicleType, Integer> capacityMap;
  private Map<VehicleType, Integer> occupiedSpaces;
  private Map<String, Vehicle> parkedVehicles;
  private MembershipSystem membershipSystem;

  /**
   * Constructor of new ParkingManagerMock with the specified membership system.
   *
   * @param membershipSystem The membership system to use.
   */
  public ParkingManagerMock(MembershipSystem membershipSystem) {
    this.capacityMap = new HashMap<>();
    this.occupiedSpaces = new HashMap<>();
    this.parkedVehicles = new HashMap<>();

    capacityMap.put(VehicleType.CAR, 20);
    capacityMap.put(VehicleType.TRUCK, 10);
    capacityMap.put(VehicleType.MOTORBIKE, 5);

    occupiedSpaces.put(VehicleType.CAR, 10);
    occupiedSpaces.put(VehicleType.TRUCK, 5);
    occupiedSpaces.put(VehicleType.MOTORBIKE, 5);

    this.membershipSystem = new MembershipSystem();
  }


  @Override
  public MembershipSystem getMembershipSystem() {
    return membershipSystem;
  }


  @Override
  public int getTotalCapacity(VehicleType vehicleType) {
    return capacityMap.getOrDefault(vehicleType, 0);
  }

  @Override
  public int getOccupiedSpaces(VehicleType vehicleType) {
    return occupiedSpaces.getOrDefault(vehicleType, 0);
  }

  @Override
  public int getAvailableSpaces(VehicleType vehicleType) {
    int totalCapacity = getTotalCapacity(vehicleType);
    int occupied = getOccupiedSpaces(vehicleType);
    return totalCapacity - occupied;
  }

  @Override
  public boolean isMemberVehicle(Vehicle vehicle) {
    return membershipSystem.isMembership(vehicle.getLicensePlate());
  }

  @Override
  public Vehicle createVehicle(String licensePlate, VehicleType vehicleType) {
    LocalDateTime arrivalTime = LocalDateTime.now().minus(2, ChronoUnit.HOURS);
    switch (vehicleType) {
      case CAR:
        return new Car(licensePlate, vehicleType, arrivalTime, null, null, null);
      case MOTORBIKE:
        return new Motorbike(licensePlate, vehicleType, arrivalTime, null, null, null);
      case TRUCK:
        return new Truck(licensePlate, vehicleType, arrivalTime, null, null, null);
      default:
        throw new IllegalArgumentException("Invalid vehicle type: " + vehicleType);
    }
  }

  @Override
  public boolean parkVehicle(Vehicle vehicle) {
    VehicleType vehicleType = vehicle.getType();
    float parkingRate = vehicle.getParkingRate();

    if (getTotalCapacity(vehicleType) <= getOccupiedSpaces(vehicleType)) {
      return false;
    } else if (!parkedVehicles.containsKey(vehicle.getLicensePlate())) {
      parkedVehicles.put(vehicle.getLicensePlate(), vehicle);
      int currentOccupiedSpaces = occupiedSpaces.getOrDefault(vehicleType, 0);
      occupiedSpaces.put(vehicleType, currentOccupiedSpaces + 1);
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean isVehicleParked(String licensePlate) {
    return parkedVehicles.containsKey(licensePlate);
  }

  @Override
  public String assignParkingPlace(VehicleType vehicleType) {
    int currentOccupiedSpaces = getOccupiedSpaces(vehicleType);
    return vehicleType.toString().charAt(0) + Integer.toString(currentOccupiedSpaces + 1);
  }


  @Override
  public boolean processToLeave(Vehicle vehicle) throws IllegalStateException {
    throw new IllegalStateException("Not implemented yet");
  }

  @Override
  public void openGate(Vehicle vehicle) {
    // Mock method, not implemented
  }

  @Override
  public Map<String, Vehicle> getVehicles(Predicate<Vehicle> predicate) {
    return null;
  }

  @Override
  public Map<String, Vehicle> getParkedVehicles() {
    return parkedVehicles != null ? parkedVehicles : new HashMap<>();
  }

  @Override
  public int count(Predicate<Vehicle> test) {
    return 0;
  }
}
