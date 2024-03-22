import java.time.LocalDateTime;
import java.util.Map;
import java.util.function.Predicate;

public interface IParkingManager {

  int getTotalCapacity(Vehicle vehicle);

  int getOccupiedSpaces(Vehicle vehicle);

  boolean hasAvailableSpace(Vehicle vehicle);

  void parkVehicle(Vehicle vehicle);

  int getAvailableSpaces(Vehicle vehicle);

  boolean isMemberVehicle(Vehicle vehicle);

  boolean isVehicleParked(String licensePlate);


  void vehicleExit(Vehicle vehicle);

  boolean vehicleHasLeft(Vehicle vehicle, LocalDateTime expectedLeaveTime);

  boolean processToLeave(Vehicle vehicle);

  void openGate(Vehicle vehicle);

  //String toString(Predicate<Vehicle> test);

  Map<String, Vehicle> getVehicles(Predicate<Vehicle> predicate);

  int count(Predicate<Vehicle> test);

}
