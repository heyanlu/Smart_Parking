import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Predicate;

/**
 * Class representing a parking manager that implements the IParkingManager interface.
 *
 * @param <T> Type parameter representing a subtype of Vehicle.
 */
public class ParkingManager<T extends Vehicle> implements IParkingManager {
  protected static final long MAX_PARKING_DURATION_MINUTES = 20;

  private final Map<VehicleType, Integer> capacityMap;

  private Map<VehicleType, Integer> occupiedSpaces;

  private Map<String, Vehicle> parkedVehicles;

  private MembershipSystem membershipSystem;

  private PaymentSystem paymentSystem;

  private boolean gateOpen;

  /**
   * Constructs a ParkingManager object with the given parameters.
   *
   * @param capacityMap A map representing the capacity of parking spaces for each vehicle type.
   * @param occupiedSpaces A map representing the currently occupied parking spaces for each vehicle type.
   * @param membershipSystem The membership system used for membership checks.
   * @param paymentSystem The payment system used for payment processing.
   */
  public ParkingManager(Map<VehicleType, Integer> capacityMap,
      Map<VehicleType, Integer> occupiedSpaces, MembershipSystem membershipSystem,
      PaymentSystem paymentSystem) {
    this.capacityMap = capacityMap;
    this.occupiedSpaces = occupiedSpaces;
    this.parkedVehicles = new HashMap<>();
    this.membershipSystem = membershipSystem;
    this.paymentSystem = paymentSystem;
    this.gateOpen = false;
  }

  @Override
  public MembershipSystem getMembershipSystem() {
    return membershipSystem;
  }

  @Override
  public Map<String, Vehicle> getParkedVehicles() {
    return parkedVehicles;
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
    int occupiedSpaces = this.occupiedSpaces.getOrDefault(vehicleType, 0);
    return totalCapacity - occupiedSpaces;
  }


  @Override
  public boolean isMemberVehicle(Vehicle vehicle) {
    return membershipSystem.isMembership(vehicle.getLicensePlate());
  }

  @Override
  public Vehicle createVehicle(String licensePlate, VehicleType vehicleType) {
    LocalDateTime arrivalTime = LocalDateTime.now();
    switch (vehicleType) {
      case CAR:
        return new Car(licensePlate, vehicleType, arrivalTime, null, null, membershipSystem);
      case MOTORBIKE:
        return new Motorbike(licensePlate, vehicleType, arrivalTime, null, null, membershipSystem);
      case TRUCK:
        return new Truck(licensePlate, vehicleType, arrivalTime, null, null, membershipSystem);
      default:
        throw new IllegalArgumentException("Invalid vehicle type: " + vehicleType);
    }
  }


  @Override
  public boolean parkVehicle(Vehicle vehicle) {
    VehicleType vehicleType = vehicle.getType();
    //float parkingRate = vehicle.getParkingRate();

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
    if (!parkedVehicles.containsKey(vehicle.getLicensePlate())) {
      throw new IllegalStateException("Vehicle with license plate " + vehicle.getLicensePlate() + " is not currently parked here.");
    } else {
      vehicle.setLeaveTime(LocalDateTime.now());

      if (vehicle.getPaymentTime() != null && paymentSystem.processPayment(vehicle)) {
        LocalDateTime expectedLeaveTime = vehicle.getPaymentTime().plusMinutes(ParkingManager.MAX_PARKING_DURATION_MINUTES);

        if (vehicle.getLeaveTime().isBefore(expectedLeaveTime)) {
          openGate(vehicle);
          return true;
        } else {
          if (vehicle.isPaidRechargeParkingFee()) {
            openGate(vehicle);
            return true;
          } else {
            return false;
          }
        }
      }
    }
    return false;
  }


  @Override
  public void openGate(Vehicle vehicle) {
      gateOpen = true;
      parkedVehicles.remove(vehicle.getLicensePlate());
      int currentOccupiedSpaces = occupiedSpaces.getOrDefault(vehicle.getType(), 0);
      occupiedSpaces.put(vehicle.getType(), currentOccupiedSpaces - 1);
      paymentSystem.getPaidVehicles().remove(vehicle.getLicensePlate());
  }


  @Override
 public Map<String, Vehicle> getVehicles(Predicate<Vehicle> predicate) {
   Map<String, Vehicle> newList = new HashMap<>();
   for (Map.Entry<String, Vehicle> entry : parkedVehicles.entrySet()) {
     if (predicate.test(entry.getValue())) {
       newList.put(entry.getKey(), entry.getValue());
     }
   }
   return newList;
 }


  @Override
  public int count(Predicate<Vehicle> test) {
    int count = 0;
    for (Map.Entry<String, Vehicle> entry : parkedVehicles.entrySet()) {
      if (test.test(entry.getValue())) {
        count++;
      }
    }
    return count;
  }

  /**
   * String representation of the parking lot information, including the capacity information, if there is any parked vehicles.
   *
   * @return String representation of the parking lot.
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Parking Manager Status: \n");
    sb.append("Total Capacity: ").append(capacityMap).append("\n");
    sb.append("Occupied Spaces: ").append(occupiedSpaces).append("\n");
    sb.append("Gate Status: ").append(gateOpen ? "Open" : "Closed").append("\n");

    sb.append("Parked Vehicles: \n");
    if (parkedVehicles.isEmpty()) {
      sb.append("No vehicles parked.\n");
    } else {
      for (Map.Entry<String, Vehicle> entry : parkedVehicles.entrySet()) {
        sb.append(entry.getValue()).append("\n");
      }
    }

    return sb.toString();
  }

}
