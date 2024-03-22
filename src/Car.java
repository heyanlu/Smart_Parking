import java.time.LocalDateTime;

public class Car extends AbstractVehicle{

    private static final float parkingRate = 2.0F;
    //private static final int totalCapacity = 200;



    public Car(String licensePlate, VehicleType type, LocalDateTime arrivalTime, MembershipSystem membershipSystem) {
        super(licensePlate, type, arrivalTime, membershipSystem, parkingRate); // Pass the default parking rate
    }

    @Override
    public VehicleType getType() {
        return VehicleType.CAR;
    }

    @Override
    public float getParkingRate() {
        return parkingRate;
    }


}
