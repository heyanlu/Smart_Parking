package edu.northeastern.sv.khoury.smartParkTest.model;

import java.time.LocalDateTime;

/**
 * Class representing a motorbike, extending edu.northeastern.sv.khoury.smartPark.model.AbstractVehicle.
 */
public class Motorbike extends AbstractVehicle {
    /**
     * Constructs a edu.northeastern.sv.khoury.smartPark.model.Motorbike object with the given parameters.
     *
     * @param licensePlate The license plate of the motorbike.
     * @param type         The type of the motorbike.
     * @param arrivalTime  The arrival time of the motorbike.
     * @param paymentTime  The payment time of the car.
     * @param leaveTime    The leave time of the car.
     * @param membershipSystem The membership system used for membership checks.
     */
    public Motorbike(String licensePlate, VehicleType type, LocalDateTime arrivalTime, LocalDateTime paymentTime, LocalDateTime leaveTime, MembershipSystem membershipSystem) {
        super(licensePlate, type, arrivalTime, paymentTime, leaveTime, membershipSystem);
    }

    /**
     * Retrieves the type of the motorbike.
     *
     * @return The type of the motorbike, which is edu.northeastern.sv.khoury.smartPark.model.VehicleType.MOTORBIKE.
     */
    @Override
    public VehicleType getType() {
        return VehicleType.MOTORBIKE;
    }


}
