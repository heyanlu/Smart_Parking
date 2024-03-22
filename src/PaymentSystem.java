import java.time.LocalDateTime;
import java.util.*;

public class PaymentSystem {
    //private boolean gateOpen;
    private ArrayList<String> paidVehicles;

    private MembershipSystem membershipSystem;

    private Map<String, Float> parkingFees;
    private float totalParkingFees;

    public PaymentSystem() {
        //this.gateOpen = false;
        this.paidVehicles = new ArrayList<>();
        this.parkingFees = new HashMap<>();
        this.totalParkingFees = 0;

    }

    public ArrayList<String> getPaidVehicles() {
        return paidVehicles;
    }

    public float getTotalParkingFees() {
        return totalParkingFees;
    }


    public boolean processPayment(Vehicle vehicle) {
        float amount = vehicle.getParkingFee();

        System.out.println("Total Parking Fee: $" + amount);

        if (amount == 0) {
            System.out.println("No payment required. Gate is open.\n");
            paidVehicles.add(vehicle.getLicensePlate());
            parkingFees.put(vehicle.getLicensePlate(), amount);
            vehicle.setPaymentTime(LocalDateTime.now());
        }else {
            System.out.println("Parking Fee: $" + amount);

            boolean paymentSuccess = true; // Simulated payment success
            if (paymentSuccess) {
                System.out.println("Payment processed successfully! Please leave within 20 minutes.\n");
                paidVehicles.addFirst(vehicle.getLicensePlate());
                parkingFees.put(vehicle.getLicensePlate(), amount);
                totalParkingFees += amount; // Update total parking fees
                vehicle.setPaymentTime(LocalDateTime.now());
            } else {
                System.out.println("Payment Failed! Please Try Again.\n");
                return false;
            }
        }
        return true;
    }

//    public boolean generateRandomPaymentResult() {
//        Random random = new Random();
//        return random.nextBoolean();
//    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Paid Vehicles: [");

        for (int i = 0; i < paidVehicles.size(); i++) {
            sb.append(paidVehicles.get(i));
            if (i < paidVehicles.size() - 1) {
                sb.append(", ");
            }
        }

        sb.append("]");
        return sb.toString();
    }

}
