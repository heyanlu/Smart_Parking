import java.time.LocalDate;
import java.time.LocalDateTime;

public class Membership {
    private final MembershipType type;
    private final LocalDateTime startTime;

    public Membership(MembershipType type, LocalDateTime startTime) {
        this.type = type;
        this.startTime = startTime;
    }


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


    public MembershipType getType() {
        return type;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return calculateEndTime(startTime, type);
    }

}
