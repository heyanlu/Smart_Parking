import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;

public class BaseSetUpTest {

  protected static ParkingManager parkingManager;
  protected Map<VehicleType, Integer> capacityMap;
  protected Map<String, Vehicle> parkedVehicles;
  protected Map<VehicleType, Integer> occupiedSpaces;
  protected static PaymentSystem paymentSystem;

  protected static MembershipSystem membershipSystem;


  protected Vehicle car1;
  protected Vehicle car2;
  protected Vehicle motorbike1;
  protected Vehicle motorbike2;
  protected Vehicle truck1;
  protected Vehicle truck2;

  @Before
  public void setUp() {

    LocalDateTime now = LocalDateTime.now();

    membershipSystem = new MembershipSystem();

    membershipSystem.addMembership("ABC123", MembershipType.YEARLY, now);
    membershipSystem.addMembership("DEF456", MembershipType.MONTHLY, now);
    membershipSystem.addMembership("GHI789", MembershipType.WEEKLY, now);


    capacityMap = new HashMap<>();
    capacityMap.put(VehicleType.CAR, 100);
    capacityMap.put(VehicleType.MOTORBIKE, 50);
    capacityMap.put(VehicleType.TRUCK, 30);

    occupiedSpaces = new HashMap<>();
    occupiedSpaces.put(VehicleType.CAR, 0);
    occupiedSpaces.put(VehicleType.MOTORBIKE, 1);
    occupiedSpaces.put(VehicleType.TRUCK, 27);

    paymentSystem = new PaymentSystem();
    parkedVehicles = new HashMap<>();


    Map<VehicleType, Float> parkingRates = new HashMap<>();
    parkingRates.put(VehicleType.CAR, ParkingRates.CAR.getRate());
    parkingRates.put(VehicleType.MOTORBIKE, ParkingRates.MOTORBIKE.getRate());
    parkingRates.put(VehicleType.TRUCK, ParkingRates.TRUCK.getRate());


    parkingManager = new ParkingManager(capacityMap, occupiedSpaces,
        membershipSystem, paymentSystem);


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
