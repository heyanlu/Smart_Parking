import java.time.LocalDateTime;

public class Truck extends AbstractVehicle{
    private static final float parkingRate = 4.5F;


    public Truck(String licensePlate, VehicleType type, LocalDateTime arrivalTime, MembershipSystem membershipSystem) {
        super(licensePlate, type, arrivalTime, membershipSystem, parkingRate);
    }

    @Override
    public VehicleType getType() {
        return VehicleType.TRUCK;
    }

    @Override
    public float getParkingRate() {
        return parkingRate;
    }


}
