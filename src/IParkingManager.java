import java.time.LocalDateTime;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Interface of parking system containing operations for managing parking lot
 */
public interface IParkingManager {


  int getTotalCapacity(VehicleType vehicleType);

  int getOccupiedSpaces(VehicleType vehicleType);

  int getAvailableSpaces(VehicleType vehicleType);

  boolean isMemberVehicle(Vehicle vehicle);


  Vehicle createVehicle(String licensePlate, VehicleType type);

  boolean parkVehicle(Vehicle vehicle) throws IllegalStateException;

  boolean isVehicleParked(String licensePlate);


  void vehicleExit(Vehicle vehicle);

  boolean processToLeave(Vehicle vehicle) throws IllegalStateException;

  void openGate(Vehicle vehicle);


  Map<String, Vehicle> getVehicles(Predicate<Vehicle> predicate);

  int count(Predicate<Vehicle> test);
}
