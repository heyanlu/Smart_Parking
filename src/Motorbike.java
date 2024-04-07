import java.time.LocalDateTime;

/**
 * Class representing a motorbike, extending AbstractVehicle.
 */
public class Motorbike extends AbstractVehicle {
    /**
     * Constructs a Motorbike object with the given parameters.
     *
     * @param licensePlate The license plate of the motorbike.
     * @param type         The type of the motorbike.
     * @param arrivalTime  The arrival time of the motorbike.
     * @param membershipSystem The membership system used for membership checks.
     */
    public Motorbike(String licensePlate, VehicleType type, LocalDateTime arrivalTime, MembershipSystem membershipSystem) {
        super(licensePlate, type, arrivalTime, membershipSystem);
    }

    /**
     * Retrieves the type of the motorbike.
     *
     * @return The type of the motorbike, which is VehicleType.MOTORBIKE.
     */
    @Override
    public VehicleType getType() {
        return VehicleType.MOTORBIKE;
    }


}
