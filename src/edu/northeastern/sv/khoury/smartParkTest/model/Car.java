package edu.northeastern.sv.khoury.smartParkTest.model;

import java.time.LocalDateTime;

/**
 * Class representing a car, extending AbstractVehicle.
 */
public class Car extends AbstractVehicle {

    /**
     * Constructs a Car object with the given parameters.
     *
     * @param licensePlate The license plate of the car.
     * @param type         The type of the car.
     * @param arrivalTime  The arrival time of the car.
     * @param paymentTime  The payment time of the car.
     * @param leaveTime    The leave time of the car.
     * @param membershipSystem The membership system used for membership checks.
     */
    public Car(String licensePlate, VehicleType type, LocalDateTime arrivalTime, LocalDateTime paymentTime, LocalDateTime leaveTime, MembershipSystem membershipSystem) {
        super(licensePlate, type, arrivalTime, paymentTime, leaveTime, membershipSystem);
    }

    /**
     * Retrieves the type of the car.
     *
     * @return The type of the car, which is VehicleType.CAR.
     */
    @Override
    public VehicleType getType() {
        return VehicleType.CAR;
    }
}
