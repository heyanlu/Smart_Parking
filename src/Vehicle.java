import java.time.LocalDateTime;

public interface Vehicle {

  String getLicensePlate();

  void setLeaveTime(LocalDateTime now);

  void setPaymentTime(LocalDateTime leaveTime);

  //LocalDateTime getLeaveTime();


  boolean isMembership();

  float getParkingRate();

  float getParkingFee();

  VehicleType getType();

  //LocalDateTime setArrivalTime();

  //LocalDateTime setArrivalTime(LocalDateTime arrivalTime);

  void setArrivalTime(LocalDateTime arrivalTime);

  LocalDateTime getArrivalTime();

  LocalDateTime getLeaveTime();

  LocalDateTime getPaymentTime();

  float rechargeParkingFee();

  //boolean isPaidRechargeParkingFee(Vehicle vehicle);

  boolean isPaidRechargeParkingFee();

  //boolean vehicleHasLeft(LocalDateTime expectedLeaveTime);
}
