import java.util.Map;

public interface IPaymentSystem {
  Map<String, Vehicle> getPaidVehicles();

  float getTotalParkingFees();

  boolean processPayment(Vehicle vehicle);

}
