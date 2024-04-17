package edu.northeastern.sv.khoury.smartParkTest.controllerTest;

import edu.northeastern.sv.khoury.smartParkTest.controller.ParkingManagerController;
import edu.northeastern.sv.khoury.smartParkTest.mock.ParkingManagerJFrameViewMock;
import edu.northeastern.sv.khoury.smartParkTest.mock.ParkingManagerMock;
import edu.northeastern.sv.khoury.smartParkTest.model.MembershipSystem;
import edu.northeastern.sv.khoury.smartParkTest.model.VehicleType;
import edu.northeastern.sv.khoury.smartParkTest.view.IParkingManagerView;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test class for ParkingManagerController.
 */
public class ParkingManagerControllerTest {
  private ParkingManagerMock parkingManager;
  private MembershipSystem membershipSystem;
  private ParkingManagerController controller;
  private StringBuilder log;
  private IParkingManagerView view;


  /**
   * Set up for the test code
   */
  @Before
  public void setUp() {
    membershipSystem = new MembershipSystem();
    log = new StringBuilder();
    int uniqueCode = 12345;
    parkingManager = new ParkingManagerMock(membershipSystem, log,
        uniqueCode);
    view = new ParkingManagerJFrameViewMock(log);

    controller = new ParkingManagerController(parkingManager,  view);
    log.setLength(0);

  }

  /**
   * Test the Total Parking Capacity option for Car.
   */
  @Test
  public void testTotalParkingCapacityOptionCar() {
    VehicleType userInput = VehicleType.CAR;
    String option = "Total Parking Capacity Button";

    controller.optionExecution(option);

    assertEquals("CAR", userInput.toString());
    assertTrue(log.toString().contains("Check total capacity for vehicle. Unique Code: 12345."));
  }

  /**
   * Test the Total Parking Capacity option for motorbike.
   */
  @Test
  public void testTotalParkingCapacityOptionMotorbike() {
    VehicleType userInput = VehicleType.MOTORBIKE;
    String option = "Total Parking Capacity Button";

    controller.optionExecution(option);

    assertEquals("MOTORBIKE", userInput.toString());
    assertTrue(log.toString().contains("Check total capacity for vehicle. Unique Code: 12345."));
  }


  /**
   * Test for the available Capacity option for truck.
   */
  @Test
  public void testAvailableParkingCapacityOptionTruck() {
    VehicleType userInput = VehicleType.TRUCK;
    String option = "Total Parking Capacity Button";

    controller.optionExecution(option);

    assertEquals("TRUCK", userInput.toString());
    assertTrue(log.toString().contains("Check total capacity for vehicle. Unique Code: 12345."));
  }


  /**
   * Test code for the membership status.
   */
  @Test
  public void testIsMemberVehicle() {
    String option = "Membership Status Button";
    controller.optionExecution(option);
    assertTrue(log.toString().contains("Mock getMembershipSystem called."));
  }

  /**
   * Test vehicle details method.
   */
  @Test
  public void testVehicleDetails() {
    String option = "Vehicle Details Button";
    controller.optionExecution(option);
    assertTrue(log.toString().contains("Mock getParkedVehicles called."));
  }

}
