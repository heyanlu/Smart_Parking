package edu.northeastern.sv.khoury.smartParkTest.modelTest;

import edu.northeastern.sv.khoury.smartParkTest.model.Car;
import edu.northeastern.sv.khoury.smartParkTest.model.MembershipType;
import edu.northeastern.sv.khoury.smartParkTest.model.Motorbike;
import edu.northeastern.sv.khoury.smartParkTest.model.ParkingFeeCalculator;
import edu.northeastern.sv.khoury.smartParkTest.model.Truck;
import edu.northeastern.sv.khoury.smartParkTest.model.Vehicle;
import edu.northeastern.sv.khoury.smartParkTest.model.VehicleType;
import edu.northeastern.sv.khoury.smartParkTest.setUp.BaseSetUpTest;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.function.Predicate;
import org.junit.Test;
import java.util.Map;
import static org.junit.Assert.*;

/**
 * This class contains unit tests for the ParkingManager class and paymentSystem class, which manages parking operations.
 * This class will extend BasedSetUpTest.
 */
public class ParkingManagerTest extends BaseSetUpTest {

    /**
     * Test case for the getTotalCapacity() method for all the three vehicle types in the ParkingManager class.
     */
    @Test
    public void testGetTotalCapacity() {
        assertEquals(100, parkingManager.getTotalCapacity(VehicleType.CAR));
        assertEquals(50, parkingManager.getTotalCapacity(VehicleType.MOTORBIKE));
        assertEquals(30, parkingManager.getTotalCapacity(VehicleType.TRUCK));
    }

    /**
     * Test case for parking space availability for car.
     * It checks if cars can be parked and the available spaces are updated accordingly.
     */
    @Test
    public void testCarParkingSpace() {
        //already parked two cars
        assertEquals(2, parkingManager.getOccupiedSpaces(VehicleType.CAR));

        //a new car is approaching entry
        Vehicle car3 = new Car("ABD987", VehicleType.CAR, LocalDateTime.now(), null, null, null);
        parkingManager.parkVehicle(car3);

        assertEquals(3, parkingManager.getOccupiedSpaces(VehicleType.CAR));
        assertEquals(97, parkingManager.getAvailableSpaces(VehicleType.CAR));
    }

    /**
     * Test case for parking space availability for trucks.
     * It checks if trucks can be parked and the available spaces are updated accordingly.
     */
    @Test
    public void testTruckParkingSpace() {
        assertEquals(29, parkingManager.getOccupiedSpaces(VehicleType.TRUCK));

        Vehicle truck3 = new Truck("POI980", VehicleType.TRUCK, LocalDateTime.now(), null, null, membershipSystem);
        parkingManager.parkVehicle(truck3);

        assertEquals(30, parkingManager.getOccupiedSpaces(VehicleType.TRUCK));
        assertEquals(0, parkingManager.getAvailableSpaces(VehicleType.TRUCK));
    }

    /**
     * Test case for parking space availability for motorbikes.
     * It checks if motorbikes can be parked and the available spaces are updated accordingly.
     */
    @Test
    public void testMotorbikeParkingSpace() {
        assertEquals(3, parkingManager.getOccupiedSpaces(VehicleType.MOTORBIKE));

        Vehicle motorbike3 = new Motorbike("REW987", VehicleType.MOTORBIKE, LocalDateTime.now(), null, null, membershipSystem);
        parkingManager.parkVehicle(motorbike3);
        assertEquals(4, parkingManager.getOccupiedSpaces(VehicleType.MOTORBIKE));
        assertEquals(46, parkingManager.getAvailableSpaces(VehicleType.MOTORBIKE));
    }

    /**
     * Test case for getLicensePlate of three types of vehicles.
     */
    @Test
    public void testGetLicensePlate() {
        assertEquals("ABC123", car1.getLicensePlate());
        assertEquals("HIJ234", motorbike2.getLicensePlate());
        assertEquals("ABD789", truck2.getLicensePlate());
    }

    /**
     * Test case for isMembership for three types of vehicles.
     */
    @Test
    public void testIsMembership() {
        //car1 is already set as membership vehicle in BasedSetUpTest.
        assertTrue(parkingManager.isMemberVehicle(car1));
        assertFalse(parkingManager.isMemberVehicle(car2));

        //truck1 is already set as membership vehicle in BasedSetUpTest.
        assertTrue(parkingManager.isMemberVehicle(truck1));
        assertFalse(parkingManager.isMemberVehicle(motorbike2));
    }

