import java.time.LocalDateTime;

public class Motorbike extends AbstractVehicle {

    public Motorbike(String licensePlate, VehicleType type, LocalDateTime arrivalTime, MembershipSystem membershipSystem) {
        super(licensePlate, type, arrivalTime, membershipSystem);
    }

    @Override
    public VehicleType getType() {
        return VehicleType.MOTORBIKE;
    }


}
