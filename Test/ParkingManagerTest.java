import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.function.Predicate;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class ParkingManagerTest extends BaseSetUpTest{

    @Test
    public void testGetTotalCapacity() {
        assertEquals(100, parkingManager.getTotalCapacity(car1));
        assertEquals(50, parkingManager.getTotalCapacity(motorbike1));
        assertEquals(30, parkingManager.getTotalCapacity(truck2));

    }

    @Test
    public void testCarParkingSpace() {
        //already parked two cars
        assertEquals(2, parkingManager.getOccupiedSpaces(car1));

        //a new car is approaching entry
        Vehicle car3 = new Car("ABD987", VehicleType.CAR, LocalDateTime.now(), membershipSystem);

        parkingManager.parkVehicle(car3);

        assertEquals(3, parkingManager.getOccupiedSpaces(car3));
        assertEquals(97, parkingManager.getAvailableSpaces(car3));
    }

    @Test
    public void testTruckParkingSpace() {
        assertEquals(29, parkingManager.getOccupiedSpaces(truck1));

        Vehicle truck3 = new Truck("POI980", VehicleType.TRUCK, LocalDateTime.now(), membershipSystem);
        parkingManager.parkVehicle(truck3);

        assertEquals(30, parkingManager.getOccupiedSpaces(truck3));
        assertEquals(0, parkingManager.getAvailableSpaces(truck3));
    }

    @Test
    public void testMotorbikeParkingSpace() {
        assertEquals(3, parkingManager.getOccupiedSpaces(motorbike1));

        Vehicle motorbike3 = new Motorbike("REW987", VehicleType.MOTORBIKE, LocalDateTime.now(), membershipSystem);
        parkingManager.parkVehicle(motorbike3);
        assertEquals(4, parkingManager.getOccupiedSpaces(motorbike3));
        assertEquals(46, parkingManager.getAvailableSpaces(motorbike3));
    }

    @Test
    public void testHasAvailableSpace() {
        Vehicle truck3 = new Truck("POI980", VehicleType.TRUCK, LocalDateTime.now(), membershipSystem);
        parkingManager.parkVehicle(truck3);
        Vehicle truck4 = new Truck("IUP123", VehicleType.TRUCK, LocalDateTime.now(), membershipSystem);
        parkingManager.hasAvailableSpace(truck4);


        Vehicle motorbike3 = new Motorbike("REW987", VehicleType.MOTORBIKE, LocalDateTime.now(), membershipSystem);
        assertTrue(parkingManager.hasAvailableSpace(motorbike3));
    }


    @Test
    public void testGetLicensePlate() {
        assertEquals("ABC123", car1.getLicensePlate());
        assertEquals("HIJ234", motorbike2.getLicensePlate());
        assertEquals("ABD789", truck2.getLicensePlate());
    }

    @Test
    public void testIsMembership() {
        assertTrue(parkingManager.isMemberVehicle(car1));
        assertFalse(parkingManager.isMemberVehicle(car2));

        assertTrue(parkingManager.isMemberVehicle(truck1));
        assertFalse(parkingManager.isMemberVehicle(motorbike2));
    }


    @Test
    public void testFailParking(){
        Vehicle truck3 = new Truck("POI980", VehicleType.TRUCK, LocalDateTime.now(), membershipSystem);

        assertFalse(parkingManager.isVehicleParked(truck3.getLicensePlate()));
    }


    @Test
    public void testVehicleExitAndHasLeft() {
        parkingManager.vehicleExit(car2);
        parkingManager.vehicleExit(truck2);

        assertNotNull(car2.getLeaveTime());
        assertTrue(parkingManager.vehicleHasLeft(car2, LocalDateTime.now()));
        assertTrue(parkingManager.vehicleHasLeft(truck2, LocalDateTime.now()));

    }

    @Test
    public void testGetParkingRate() {
        float actualCarParkingRate = car2.getParkingRate();
        float expectedCarParkingRate = 2.0F;

        float actualTruckParkingRate = truck2.getParkingRate();
        float expectedTruckParkingRate = 4.5F;

        float actualMotorbikeParkingRate = motorbike2.getParkingRate();
        float expectedMotorbikeParkingRate = 1.5F;

        float delta = 0.0001F;
        assertEquals(expectedCarParkingRate, actualCarParkingRate, delta);
        assertEquals(expectedTruckParkingRate, actualTruckParkingRate, delta);
        assertEquals(actualMotorbikeParkingRate, expectedMotorbikeParkingRate, delta);

    }


    @Test
    public void testDurationTime() {
        LocalDateTime arrivalTime = LocalDateTime.now().minusMinutes(50);
        car2.setArrivalTime(arrivalTime);
        car2.setPaymentTime(arrivalTime.plusMinutes(50));

        paymentSystem.processPayment(car2);

        Duration duration = Duration.between(car2.getArrivalTime(), car2.getPaymentTime());
        long minutes = duration.toMinutes();

        assertEquals(50, minutes);
    }




    @Test
    public void testPaidVehicleToString() {
        paymentSystem.processPayment(car2);
        paymentSystem.processPayment(motorbike2);
        paymentSystem.processPayment(truck2);


        String actual = paymentSystem.toString();
        String expected = "Paid Vehicles: [XYZ789, HIJ234, ABD789]";
        assertEquals(expected, actual);

    }



    @Test
    public  void testCalculateParkingFee() {
        float actualCarFee = ParkingFeeCalculator.calculateParkingFee(1450, 2.0F, VehicleType.CAR);
        assertEquals(32.0F, actualCarFee, 0.001);

        float actualTruckFee = ParkingFeeCalculator.calculateParkingFee(50, 4.5F, VehicleType.TRUCK);
        assertEquals(4.5F, actualTruckFee, 0.001);


        float actualMotorbikeFee = ParkingFeeCalculator.calculateParkingFee(2800, 1.5F, VehicleType.MOTORBIKE);
        assertEquals(30.0F, actualMotorbikeFee, 0.001);

    }


    @Test
    public void testProcessPaymentWithoutMembership() {
        LocalDateTime arrivalTime = LocalDateTime.now().minusMinutes(50);
        car2.setArrivalTime(arrivalTime);
        car2.setPaymentTime(arrivalTime.plusMinutes(50));

        truck2.setArrivalTime(arrivalTime);
        truck2.setPaymentTime(arrivalTime.plusHours(30));

        paymentSystem.processPayment(car2);

        paymentSystem.processPayment(truck2);

        assertEquals(79.0f, paymentSystem.getTotalParkingFees(), 0.0f);

    }




    @Test
    public void testProcessPaymentWithMembership() {
        LocalDateTime arrivalTime = LocalDateTime.now().minusMinutes(50);
        car1.setArrivalTime(arrivalTime);
        car1.setPaymentTime(arrivalTime.plusMinutes(50));

        paymentSystem.processPayment(car1);
        assertEquals(0.0f, paymentSystem.getTotalParkingFees(), 0.0f);
    }



    @Test
    public void testProcessToLeave_LeavesWithinAllowedDuration() {
        LocalDateTime paymentTime = LocalDateTime.now().minusMinutes(5);
        car1.setPaymentTime(paymentTime);

        LocalDateTime leaveTime = paymentTime.plusMinutes(10);
        car1.setLeaveTime(leaveTime);

        assertTrue(parkingManager.processToLeave(car1));

    }


    @Test
    public void testLeaveExceedsAllowedDuration_RechargeParkingFee() {
        LocalDateTime paymentTime = LocalDateTime.now();
        truck2.setPaymentTime(paymentTime);
        motorbike2.setPaymentTime(paymentTime);

        LocalDateTime leaveTime1 = LocalDateTime.now().plusMinutes(21);
        LocalDateTime leaveTime2 = LocalDateTime.now().plusHours(2);
        truck2.setLeaveTime(leaveTime1);
        motorbike2.setLeaveTime(leaveTime2);

        float rechargeFee1 = truck2.rechargeParkingFee();
        assertEquals(4.5F, rechargeFee1, 0.001);

        float rechargeFee2 = motorbike2.rechargeParkingFee();
        assertEquals(3.0F, rechargeFee2, 0.001);

    }


    @Test
    public void testLeaveExceedsAllowedDurationWithMembership() {
        LocalDateTime paymentTime = LocalDateTime.now();
        car1.setPaymentTime(paymentTime);
        motorbike1.setPaymentTime(paymentTime);

        LocalDateTime leaveTime1 = LocalDateTime.now().plusMinutes(21);
        LocalDateTime leaveTime2 = LocalDateTime.now().plusHours(2);
        car1.setLeaveTime(leaveTime1);
        motorbike1.setLeaveTime(leaveTime2);

        float rechargeFee1 = car1.rechargeParkingFee();
        assertEquals(0.0F, rechargeFee1, 0.001);

        float rechargeFee2 = motorbike1.rechargeParkingFee();
        assertEquals(0.0F, rechargeFee2, 0.001);
    }



    @Test
    public void testGateOpen() {
        assertEquals(2, parkingManager.getOccupiedSpaces(car1));
        assertEquals(29, parkingManager.getOccupiedSpaces(truck2));

        parkingManager.openGate(car1);
        parkingManager.openGate(truck2);

        assertEquals(1, parkingManager.getOccupiedSpaces(car1));
        assertFalse(parkedVehicles.containsKey(car1.getLicensePlate()));
        assertFalse(paymentSystem.getPaidVehicles().contains(car1.getLicensePlate()));

        assertEquals(28, parkingManager.getOccupiedSpaces(truck2));
        assertFalse(parkedVehicles.containsKey(truck2.getLicensePlate()));
        assertFalse(paymentSystem.getPaidVehicles().contains(truck2.getLicensePlate()));

    }




    @Test
    public void testParkVehicle() {
        assertTrue(parkingManager.isVehicleParked("ABC123"));
        assertTrue(parkingManager.isVehicleParked("DEF456"));
        assertFalse(parkingManager.isVehicleParked("DEF457"));
    }


    @Test
    public void testRemoveVehicle() {
        String MembershiplicensePlate = "ABC123";
        assertTrue(parkingManager.getMembershipSystem().removeMembership(MembershiplicensePlate));

        String NonmembershiplicensePlate = "ABC124";
        assertFalse(parkingManager.getMembershipSystem().removeMembership(NonmembershiplicensePlate));

    }


    @Test
    public void testVehicleToString() {
        String actual1 = parkingManager.toString();

        System.out.println("Current Vehicle Output:");
        System.out.println(actual1);

        parkingManager.openGate(car1);
        parkingManager.openGate(truck1);
        parkingManager.openGate(motorbike1);
        parkingManager.openGate(car2);
        parkingManager.openGate(truck2);
        parkingManager.openGate(motorbike2);

        String actual2 = parkingManager.toString();
        System.out.println("After removing all Vehicles Output:");
        System.out.println(actual2);

    }



    @Test
    public void testParkingManagerToString() {
        String actual = parkingManager.toString();
        System.out.println("Parking Manager Output:");
        System.out.println(actual);

    }


    @Test
    public void testGetAllVehicles() {
        Map<String, Vehicle> allParkedVehicles = parkingManager.getVehicles(parkedVehicles->true);

        assertEquals(6, allParkedVehicles.size());

        Map<String, Vehicle> allMembershipVehicles = new HashMap<>();
        for (Vehicle vehicle : allParkedVehicles.values()) {
            if (vehicle.isMembership()) {
                allMembershipVehicles.put(vehicle.getLicensePlate(), vehicle);
            }
        }

        assertEquals(3, allMembershipVehicles.size());
    }



    @Test
    public void testGetMembershipVehicles() {
        Predicate<Vehicle> isMember = new Predicate<Vehicle>() {
            @Override
            public boolean test(Vehicle vehicle) {
                MembershipType membershipType = membershipSystem.getMembershipType(vehicle.getLicensePlate());
                return (parkingManager.isMemberVehicle(vehicle) && membershipType == MembershipType.YEARLY);
            }
        };

        Map<String, Vehicle> result = parkingManager.getVehicles(isMember);
        for (Vehicle vehicle : result.values()) {
            System.out.println(vehicle.toString());
        }

    }


    @Test
    public void testGetVehicles() {
        LocalDateTime arrivalTime = LocalDateTime.now().minusMinutes(50);
        car2.setArrivalTime(arrivalTime);
        car2.setPaymentTime(arrivalTime.plusMinutes(50));

        truck2.setArrivalTime(arrivalTime);
        truck2.setPaymentTime(arrivalTime.plusHours(3));

        paymentSystem.processPayment(car2);
        paymentSystem.processPayment(truck2);

        Predicate<Vehicle> parkingFeeExceedAmount = new Predicate<Vehicle>() {
            @Override
            public boolean test(Vehicle vehicle) {
                return (vehicle.getParkingFee() > 10.0);
            }
        };

        Map<String, Vehicle> result = parkingManager.getVehicles(parkingFeeExceedAmount);

        for (Vehicle vehicle : result.values()) {
            System.out.println(vehicle.toString());
        }

    }




    @Test
    public void testMembershipVehicleCount() {
        Predicate<Vehicle> isMember = new Predicate<Vehicle>() {
            @Override
            public boolean test(Vehicle vehicle) {
                return parkingManager.isMemberVehicle(vehicle);
            }
        };

        int numberOfMembers = parkingManager.count(isMember);

        assertEquals(3, numberOfMembers);
    }

    @Test
    public void testTotalVehicleCount() {
        Predicate<Vehicle> allVehiclesPredicate = vehicle -> true;

        int totalVehicleCount = parkingManager.count(allVehiclesPredicate);
        assertEquals(6, totalVehicleCount);

        Vehicle truck3 = new Truck("POI980", VehicleType.TRUCK, LocalDateTime.now(), membershipSystem);

        parkingManager.parkVehicle(truck3);

        int newTotalVehicleCount = parkingManager.count(allVehiclesPredicate);
        assertEquals(7, newTotalVehicleCount);

    }



}