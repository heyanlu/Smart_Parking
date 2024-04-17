package edu.northeastern.sv.khoury.smartParkTest.model;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Class representing a payment system for managing vehicle payments in a parking lot.
 */
public class PaymentSystem implements IPaymentSystem {
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
     * It will output the paid vehicles hashmap where key is the license plate, vehicle is the value.
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
