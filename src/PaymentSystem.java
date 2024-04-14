import java.time.LocalDateTime;
import java.util.*;

/**
 * Class representing a payment system for managing vehicle payments in a parking lot.
 */
public class PaymentSystem implements IPaymentSystem{
    private Map<String, Vehicle> paidVehicles;

    private Map<String, Float> parkingFees;

    private float totalParkingFees;

    /**
     * Constructor of PaymentSystem.
     */
    public PaymentSystem() {
        this.paidVehicles = new HashMap<>();
        this.parkingFees = new HashMap<>();
        this.totalParkingFees = 0;
    }

    /**
     * Retrieves a map of the paid vehicle.
     *
     * @return A map containing the paid vehicles.
     */
    @Override
    public Map<String, Vehicle> getPaidVehicles() {
        return paidVehicles;
    }

    /**
     * Retrieves the total parking fee collected by the parking system
     *
     * @return total parking fee collected.
     */
    @Override
    public float getTotalParkingFees() {
        return totalParkingFees;
    }

    /**
     * Processes to pay for the given vehicle.
     *
     * My payment logic would be: if vehicle is process Payment, it will set up a temporary
     * paymentTime. If the payment is successful, it will return true, otherwise, it will
     * return false, and reset the paymentTime to null.
     *
     * @param vehicle The vehicle for which payment is to be processed.
     * @return True if the payment is successfully processed, false otherwise.
     */
    @Override
    public boolean processPayment(Vehicle vehicle) {
        vehicle.setPaymentTime(LocalDateTime.now());
        float amount = vehicle.getParkingFee();

        if (amount == 0) {
            paidVehicles.put(vehicle.getLicensePlate(), vehicle);
            parkingFees.put(vehicle.getLicensePlate(), amount);
            return true;
        } else {
            //Have not created the payment acceptance system, so set paymentSuccess to true
            boolean paymentSuccess = true;
            if (paymentSuccess) {
                paidVehicles.put(vehicle.getLicensePlate(), vehicle);
                parkingFees.put(vehicle.getLicensePlate(), amount);
                totalParkingFees += amount;
                return true;
            } else {
                vehicle.setPaymentTime(null);
                return false;
            }
        }
    }

    /**
     * Returns a string representation of the paid vehicles.
     *
     * @return A string representation of the paid vehicles.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Paid Vehicles: [");

        for (Map.Entry<String, Vehicle> entry: paidVehicles.entrySet()) {
            sb.append(entry.getKey());
            sb.append(", ");
        }

        // Remove the trailing comma and space
        if (!paidVehicles.isEmpty()) {
            sb.delete(sb.length() - 2, sb.length());
        }

        sb.append("]");
        return sb.toString();
    }

}
