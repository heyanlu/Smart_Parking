import java.time.LocalDateTime;
import java.util.*;
import javax.swing.JOptionPane;

public class PaymentSystem {
    //private boolean gateOpen;
    private Map<String, Vehicle> paidVehicles;

    private MembershipSystem membershipSystem;

    private Map<String, Float> parkingFees;
    private float totalParkingFees;

    public PaymentSystem() {
        //this.gateOpen = false;
        this.paidVehicles = new HashMap<>();
        this.parkingFees = new HashMap<>();
        this.totalParkingFees = 0;

    }

    public Map<String, Vehicle> getPaidVehicles() {
        return paidVehicles;
    }

    public float getTotalParkingFees() {
        return totalParkingFees;
    }

    public boolean processPayment(Vehicle vehicle) {
        float amount = vehicle.getParkingFee();

        if (amount == 0) {
            paidVehicles.put(vehicle.getLicensePlate(), vehicle);
            parkingFees.put(vehicle.getLicensePlate(), amount);
            vehicle.setPaymentTime(LocalDateTime.now());
        } else {
            boolean paymentSuccess = true; // Simulated payment success
            if (paymentSuccess) {
                paidVehicles.put(vehicle.getLicensePlate(), vehicle);
                parkingFees.put(vehicle.getLicensePlate(), amount);
                totalParkingFees += amount; // Update total parking fees
                vehicle.setPaymentTime(LocalDateTime.now());
            } else {
                return false;
            }
        }

        return true;
    }

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
