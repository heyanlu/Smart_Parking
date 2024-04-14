import java.time.Duration;
import java.time.LocalDateTime;
import java.util.function.Predicate;
import org.junit.Test;
import java.util.Map;
import static org.junit.Assert.*;

public class ParkingManagerTest extends BaseSetUpTest{

    @Test
    public void testGetTotalCapacity() {
        assertEquals(100, parkingManager.getTotalCapacity(VehicleType.CAR));
        assertEquals(50, parkingManager.getTotalCapacity(VehicleType.MOTORBIKE));
        assertEquals(30, parkingManager.getTotalCapacity(VehicleType.TRUCK));

    }

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

    @Test
    public void testTruckParkingSpace() {
        assertEquals(29, parkingManager.getOccupiedSpaces(VehicleType.TRUCK));

        Vehicle truck3 = new Truck("POI980", VehicleType.TRUCK, LocalDateTime.now(), null, null, membershipSystem);
        parkingManager.parkVehicle(truck3);

        assertEquals(30, parkingManager.getOccupiedSpaces(VehicleType.TRUCK));
        assertEquals(0, parkingManager.getAvailableSpaces(VehicleType.TRUCK));
    }

    @Test
    public void testMotorbikeParkingSpace() {
        assertEquals(3, parkingManager.getOccupiedSpaces(VehicleType.MOTORBIKE));

        Vehicle motorbike3 = new Motorbike("REW987", VehicleType.MOTORBIKE, LocalDateTime.now(), null, null, membershipSystem);
        parkingManager.parkVehicle(motorbike3);
        assertEquals(4, parkingManager.getOccupiedSpaces(VehicleType.MOTORBIKE));
        assertEquals(46, parkingManager.getAvailableSpaces(VehicleType.MOTORBIKE));
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
    public void testCreateVehicle() {
        String licensePlate = "ABJ123";
        LocalDateTime arrivalTime = LocalDateTime.of(2022, 4, 3, 19, 1); // Example arrival time
        VehicleType vehicleType = VehicleType.CAR;

        Vehicle vehicle = parkingManager.createVehicle(licensePlate, vehicleType);

        assertEquals(licensePlate, vehicle.getLicensePlate());
        assertEquals(vehicleType, vehicle.getType());
    }


    @Test
    public void testFailParking(){
        Vehicle truck3 = new Truck("POI980", VehicleType.TRUCK, LocalDateTime.now(), null, null, membershipSystem);

        assertFalse(parkingManager.isVehicleParked(truck3.getLicensePlate()));
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
        String expected = "Paid Vehicles: [XYZ789, ABD789, HIJ234]";
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

        truck2.setArrivalTime(arrivalTime);

        paymentSystem.processPayment(car2);

        paymentSystem.processPayment(truck2);

        assertEquals(6.5f, paymentSystem.getTotalParkingFees(), 0.0f);

    }


    @Test
    public void testProcessPaymentWithMembership() {
        LocalDateTime arrivalTime = LocalDateTime.now().minusMinutes(50);
        car1.setArrivalTime(arrivalTime);

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


    @Test (expected = IllegalStateException.class)
    public void testProcessToLeaveWithException() {
        Vehicle truck3 = new Truck("POI980", VehicleType.TRUCK, LocalDateTime.now(), null, null, membershipSystem);

        assertTrue(parkingManager.processToLeave(truck3));
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




    @Test
    public void testParkVehicle() {
        assertTrue(parkingManager.isVehicleParked("ABC123"));
        assertTrue(parkingManager.isVehicleParked("DEF456"));
        assertFalse(parkingManager.isVehicleParked("DEF457"));

        Vehicle car3 = new Truck("POI980", VehicleType.TRUCK, LocalDateTime.now(), null, null, membershipSystem);
        parkingManager.parkVehicle(car3);
        assertTrue(parkingManager.isVehicleParked("POI980"));
    }

    @Test(expected = IllegalStateException.class)
    public void testParkVehicleExpectedError() {
        assertEquals(29, parkingManager.getOccupiedSpaces(VehicleType.TRUCK));
        Vehicle truck3 = new Truck("POI980", VehicleType.TRUCK, LocalDateTime.now(), null, null, membershipSystem);
        Vehicle truck4 = new Truck("POI981", VehicleType.TRUCK, LocalDateTime.now(), null, null, membershipSystem);
        parkingManager.parkVehicle(truck3);

        parkingManager.parkVehicle(truck4);
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
        Predicate<Vehicle> predicateFilterCar = vehicle -> vehicle.getType().equals(VehicleType.CAR);
        Map<String, Vehicle> filteredCar = parkingManager.getVehicles(predicateFilterCar);
        assertEquals(2, filteredCar.size());

        // Test filtering vehicles based on ALL predicate
        Predicate<Vehicle> predicateFilterAll = vehicle -> true;
        Map<String, Vehicle> filteredVehicles = parkingManager.getVehicles(predicateFilterAll);
        assertEquals(6, filteredVehicles.size());

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
    public void testMembershipReportToString() {
        String report = membershipSystem.generateMembershipReport("ABC123");
        System.out.println(report);
    }

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