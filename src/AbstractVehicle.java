import java.time.Duration;
import java.time.LocalDateTime;

public class AbstractVehicle implements Vehicle {

  private String licensePlate;

  private VehicleType type;

  private LocalDateTime arrivalTime;

  private LocalDateTime leaveTime;
  private LocalDateTime paymentTime;

  private float parkingRate;
  private MembershipSystem membershipSystem;

  public AbstractVehicle(String licensePlate, VehicleType type, LocalDateTime arrivalTime,
      MembershipSystem membershipSystem, float parkingRate) {
    this.licensePlate = licensePlate;
    this.type = type;
    this.arrivalTime = arrivalTime;
    this.paymentTime = null;
    this.leaveTime = null;
    this.membershipSystem = membershipSystem;
    this.parkingRate = parkingRate;
  }


  public VehicleType getType() {
    return type;
  }


  @Override
  public void setArrivalTime(LocalDateTime arrivalTime) {
    this.arrivalTime = arrivalTime;
  }


  @Override
  public void setLeaveTime(LocalDateTime leaveTime) {
    this.leaveTime = leaveTime;
  }

  @Override
  public void setPaymentTime(LocalDateTime paymentTime) {
    this.paymentTime = paymentTime;
  }

  @Override
  public LocalDateTime getArrivalTime() {
    return arrivalTime;
  }

  @Override
  public LocalDateTime getLeaveTime() {
    return leaveTime;
  }

  @Override
  public LocalDateTime getPaymentTime() {
    return paymentTime;
  }



  @Override
  public String getLicensePlate() {
    return licensePlate;
  }

  @Override
  public boolean isMembership() {
    return membershipSystem.isMembership(licensePlate);
  }

  @Override
  public float getParkingRate() {
    return parkingRate;
  }


  @Override
  public float getParkingFee() {
    if (paymentTime == null) {
      return 0.0F;
    }

    //for checking the output
    System.out.println("Payment Time: " + paymentTime);

    if (isMembership()) {
      Membership membership = membershipSystem.getMembership(licensePlate);
      if (membership != null && paymentTime.isBefore(membership.getEndTime())) {
        return 0.0F;
      }
    }

    Duration duration = Duration.between(arrivalTime, paymentTime);
    long minutes = duration.toMinutes();

    //for checking the output
    System.out.println("Duration in minutes: " + minutes);


    if (minutes <= 30) {
      return 0.0F;
    } else {
      minutes -= 30;
      return ParkingFeeCalculator.calculateParkingFee((float) minutes, parkingRate, type);
    }
  }


  @Override
  public float rechargeParkingFee() {
    LocalDateTime expectedLeaveTime = paymentTime.plusMinutes(
        ParkingManager.MAX_PARKING_DURATION_MINUTES);

    //check for output
    System.out.println("payment time " + paymentTime);
    System.out.println("leave time " + leaveTime);

    Duration duration = Duration.between(expectedLeaveTime, leaveTime);

    long minutes = duration.toMinutes();

    if (isMembership()) {
      Membership membership = membershipSystem.getMembership(licensePlate);
      if (membership != null && paymentTime.isBefore(membership.getEndTime())) {
        return 0.0F;
      }
    }

    return ParkingFeeCalculator.calculateParkingFee((float) minutes, parkingRate, type);

  }


  @Override
  public boolean isPaidRechargeParkingFee() {
    //by default set it to true;
    boolean paymentSuccess = true;
    return true;
  }


  @Override
  public String toString() {
    return "Vehicle{" +
        "licensePlate='" + licensePlate + '\'' +
        ", type=" + type +
        ", arrivalTime=" + arrivalTime +
        ", paymentTime=" + paymentTime +
        ", leaveTime=" + leaveTime +
        ", membership=" + membershipSystem.isMembership(getLicensePlate()) +
        '}';
  }
}
