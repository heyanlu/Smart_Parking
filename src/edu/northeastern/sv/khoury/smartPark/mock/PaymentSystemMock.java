package edu.northeastern.sv.khoury.smartPark.mock;

import edu.northeastern.sv.khoury.smartPark.model.IPaymentSystem;
import edu.northeastern.sv.khoury.smartPark.model.Vehicle;
import java.util.Map;

/**
 * This class represents a mock implementation of the payment system for testing purposes.
 * The mock payment system is not suppose to go into the detail of the every class, it only checks the input and output
 * logic of this class
 */
public class PaymentSystemMock implements IPaymentSystem {
  private StringBuilder log;

  private final int uniqueCode;


  /**
   * Constructs a new PaymentSystemMock with initial empty collections.
   */
  public PaymentSystemMock(StringBuilder log, int uniqueCode) {
    this.uniqueCode = uniqueCode;
    this.log = log;
  }

  @Override
  public Map<String, Vehicle> getPaidVehicles() {
    // Mock method, not implemented
    return null;
  }

  @Override
  public float getTotalParkingFees() {
    // Mock method, not implemented
    return 0.0F;
  }

  @Override
  public boolean processPayment(Vehicle vehicle) {
    log.append("Vehicle with license plate null not found in the parking lot.");
    return true;
  }
}
