import java.time.LocalDateTime;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Interface of parking system containing operations for managing parking lot
 */
public interface IParkingManager {


  int getTotalCapacity(Vehicle vehicle);

  int getOccupiedSpaces(Vehicle vehicle);

  //boolean hasAvailableSpace(Vehicle vehicle);

  int getAvailableSpaces(Vehicle vehicle);

  boolean isMemberVehicle(Vehicle vehicle);

  void parkVehicle(Vehicle vehicle) throws IllegalStateException;


  boolean isVehicleParked(String licensePlate);


  void vehicleExit(Vehicle vehicle);

  boolean vehicleHasLeft(Vehicle vehicle, LocalDateTime expectedLeaveTime);

  boolean processToLeave(Vehicle vehicle) throws IllegalStateException;

  void openGate(Vehicle vehicle);

  Map<String, Vehicle> getVehicles(Predicate<Vehicle> predicate);

  int count(Predicate<Vehicle> test);
}
