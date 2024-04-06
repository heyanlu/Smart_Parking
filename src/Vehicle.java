import java.time.Duration;
import java.time.LocalDateTime;

public interface Vehicle {

  String getLicensePlate();

  void setLeaveTime(LocalDateTime now);

  void setPaymentTime(LocalDateTime leaveTime);

  LocalDateTime getLeaveTime();


  boolean isMembership();

  float getParkingRate();

  float getParkingFee();

  VehicleType getType();

  void setArrivalTime(LocalDateTime arrivalTime);

  LocalDateTime getArrivalTime();

  LocalDateTime getPaymentTime();

  float rechargeParkingFee();


  boolean isPaidRechargeParkingFee();

}
