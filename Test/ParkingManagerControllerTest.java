import edu.northeastern.sv.khoury.smartParkTest.controller.ParkingManagerController;
import edu.northeastern.sv.khoury.smartParkTest.mock.ParkingManagerMock;
import edu.northeastern.sv.khoury.smartParkTest.mock.ParkingManagerViewMock;
import edu.northeastern.sv.khoury.smartParkTest.mock.PaymentSystemMock;
import edu.northeastern.sv.khoury.smartParkTest.model.Car;
import edu.northeastern.sv.khoury.smartParkTest.model.MembershipSystem;
import edu.northeastern.sv.khoury.smartParkTest.model.Vehicle;
import edu.northeastern.sv.khoury.smartParkTest.model.VehicleType;
import java.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ParkingManagerControllerTest {
  private ParkingManagerMock parkingManager;
  private PaymentSystemMock paymentSystem;

  private MembershipSystem membershipSystem;

  private ParkingManagerController controller;
  private ParkingManagerViewMock view;

  @Before
  public void setUp() {
    membershipSystem = new MembershipSystem();
    parkingManager = new ParkingManagerMock(membershipSystem);
    paymentSystem = new PaymentSystemMock();
    controller = new ParkingManagerController(parkingManager, paymentSystem);
    view = new ParkingManagerViewMock("Parking Manager");
    controller.setView(view);
  }

  @Test
  public void testTotalParkingCapacityOption() {
    view.setVehicleType(VehicleType.CAR);
    controller.optionExecution("Total Parking Capacity");
    String expectedCar = "Total Parking Capacity: 20";
    assertEquals(expectedCar, view.getMessage());

    view.setVehicleType(VehicleType.MOTORBIKE);
    controller.optionExecution("Total Parking Capacity");
    String expectedMotorbike = "Total Parking Capacity: 5";
    assertEquals(expectedMotorbike, view.getMessage());
  }

  @Test
  public void testAvailableParkingCapacityOption() {
    view.setVehicleType(VehicleType.MOTORBIKE);
    controller.optionExecution("Available Parking Capacity");
    String expectedMotorbike = "Available Parking Spaces: 0";
    assertEquals(expectedMotorbike, view.getMessage());

    view.setVehicleType(VehicleType.TRUCK);
    controller.optionExecution("Available Parking Capacity");
    String expectedTruck = "Available Parking Spaces: 5";
    assertEquals(expectedTruck, view.getMessage());
  }

  @Test
  public void testVehicleDetailsOption() {
    Vehicle vehicle = new Car("ABC123", VehicleType.CAR, LocalDateTime.now(), null, null, null);

    String expected = "edu.northeastern.sv.khoury.smartPark.model.Vehicle{" +
        "\n\tlicensePlate='ABC123'" +
        ",\n\ttype=CAR" +
        ",\n\tarrivalTime=" + vehicle.getArrivalTime() +
        ",\n\tpaymentTime=null" +
        ",\n\tleaveTime=null" +
        ",\n\tmembership=unknown" +
        '}';
    controller.optionExecution("edu.northeastern.sv.khoury.smartPark.model.Vehicle Details");
    assertEquals(expected, vehicle.toString());
  }


  @Test
  public void testMembershipStatusOptionWithMembership() {
    view.setLicensePlateInput("ABC123");
    controller.optionExecution("edu.northeastern.sv.khoury.smartPark.model.Membership Status");
    String expected = "edu.northeastern.sv.khoury.smartPark.model.Vehicle is not a member.";
    assertEquals(expected, view.getMessage());
  }

  @Test
  public void testMembershipStatusOptionWithoutMembership() {
    view.setLicensePlateInput("ABC567");
    controller.optionExecution("edu.northeastern.sv.khoury.smartPark.model.Membership Status");
    String expected = "edu.northeastern.sv.khoury.smartPark.model.Vehicle is not a member.";
    assertEquals(expected, view.getMessage());
  }

}
