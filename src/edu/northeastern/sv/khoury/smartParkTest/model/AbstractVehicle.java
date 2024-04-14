package edu.northeastern.sv.khoury.smartParkTest.model;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Abstract class representing a vehicle that implements the edu.northeastern.sv.khoury.smartPark.model.Vehicle interface.
 */
public class AbstractVehicle implements Vehicle {
  private String licensePlate;

  private VehicleType type;

  private LocalDateTime arrivalTime;

  private LocalDateTime paymentTime;

  private LocalDateTime leaveTime;

  private MembershipSystem membershipSystem;

  /**
   * Constructs an edu.northeastern.sv.khoury.smartPark.model.AbstractVehicle object with the given parameters.
   *
   * @param licensePlate The license plate of the vehicle.
   * @param type The type of the vehicle.
   * @param arrivalTime The arrival time of the vehicle.
   * @param paymentTime  The payment time of the car.
   * @param leaveTime    The leave time of the car.
   * @param membershipSystem The membership system used for membership checks.
   */
  public AbstractVehicle(String licensePlate, VehicleType type, LocalDateTime arrivalTime,
      LocalDateTime paymentTime, LocalDateTime leaveTime, MembershipSystem membershipSystem) {
    this.licensePlate = licensePlate;
    this.type = type;
    this.arrivalTime = arrivalTime;
    this.paymentTime = paymentTime;
    this.leaveTime = leaveTime;
    this.membershipSystem = membershipSystem;
  }


  @Override
  public VehicleType getType() {
    return type;
  }

  @Override
  public String getLicensePlate() {
    return licensePlate;
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
  public boolean isMembership() {
    return membershipSystem.isMembership(licensePlate);
  }

  @Override
  public float getParkingRate() {
    return ParkingRates.getRateForVehicleType(type);
  }


  @Override
  public float getParkingFee() {
    if (paymentTime == null) {
      return 0.0F;
    }

    if (isMembership()) {
      Membership membership = membershipSystem.getMembership(licensePlate);
      if (membership != null && paymentTime.isBefore(membership.getEndTime())) {
        return 0.0F;
      }
    }

    Duration duration = Duration.between(arrivalTime, paymentTime);
    long minutes = duration.toMinutes();

    if (minutes <= 30) {
      return 0.0F;
    } else {
      minutes -= 30;
      return ParkingFeeCalculator.calculateParkingFee((float) minutes, getParkingRate(), type);
    }
  }


  @Override
  public float rechargeParkingFee() throws IllegalStateException{
    if (paymentTime == null) {
      throw new IllegalStateException("You should pay the parking fee first!");
    }
    
    LocalDateTime expectedLeaveTime = paymentTime.plusMinutes(
        ParkingManager.MAX_PARKING_DURATION_MINUTES);

    Duration duration = Duration.between(expectedLeaveTime, leaveTime);

    long minutes = duration.toMinutes();

    if (isMembership()) {
      Membership membership = membershipSystem.getMembership(licensePlate);
      if (membership != null && paymentTime.isBefore(membership.getEndTime())) {
        return 0.0F;
      }
    }
    return ParkingFeeCalculator.calculateParkingFee((float) minutes, getParkingRate(), type);
  }


  @Override
  public boolean isPaidRechargeParkingFee() {
    //Have not created the payment acceptance system, so set paymentSuccess to true
    boolean paymentSuccess = true;
    return paymentSuccess;
  }

  /**
   * String representation of vehicle, including its license plate, type, arrival time,
   * payment time, leave time, and membership status.
   *
   * @return string representation of the vehicle
   */
  @Override
  public String toString() {
    String membershipStatus = membershipSystem != null ? String.valueOf(membershipSystem.isMembership(getLicensePlate())) : "unknown";
    return "edu.northeastern.sv.khoury.smartPark.model.Vehicle{" +
        "\n\tlicensePlate='" + licensePlate + '\'' +
        ",\n\ttype=" + type +
        ",\n\tarrivalTime=" + arrivalTime +
        ",\n\tpaymentTime=" + paymentTime +
        ",\n\tleaveTime=" + leaveTime +
        ",\n\tmembership=" + membershipStatus +
        '}';
  }
}
