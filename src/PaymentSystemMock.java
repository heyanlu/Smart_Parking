import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class PaymentSystemMock implements IPaymentSystem {
  private Map<String, Vehicle> paidVehicles;
  private Map<String, Float> parkingFees;
  private float totalParkingFees;

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
