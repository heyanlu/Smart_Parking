import java.time.LocalDateTime;

public class Truck extends AbstractVehicle{

    public Truck(String licensePlate, VehicleType type, LocalDateTime arrivalTime, MembershipSystem membershipSystem) {
        super(licensePlate, type, arrivalTime, membershipSystem);
    }

    @Override
    public VehicleType getType() {
        return VehicleType.TRUCK;
    }


}
