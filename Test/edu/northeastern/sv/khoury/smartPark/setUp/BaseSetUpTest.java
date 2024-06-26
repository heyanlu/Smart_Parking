package edu.northeastern.sv.khoury.smartPark.setUp;

import edu.northeastern.sv.khoury.smartPark.model.Car;
import edu.northeastern.sv.khoury.smartPark.model.MembershipSystem;
import edu.northeastern.sv.khoury.smartPark.model.MembershipType;
import edu.northeastern.sv.khoury.smartPark.model.Motorbike;
import edu.northeastern.sv.khoury.smartPark.model.ParkingManager;
import edu.northeastern.sv.khoury.smartPark.model.ParkingRates;
import edu.northeastern.sv.khoury.smartPark.model.PaymentSystem;
import edu.northeastern.sv.khoury.smartPark.model.Truck;
import edu.northeastern.sv.khoury.smartPark.model.Vehicle;
import edu.northeastern.sv.khoury.smartPark.model.VehicleType;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;

/**
 * Set up the basic component for the ParkingMangerTest, MainCustomer and MainManager to avoid repetition of cde
 */
public class BaseSetUpTest {
  //Set the parkingManger and paymentSystem as public as they are used by class from other packages
  public static ParkingManager parkingManager;
  public static PaymentSystem paymentSystem;

  //Set the remaining of variables as protected
  protected Map<VehicleType, Integer> capacityMap;
  protected Map<String, Vehicle> parkedVehicles;
  protected Map<VehicleType, Integer> occupiedSpaces;
  protected static MembershipSystem membershipSystem;
  protected Vehicle car1;
  protected Vehicle car2;
  protected Vehicle motorbike1;
  protected Vehicle motorbike2;
  protected Vehicle truck1;
  protected Vehicle truck2;

  /**
   * Initialize for the components of the parking lot system, including create membershipSystem and add
   * membership vehicles, initialize parking capacity, create new vehicles and add them to parking lot system.
   */
  @Before
  public void setUp() {
    LocalDateTime now = LocalDateTime.now();

    //Set up membership system and add new membership vehicle objects.
    membershipSystem = new MembershipSystem();
    membershipSystem.addMembership("ABC123", MembershipType.YEARLY, now);
    membershipSystem.addMembership("DEF456", MembershipType.MONTHLY, now);
    membershipSystem.addMembership("GHI789", MembershipType.WEEKLY, now);

    //Initialize the total capacity for vehicle objects.
    capacityMap = new HashMap<>();
    capacityMap.put(VehicleType.CAR, 100);
    capacityMap.put(VehicleType.MOTORBIKE, 50);
    capacityMap.put(VehicleType.TRUCK, 30);

    //Initialize the occupied capacity for vehicle objects.
    occupiedSpaces = new HashMap<>();
    occupiedSpaces.put(VehicleType.CAR, 0);
    occupiedSpaces.put(VehicleType.MOTORBIKE, 1);
    occupiedSpaces.put(VehicleType.TRUCK, 27);

    //Initialize the payment system for vehicle objects.
    paymentSystem = new PaymentSystem();
    parkedVehicles = new HashMap<>();

    //Initialize the parking rate for vehicle objects.
    Map<VehicleType, Float> parkingRates = new HashMap<>();
    parkingRates.put(VehicleType.CAR, ParkingRates.CAR.getRate());
    parkingRates.put(VehicleType.MOTORBIKE, ParkingRates.MOTORBIKE.getRate());
    parkingRates.put(VehicleType.TRUCK, ParkingRates.TRUCK.getRate());

    //Initialize parking manager.
    parkingManager = new ParkingManager(capacityMap, occupiedSpaces,
        membershipSystem, paymentSystem);

    //Create new vehicle objects and park them into the parking lot.
    car1 = new Car(
        "ABC123",
        VehicleType.CAR,
        now,
        null,
        null,
        membershipSystem);

    car2 = new Car(
        "XYZ789",
        VehicleType.CAR,
        now,
        null,
        null,
        membershipSystem);

    motorbike1 = new Motorbike(
        "DEF456",
        VehicleType.MOTORBIKE,
        now,
        null,
        null,
        membershipSystem);

    motorbike2 = new Motorbike(
        "HIJ234",
        VehicleType.MOTORBIKE,
        now,
        null,
        null,
        membershipSystem);

    truck1 = new Truck(
        "GHI789",
        VehicleType.TRUCK,
        now,
        null,
        null,
        membershipSystem);

    truck2 = new Truck(
        "ABD789",
        VehicleType.TRUCK,
        now,
        null,
        null,
        membershipSystem);

    parkingManager.parkVehicle(car1);
    parkingManager.parkVehicle(car2);
    parkingManager.parkVehicle(motorbike1);
    parkingManager.parkVehicle(motorbike2);
    parkingManager.parkVehicle(truck1);
    parkingManager.parkVehicle(truck2);

  }
}
