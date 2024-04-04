import java.time.LocalDateTime;

public class Car extends AbstractVehicle{
    public Car(String licensePlate, VehicleType type, LocalDateTime arrivalTime, MembershipSystem membershipSystem) {
        super(licensePlate, type, arrivalTime, membershipSystem);
    }

    @Override
    public VehicleType getType() {
        return VehicleType.CAR;
    }
}
