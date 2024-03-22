import java.time.LocalDateTime;

public class Motorbike extends AbstractVehicle {
    private static final float parkingRate = 1.5F;
    //private static final int totalCapacity = 100;

    public Motorbike(String licensePlate, VehicleType type, LocalDateTime arrivalTime, MembershipSystem membershipSystem) {
        super(licensePlate, type, arrivalTime, membershipSystem, parkingRate);
    }

    @Override
    public VehicleType getType() {
        return VehicleType.MOTORBIKE;
    }

    @Override
    public float getParkingRate() {
        return parkingRate;
    }

}
