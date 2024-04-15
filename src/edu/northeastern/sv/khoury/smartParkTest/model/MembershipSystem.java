package edu.northeastern.sv.khoury.smartParkTest.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Class of membership for different vehicles type.
 */
public class MembershipSystem {
    private Map<String, Membership> memberships;

    /**
     * Constructor of edu.northeastern.sv.khoury.smartPark.model.MembershipSystem.
     */
    public MembershipSystem() {
        this.memberships = new HashMap<>();
    }

    /**
     * Adds a new membership for a vehicle.
     *
     * @param licensePlate The license plate of the vehicle.
     * @param type         The type of membership.
     * @param startTime    The start time of the membership.
     * @return True if the membership is successfully added, false otherwise.
     */
    public boolean addMembership(String licensePlate, MembershipType type, LocalDateTime startTime) {
        Membership membership = new Membership(type, startTime);
        memberships.put(licensePlate, membership);
        return true;
    }

    /**
     * Removes a membership for a vehicle.
     *
     * @param licensePlate The license plate of the vehicle.
     * @return True if the membership is successfully removed, false otherwise.
     */
    public boolean removeMembership(String licensePlate) {
        if (memberships.containsKey(licensePlate)) {
            memberships.remove(licensePlate); 
            return true;
        }
        return false; 
    }

    /**
     * Checks if a vehicle has a membership.
     *
     * @param licensePlate The license plate of the vehicle.
     * @return True if the vehicle has a membership, false otherwise.
     */
    public boolean isMembership(String licensePlate) {
        if (memberships == null) {
            return false;
        } else {
            return memberships.containsKey(licensePlate);
        }
    }

    /**
     * Retrieves the membership of a vehicle.
     *
     * @param licensePlate The license plate of the vehicle.
     * @return The membership object if the vehicle is a member, null otherwise.
     */
    public Membership getMembership(String licensePlate) {
        if (isMembership(licensePlate)) {
            return memberships.get(licensePlate);
        }
        return null;
    }

    /**
     * Retrieves the membership type for a given licensePlate.
     * @param licensePlate The licensePlate of vehicle
     * @return The membership type if the vehicle is a member, null otherwise.
     */
    public MembershipType getMembershipType(String licensePlate) {
        Membership membership = memberships.get(licensePlate);
        if (membership != null) {
            return membership.getType();
        }
        return null;
    }


    /**
     * Strign representation of the membership report
     *
     * @param licensePlate The license plate of the vehicle.
     * @return The membership report if the vehicle is a member, otherwise "Not a member: licensePlate".
     */
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
