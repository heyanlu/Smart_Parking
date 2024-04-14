package edu.northeastern.sv.khoury.smartParkTest.model;

import java.util.Map;

public interface IPaymentSystem {
  Map<String, Vehicle> getPaidVehicles();

  float getTotalParkingFees();

  boolean processPayment(Vehicle vehicle);

}
