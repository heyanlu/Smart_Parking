import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Predicate;

public class ParkingManager<T extends Vehicle> implements IParkingManager {
  protected static final long MAX_PARKING_DURATION_MINUTES = 20;

  private boolean gateOpen;

  private final Map<VehicleType, Integer> capacityMap;

  private Map<VehicleType, Integer> occupiedSpaces;

  PaymentSystem paymentSystem;

  private Map<String, Vehicle> parkedVehicles;

  private MembershipSystem membershipSystem;



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

  public MembershipSystem getMembershipSystem() {
    return membershipSystem;
  }

  @Override
  public int getTotalCapacity(Vehicle vehicle) {
    return capacityMap.getOrDefault(vehicle.getType(), 0);
  }


  @Override
  public int getOccupiedSpaces(Vehicle vehicle) {
    return occupiedSpaces.getOrDefault(vehicle.getType(), 0);
  }

//  @Override
//  public boolean hasAvailableSpace(Vehicle vehicle) {
//    int capacity = getTotalCapacity(vehicle);
//    int occupied = getOccupiedSpaces(vehicle);
//    return occupied < capacity;
//  }


  @Override
  public int getAvailableSpaces(Vehicle vehicle) {
    int totalCapacity = getTotalCapacity(vehicle);
    int occupiedSpaces = this.occupiedSpaces.getOrDefault(vehicle.getType(), 0);
    return totalCapacity - occupiedSpaces;
  }



  @Override
  public boolean isMemberVehicle(Vehicle vehicle) {
    return membershipSystem.isMembership(vehicle.getLicensePlate());
  }


  @Override
  public void parkVehicle(Vehicle vehicle) throws IllegalStateException{
    VehicleType vehicleType = vehicle.getType();

    if (getTotalCapacity(vehicle) <= getOccupiedSpaces(vehicle)) {
      throw new IllegalStateException("Parking lot capacity for " + vehicle.getType() + " is full.");
    } else if (!parkedVehicles.containsKey(vehicle.getLicensePlate())) {
      parkedVehicles.put(vehicle.getLicensePlate(), (Vehicle) vehicle);
      int currentOccupiedSpaces = occupiedSpaces.getOrDefault(vehicleType, 0);
      occupiedSpaces.put(vehicleType, currentOccupiedSpaces + 1);
    }
  }

  @Override
  public boolean isVehicleParked(String licensePlate) {
    return parkedVehicles.containsKey(licensePlate);
  }



  @Override
  public void vehicleExit(Vehicle vehicle) {
    if (parkedVehicles.containsKey(vehicle.getLicensePlate())) {
      vehicle.setLeaveTime(LocalDateTime.now());
    }
  }

  @Override
  public boolean vehicleHasLeft(Vehicle vehicle, LocalDateTime expectedLeaveTime) {
    LocalDateTime actualLeaveTime = vehicle.getLeaveTime();
    return actualLeaveTime != null && actualLeaveTime.isBefore(expectedLeaveTime);
  }



  @Override
  public boolean processToLeave(Vehicle vehicle) throws IllegalStateException {
    if (!parkedVehicles.containsKey(vehicle.getLicensePlate())) {
      throw new IllegalStateException("Vehicle with license plate " + vehicle.getLicensePlate() + " is not currently parked here.");
    } else {
      if (vehicle.getPaymentTime() != null && paymentSystem.processPayment(vehicle)) {
        LocalDateTime expectedLeaveTime = vehicle.getPaymentTime().plusMinutes(ParkingManager.MAX_PARKING_DURATION_MINUTES);
        System.out.println("Expected leave time: " + expectedLeaveTime);

        if (vehicleHasLeft(vehicle, expectedLeaveTime)) {
          openGate(vehicle);
          return true;
        } else {
          if (vehicle.isPaidRechargeParkingFee()) {
            openGate(vehicle);
            float amount = vehicle.rechargeParkingFee();
            System.out.printf("Recharge Parking fee: $%.2f, Recharging fee is paid.%n", amount);

            return true;
          } else {
            float amount = vehicle.rechargeParkingFee();
            if (amount > 0) {
              System.out.println("Recharge Parking fee: $" + amount);
              return false;
            } else {
              System.out.println("Error: Vehicle payment not found.");
              return false;
            }
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
