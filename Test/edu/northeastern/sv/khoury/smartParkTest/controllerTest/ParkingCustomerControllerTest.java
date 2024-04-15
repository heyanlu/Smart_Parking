package edu.northeastern.sv.khoury.smartParkTest.controllerTest;

import edu.northeastern.sv.khoury.smartParkTest.controller.ParkingCustomerController;
import edu.northeastern.sv.khoury.smartParkTest.mock.ParkingCustomerViewMock;
import edu.northeastern.sv.khoury.smartParkTest.mock.ParkingManagerMock;
import edu.northeastern.sv.khoury.smartParkTest.mock.PaymentSystemMock;
import edu.northeastern.sv.khoury.smartParkTest.model.Car;
import edu.northeastern.sv.khoury.smartParkTest.model.MembershipSystem;
import edu.northeastern.sv.khoury.smartParkTest.model.Vehicle;
import edu.northeastern.sv.khoury.smartParkTest.model.VehicleType;
import java.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;
import java.time.Duration;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Test code for ParkingCustomerController.
 */
public class ParkingCustomerControllerTest {

  private ParkingManagerMock parkingManager;
  private PaymentSystemMock paymentSystem;
  private MembershipSystem membershipSystem;
  private ParkingCustomerController controller;
  private ParkingCustomerViewMock view;

  /**
   * Set up and initialize the components.
   */
  @Before
  public void setUp() {
    membershipSystem = new MembershipSystem();
    parkingManager = new ParkingManagerMock(membershipSystem);
    paymentSystem = new PaymentSystemMock();
    controller = new ParkingCustomerController(parkingManager, paymentSystem);
    view = new ParkingCustomerViewMock();
    controller.setView(view);
  }

  /**
   * Test for the ParkVehicleButton for two situations. One assertion is parking success, the other
   * is parking failure when parked the same vehicle twice.
   */
  @Test
  public void testParkVehicleButton() {
    view.setVehicleType(VehicleType.CAR);
    view.setLicensePlateInput("ABC123");
    controller.optionExecution("Park Vehicle Button");
    String expected = "Vehicle parked successfully! Vehicle parking place: C12";
    assertEquals(expected, view.getMessage());

    // Park the same vehicle twice, expected failure.
    view.setLicensePlateInput("ABC123");
    controller.optionExecution("Park Vehicle Button");
    String expectedFail = "Parking failed!";
    assertEquals(expectedFail, view.getMessage());
  }

  /**
   * Test for the ParkVehicleButton when parking lot is fully for one vehicle type.
   */
  @Test
  public void testParkVehicleButton_ParkingLotFull() {
    view.setVehicleType(VehicleType.MOTORBIKE);
    view.setLicensePlateInput("ABC129");
    controller.optionExecution("Park Vehicle Button");
    String expected = "Parking failed!";
    assertEquals(expected, view.getMessage());
  }

  /**
   * Test for ProcessPaymentButton when vehicle is parked in the parking lot.
   */
  @Test
  public void testProcessPaymentButtonVehicleParked() {
    LocalDateTime arrivalTime = LocalDateTime.now().minus(Duration.ofHours(1));
    Vehicle vehicle = new Car("ABC124", VehicleType.CAR, arrivalTime, null, null, membershipSystem);
    parkingManager.getParkedVehicles().put("ABC124", vehicle);

    view.setLicensePlateInput("ABC124");
    controller.optionExecution("Process Payment Button");
    String expected = "Payment processed successfully! Please leave within 20 minutes.";
    assertEquals(expected, view.getMessage());
    assertNotNull(view.getParkedDuration());
  }

  /**
   * Test for ProcessPaymentButton when vehicle is not currently parked in the parking lot.
   */
  @Test
  public void testProcessPaymentButtonVehicleNotInParkingLot() {
    String licensePlate = "ABC123";

    view.setLicensePlateInput(licensePlate);
    controller.optionExecution("Process Payment Button");
    String expected = "Vehicle with license plate ABC123 not found in the parking lot.";
    assertEquals(expected, view.getMessage());
  }

  /**
   * Test for ProcessToLeaveButton when vehicle has not paid first.
   */
  @Test
  public void testProcessToLeaveButton() {
    LocalDateTime arrivalTime = LocalDateTime.now().minus(Duration.ofHours(1));
    Vehicle vehicle = new Car("ABC123", VehicleType.CAR, arrivalTime, null, null, null);
    parkingManager.getParkedVehicles().put("ABC123", vehicle);
    view.setLicensePlateInput("ABC123");

    controller.optionExecution("Process to Leave Button");
    String expected = "Vehicle with license plate ABC123 has not paid the parking fee. Please pay first!";
    assertEquals(expected, view.getMessage());
  }

  /**
   * Test for ProcessToLeaveButton when vehicle is not parked in parking lot.
   */
  @Test
  public void testProcessToLeaveButtonWithDoesNotExistVehicle() {
    view.setLicensePlateInput("ABC124");

    controller.optionExecution("Process to Leave Button");
    String expected = "Vehicle with license plate ABC124 not found in the parking lot.";
    assertEquals(expected, view.getMessage());
  }
}