import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class MembershipSystem {
    private Map<String, Membership> memberships;

    public MembershipSystem(Map<String, Membership> memberships) {
        this.memberships = memberships;
    }

    public MembershipSystem() {
        this.memberships = new HashMap<>();
    }

    public boolean addMembership(String licensePlate, MembershipType type, LocalDateTime startTime) {
        Membership membership = new Membership(type, startTime);
        memberships.put(licensePlate, membership);
        return true;
    }

    public boolean removeMembership(String licensePlate) {
        if (memberships.containsKey(licensePlate)) {
            memberships.remove(licensePlate); 
            return true;
        }
        return false; 
    }

    public boolean isMembership(String licensePlate) {
        return memberships.containsKey(licensePlate);
    }


    public Membership getMembership(String licensePlate) {
        if (isMembership(licensePlate)) {
            return memberships.get(licensePlate);
        }
        return null;
    }

    public MembershipType getMembershipType(String licensePlate) {
        Membership membership = memberships.get(licensePlate);
        if (membership != null) {
            return membership.getType();
        }
        return null;
    }
}
