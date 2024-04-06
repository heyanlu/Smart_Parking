import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Predicate;

public class ParkingManager<T extends Vehicle> implements IParkingManager {
  protected static final long MAX_PARKING_DURATION_MINUTES = 20;

  private ParkingRates parkingRates;

  private boolean gateOpen;

  private final Map<VehicleType, Integer> capacityMap;

  private Map<VehicleType, Integer> occupiedSpaces;

  private PaymentSystem paymentSystem;

  private Map<String, Vehicle> parkedVehicles;

  private MembershipSystem membershipSystem;

  //ParkingRates parkingRates = new ParkingRates();



  public ParkingManager(Map<VehicleType, Integer> capacityMap,
      Map<VehicleType, Integer> occupiedSpaces, MembershipSystem membershipSystem,
      PaymentSystem paymentSystem) {
    this.capacityMap = capacityMap;
    this.occupiedSpaces = occupiedSpaces;
    this.parkedVehicles = new HashMap<>();
    this.membershipSystem = membershipSystem;
    this.paymentSystem = paymentSystem;
    this.gateOpen = false;
    //this.parkingRates = parkingRates;
  }

  public MembershipSystem getMembershipSystem() {
    return membershipSystem;
  }

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
        return new Car(licensePlate, vehicleType, arrivalTime, membershipSystem);
      case MOTORBIKE:
        return new Motorbike(licensePlate, vehicleType, arrivalTime, membershipSystem);
      case TRUCK:
        return new Truck(licensePlate, vehicleType, arrivalTime, membershipSystem);
      default:
        throw new IllegalArgumentException("Invalid vehicle type: " + vehicleType);
    }
  }


  @Override
  public boolean parkVehicle(Vehicle vehicle) throws IllegalStateException{
    VehicleType vehicleType = vehicle.getType();
    float parkingRate = vehicle.getParkingRate();
    String message;

    if (getTotalCapacity(vehicleType) <= getOccupiedSpaces(vehicleType)) {
      message = "Parking lot capacity for " + vehicleType + " is full.";
      throw new IllegalStateException(message);
    } else if (!parkedVehicles.containsKey(vehicle.getLicensePlate())) {
      parkedVehicles.put(vehicle.getLicensePlate(), vehicle);
      int currentOccupiedSpaces = occupiedSpaces.getOrDefault(vehicleType, 0);
      occupiedSpaces.put(vehicleType, currentOccupiedSpaces + 1);
      message = "Welcome to Park" + vehicle.getLicensePlate() + "!";
      return true;
    } else {
      message = "Vehicle with license plate " + vehicle.getLicensePlate() + " is already parked.";
      return false;
    }
  }


  @Override
  public boolean isVehicleParked(String licensePlate) {
    return parkedVehicles.containsKey(licensePlate);
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
