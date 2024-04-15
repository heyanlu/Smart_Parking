package edu.northeastern.sv.khoury.smartParkTest.mock;

import edu.northeastern.sv.khoury.smartParkTest.model.IPaymentSystem;
import edu.northeastern.sv.khoury.smartParkTest.model.Vehicle;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a mock implementation of the payment system for testing purposes.
 * The mock payment system keeps track of paid vehicles, parking fees, and the total parking fees collected.
 * It simulates processing payments for vehicles and updating payment-related information.
 */
public class PaymentSystemMock implements IPaymentSystem {
  private Map<String, Vehicle> paidVehicles;
  private Map<String, Float> parkingFees;
  private float totalParkingFees;

  /**
   * Constructs a new PaymentSystemMock with initial empty collections.
   */
  public PaymentSystemMock() {
    this.paidVehicles = new HashMap<>();
    this.parkingFees = new HashMap<>();
    this.totalParkingFees = 0;
  }

  @Override
  public Map<String, Vehicle> getPaidVehicles() {
    return paidVehicles;
  }

  @Override
  public float getTotalParkingFees() {
    return totalParkingFees;
  }

  @Override
  public boolean processPayment(Vehicle vehicle) {
    float amount = vehicle.getParkingFee();

    if (amount == 0) {
      paidVehicles.put(vehicle.getLicensePlate(), vehicle);
      parkingFees.put(vehicle.getLicensePlate(), amount);
      vehicle.setPaymentTime(LocalDateTime.now());
    } else {
      boolean paymentSuccess = true;
      if (paymentSuccess) {
        paidVehicles.put(vehicle.getLicensePlate(), vehicle);
        parkingFees.put(vehicle.getLicensePlate(), amount);
        totalParkingFees += amount;
        vehicle.setPaymentTime(LocalDateTime.now());
      } else {
        return false;
      }
    }

    return true;
  }
}