    /**
     * Test case for createVehicle method to check if create vehicle will render a vehicle object
     * based on vehicle type and license plate.
     */
    @Test
    public void testCreateVehicle() {
        String licensePlate = "ABJ123";
        VehicleType vehicleType = VehicleType.CAR;
        Vehicle vehicle = parkingManager.createVehicle(licensePlate, vehicleType);

        assertEquals(licensePlate, vehicle.getLicensePlate());
        assertEquals(vehicleType, vehicle.getType());
    }

    /**
     * Test case for createVehicle failure when licensePlate is null
     */
    @Test
    public void testCreateVehicleWithNullLicensePlate() {
        String licensePlate = null;
        VehicleType vehicleType = VehicleType.CAR;
        Vehicle vehicle = parkingManager.createVehicle(licensePlate, vehicleType);
        assertNull(vehicle);
    }

    /**
     * Test case for getParkingRate for three types of vehicles.
     */
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

    /**
     * Test case for duration time method to check if duration can update accordingly when vehicle object
     * call processPayment.
     */
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

    /**
     * Test case for toString method and getPaidVehicle method for three types of vehicles.
     * It expected to return a list of vehicle objects that has payed the parking fee.
     */
    @Test
    public void testPaidVehicleToString() {
        paymentSystem.processPayment(car2);
        paymentSystem.processPayment(motorbike2);
        paymentSystem.processPayment(truck2);

        String actual = paymentSystem.toString();
        String expected = "Paid Vehicles: [XYZ789, ABD789, HIJ234]";
        assertEquals(expected, actual);

    }

    /**
     * Test case for assignParkingPlace method to check if it can assign parking location based on
     * current occupied spaces to a new vehicle.
     */
    @Test
    public void testAssignParkingPlace() {
        Vehicle car = new Car("ABC124", VehicleType.CAR, LocalDateTime.now(), null, null, membershipSystem);
        Vehicle motorbike = new Truck("AAA111", VehicleType.MOTORBIKE, LocalDateTime.now(), null, null, membershipSystem);

        if (parkingManager.parkVehicle(car)) {
            assertEquals("C4", parkingManager.assignParkingPlace(VehicleType.CAR));
        } else {
            fail("Failed to park.");
        }

        if (parkingManager.parkVehicle(motorbike)) {
            assertEquals("M4", parkingManager.assignParkingPlace(VehicleType.MOTORBIKE));
        } else {
            fail("Failed to park.");
        }
    }

    /**
     * Test case for calculating ParkingFee for three types of vehicles.
     * Each of them has different parking durations that cover different fee calculate situations.
     */
    @Test
    public  void testCalculateParkingFee() {
        float actualCarFee = ParkingFeeCalculator.calculateParkingFee(1450, 2.0F, VehicleType.CAR);
        assertEquals(32.0F, actualCarFee, 0.001);

        float actualTruckFee = ParkingFeeCalculator.calculateParkingFee(50, 4.5F, VehicleType.TRUCK);
        assertEquals(4.5F, actualTruckFee, 0.001);

        float actualMotorbikeFee = ParkingFeeCalculator.calculateParkingFee(2800, 1.5F, VehicleType.MOTORBIKE);
        assertEquals(30.0F, actualMotorbikeFee, 0.001);
    }

    /**
     * Test case for process to leave without membership.
     * Check if parking fee can be calculated correctly.
     */
    @Test
    public void testProcessPaymentWithoutMembership() {
        LocalDateTime arrivalTime = LocalDateTime.now().minusMinutes(50);
        car2.setArrivalTime(arrivalTime);
        truck2.setArrivalTime(arrivalTime);

        paymentSystem.processPayment(car2);
        paymentSystem.processPayment(truck2);

        assertEquals(6.5f, paymentSystem.getTotalParkingFees(), 0.0f);
    }

    /**
     * Test case for process to leave with membership.
     * Check if parking fee is 0 for membership vehicles. .
     */
    @Test
    public void testProcessPaymentWithMembership() {
        LocalDateTime arrivalTime = LocalDateTime.now().minusMinutes(50);
        car1.setArrivalTime(arrivalTime);

        paymentSystem.processPayment(car1);
        assertEquals(0.0f, paymentSystem.getTotalParkingFees(), 0.0f);
    }


