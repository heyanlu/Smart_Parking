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


    public String generateMembershipReport(String licensePlate) {
        boolean isMember = isMembership(licensePlate);
        if (isMember) {
            MembershipType membershipType = getMembershipType(licensePlate);
            Membership membership = getMembership(licensePlate);
            LocalDateTime startTime = membership.getStartTime();
            LocalDateTime endTime = membership.getEndTime();

            StringBuilder report = new StringBuilder();
            report.append("Membership report for ").append(licensePlate).append(":\n");
            report.append("Start Time: ").append(startTime).append("\n");
            report.append("End Time: ").append(endTime).append("\n");
            report.append("Type: ").append(membershipType).append("\n");
            report.append("Membership Status: ").append(isMember);

            return report.toString();
        } else {
            return "Not a member: " + licensePlate;
        }
    }
}
