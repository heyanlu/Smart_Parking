import java.time.LocalDateTime;

/**
 * Class of the membership information, including type, startTime and endTime.
 */
public class Membership {
    private final MembershipType type;
    private final LocalDateTime startTime;

    /**
     * Constructor for a membership.
     * @param type Membership type.
     * @param startTime The start time of the membership.
     */
    public Membership(MembershipType type, LocalDateTime startTime) {
        this.type = type;
        this.startTime = startTime;
    }

    /**
     * Calculates the end time of a membership based on the start time and membership type.
     * @param startTime The start time of membership
     * @param type The type of membership
     * @return The end time of membership
     */
    public LocalDateTime calculateEndTime(LocalDateTime startTime, MembershipType type) {
        switch (type) {
            case YEARLY:
                return startTime.plusYears(1);
            case MONTHLY:
                return startTime.plusMonths(1);
            case WEEKLY:
                return startTime.plusWeeks(1);
            default:
                throw new IllegalArgumentException("Unknown membership type: " + type);
        }
    }

    /**
     * Retrieves the type of membership
     * @return The type of membership
     */
    public MembershipType getType() {
        return type;
    }

    /**
     * Retrieves the start time of membership
     * @return The start time of membership
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Retrieves the end time of membership
     * @return The end time of membership
     */
    public LocalDateTime getEndTime() {
        return calculateEndTime(startTime, type);
    }

}