    /**
     * Test case for process to leave within allowed duration (20 minutes after paying the parking fee)
     * to check if the processToLeave will return True.
     */
    @Test
    public void testProcessToLeave_LeavesWithinAllowedDuration() {
        LocalDateTime paymentTime = LocalDateTime.now().minusMinutes(5);
        car1.setPaymentTime(paymentTime);

        LocalDateTime leaveTime = paymentTime.plusMinutes(10);
        car1.setLeaveTime(leaveTime);

        assertTrue(parkingManager.processToLeave(car1));
    }

    /**
     * Test case for when process to leave exceeds allowed duration, if the recharge parking fee
     * will update the recharging fee correctly.
     */
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


    /**
     * Test case for process to leave when vehicle has not paid the parking fee.
     * It expected to return IllegalStateException as the system enforce vehicle to pay before leave.
     */
    @Test (expected = IllegalStateException.class)
    public void testProcessToLeaveWithException() {
        Vehicle truck3 = new Truck("POI980", VehicleType.TRUCK, LocalDateTime.now(), null, null, membershipSystem);
        assertTrue(parkingManager.processToLeave(truck3));
    }

    /**
     * Test case for recharging fee method when payment time is null or vehicle has not paid first.
     * It expected to return IllegalStateException as the system for user to pay all the fee before leaving the parking lot.
     */
    @Test(expected = IllegalStateException.class)
    public void testRechargeParkingFeeNoPaymentTime() {
        LocalDateTime leaveTime = LocalDateTime.now();
        motorbike2.setLeaveTime(leaveTime);
        motorbike2.rechargeParkingFee();
    }

    /**
     * Test case for recharging method when vehicle is not parked in the parking lot.
     * It expected to return IllegalStateException when the vehicle is not in the parking lot.
     */
    @Test(expected = IllegalStateException.class)
    public void testRechargeParkingFeeNotParked() {
        Vehicle truck = new Truck("POP111", VehicleType.TRUCK, null, null, null, membershipSystem);
        truck.rechargeParkingFee();
    }

    /**
     * Test for isPaidRechargeParkingFee method. Currently, this method is set to true, as I have
     * not coded the receive payment part.
     */
    @Test
    public void testIsPaidRechargeParkingFee() {
        assertTrue(truck2.isPaidRechargeParkingFee());
    }

    /**
     * Test for recharge payment method for membership vehicles.
     * Membership vehicles would not be charged the rechargeFee.
     */
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

    /**
     * Test for gate open method. If gate is opened for a vehicle, vehicle should be removed from
     * parkedVehicles list and paidVehicles list. So when the vehicle reentry, it starts a new parking.
     */
    @Test
    public void testGateOpen() {
        assertEquals(2, parkingManager.getOccupiedSpaces(VehicleType.CAR));
        assertEquals(29, parkingManager.getOccupiedSpaces(VehicleType.TRUCK));

        parkingManager.openGate(car1);
        parkingManager.openGate(truck2);

        assertEquals(1, parkingManager.getOccupiedSpaces(VehicleType.CAR));
        assertFalse(parkedVehicles.containsKey(car1.getLicensePlate()));
        assertFalse(paymentSystem.getPaidVehicles().containsKey(car1.getLicensePlate()));

        assertEquals(28, parkingManager.getOccupiedSpaces(VehicleType.TRUCK));
        assertFalse(parkedVehicles.containsKey(truck2.getLicensePlate()));
        assertFalse(paymentSystem.getPaidVehicles().containsKey(truck2.getLicensePlate()));
    }

    /**
     * Test case for isVehicleParked for those already parked and not yet parked vehicles objects.
     */
    @Test
    public void testParkVehicle() {
        //already parked vehicles objects
        assertTrue(parkingManager.isVehicleParked("ABC123"));
        assertTrue(parkingManager.isVehicleParked("DEF456"));

        //already parked vehicles objects
        assertFalse(parkingManager.isVehicleParked("DEF457"));

        Vehicle car3 = new Truck("POI980", VehicleType.TRUCK, LocalDateTime.now(), null, null, membershipSystem);
        parkingManager.parkVehicle(car3);
        assertTrue(parkingManager.isVehicleParked("POI980"));
    }


    /**
     * Test case for isVehicleParked when vehicle is not currently parked in parking lot.
     */
    @Test
    public void testIsVehicleParkedFail(){
        Vehicle truck3 = new Truck("POI980", VehicleType.TRUCK, LocalDateTime.now(), null, null, membershipSystem);
        assertFalse(parkingManager.isVehicleParked(truck3.getLicensePlate()));
    }

    /**
     * Test case for remove Vehicle to check if it can successfully remove a vehicle from a membership
     */
    @Test
    public void testRemoveVehicle() {
        String MembershiplicensePlate = "ABC123";
        assertTrue(parkingManager.getMembershipSystem().removeMembership(MembershiplicensePlate));

        String NonmembershiplicensePlate = "ABC124";
        assertFalse(parkingManager.getMembershipSystem().removeMembership(NonmembershiplicensePlate));
    }

    /**
     * Test the toString method and openGame method to check if the parking vehicles will be successfully
     * remove when the game opens (exit the parking lot).
     */
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

    /**
     * Test code for toString of the parking manager.
     * It supposes to return the parking manager status, including the total capacity, occupied space,
     * gate status, all parked vehicles.
     */
    @Test
    public void testParkingManagerToString() {
        String actual = parkingManager.toString();
        //Matching the expected and actual values using assertEquals(expected, actual) can be tricky because
        //they might not be exactly equal due to a small time gap of about 0.005 seconds between their executions.
        //So here I use system.out.println
        System.out.println(actual);
    }

    /**
     * Test code for filtering vehicles based on vehicle type and a specific predicate.
     */
    @Test
    public void testGetAllVehicles() {
        //filtering vehicles based on vehicle type
        Predicate<Vehicle> predicateFilterCar = vehicle -> vehicle.getType().equals(VehicleType.CAR);
        Map<String, Vehicle> filteredCar = parkingManager.getVehicles(predicateFilterCar);
        assertEquals(2, filteredCar.size());

        //filtering vehicles based on all predicate
        Predicate<Vehicle> predicateFilterAll = vehicle -> true;
        Map<String, Vehicle> filteredVehicles = parkingManager.getVehicles(predicateFilterAll);
        assertEquals(6, filteredVehicles.size());
    }

    /**
     * Test case for retrieval of vehicles with membership.
     */
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

    /**
     * Test case for retrieval of vehicles based on parking fee exceeding a certain amount (10).
     * It should output detail of truck2 at the terminal, including license plate, type, arrival time, payment time,
     * leave time, membership status.
     */
    @Test
    public void testGetVehicles() {
        LocalDateTime arrivalTimeCar = LocalDateTime.now().minusMinutes(50);
        car2.setArrivalTime(arrivalTimeCar);

        LocalDateTime arrivalTimeTruck = LocalDateTime.now().minusHours(3);
        truck2.setArrivalTime(arrivalTimeTruck);

        paymentSystem.processPayment(car2);
        paymentSystem.processPayment(truck2);

        Predicate<Vehicle> parkingFeeExceedAmount = new Predicate<Vehicle>() {
            @Override
            public boolean test(Vehicle vehicle) {
                return (vehicle.getParkingFee() > 10.0);
            }
        };

        Map<String, Vehicle> result = parkingManager.getVehicles(parkingFeeExceedAmount);

        //Matching the expected and actual values using assertEquals(expected, actual) can be tricky because
        //they might not be exactly equal due to a small time gap of about 0.005 seconds between their executions.
        //So here I use system.out.println
        for (Vehicle vehicle : result.values()) {
            System.out.println(vehicle.toString());
        }
    }

    /**
     * Test case for retrieval of vehicles based on if they are membership.
     * It will return the number of vehicles with membership.
     */
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

    /**
     * Test the membership report of one vehicle. It should output the type of membership, when it starts
     * and when it ends.
     */
    @Test
    public void testMembershipReportToString() {
        String report = membershipSystem.generateMembershipReport("ABC123");

        //Matching the expected and actual values using assertEquals(expected, actual) can be tricky because
        //they might not be exactly equal due to a small time gap of about 0.005 seconds between their executions.
        //So here I use system.out.println
        System.out.println(report);
    }

    /**
     * Test case for the total number of vehicles currently parked in the parking system.
     */
    @Test
    public void testTotalVehicleCount() {
        Predicate<Vehicle> allVehiclesPredicate = vehicle -> true;

        int totalVehicleCount = parkingManager.count(allVehiclesPredicate);
        assertEquals(6, totalVehicleCount);

        Vehicle truck3 = new Truck("POI980", VehicleType.TRUCK, LocalDateTime.now(), null, null, membershipSystem);

        parkingManager.parkVehicle(truck3);

        int newTotalVehicleCount = parkingManager.count(allVehiclesPredicate);
        assertEquals(7, newTotalVehicleCount);

    }
}