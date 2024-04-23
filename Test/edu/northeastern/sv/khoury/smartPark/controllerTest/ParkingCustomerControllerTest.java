package edu.northeastern.sv.khoury.smartPark.controllerTest;

import edu.northeastern.sv.khoury.smartPark.controller.ParkingCustomerController;
import edu.northeastern.sv.khoury.smartPark.mock.ParkingCustomerJFrameViewMock;
import edu.northeastern.sv.khoury.smartPark.mock.ParkingManagerMock;
import edu.northeastern.sv.khoury.smartPark.mock.PaymentSystemMock;
import edu.northeastern.sv.khoury.smartPark.model.Car;
import edu.northeastern.sv.khoury.smartPark.model.MembershipSystem;
import edu.northeastern.sv.khoury.smartPark.model.VehicleType;
import edu.northeastern.sv.khoury.smartPark.view.IParkingCustomerView;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test code for ParkingCustomerController.
 */
public class ParkingCustomerControllerTest {
  private PaymentSystemMock paymentSystem;
  private ParkingManagerMock parkingManager;
  private MembershipSystem membershipSystem;
  private ParkingCustomerController controller;
  private StringBuilder log;
  private IParkingCustomerView view;

  /**
   * Set up and initialize the components.
   */
  @Before
  public void setUp() {
    log = new StringBuilder();
    int uniqueCode = 12345;

    paymentSystem = new PaymentSystemMock(log, uniqueCode);
    membershipSystem = new MembershipSystem();
    parkingManager = new ParkingManagerMock(membershipSystem, log, uniqueCode);

    view = new ParkingCustomerJFrameViewMock(log);
    controller = new ParkingCustomerController(parkingManager, paymentSystem, view);
  }

  /**
   * Test code for pak vehicle button.
   * Verifies that the vehicle is parked successfully and a unique code is generated.
   */
  @Test
  public void testParkVehicleButton() {
    VehicleType vehicleType = VehicleType.CAR;
    String option = "Park Vehicle Button";
    controller.optionExecution(option);

    assertEquals("CAR", vehicleType.toString());
    assertTrue(log.toString().contains("Parking Successful! Unique Code: 12345"));
  }

  /**
   * Test code for process payment button when the vehicle is not parked.
   * Verifies that the vehicle is not parked and a process payment should fail.
   */
  @Test
  public void testProcessPaymentButton() {
    Car car = new Car("ABC123", VehicleType.CAR, null, null, null, membershipSystem);
    String option = "Process Payment Button";
    controller.optionExecution(option);
    paymentSystem.processPayment(car);
    assertTrue(log.toString().contains("Vehicle with license plate null not found in the parking lot."));
  }

  /**
   * Test code for process payment button.
   * Verifies that the vehicle is processed to leave and the gate is opened.
   */
  @Test
  public void testProcessToLeaveButton() {
    Car car = new Car("ABC123", VehicleType.CAR, null, null, null, membershipSystem);
    parkingManager.parkVehicle(car);
    String option = "Process to Leave Button";
    controller.optionExecution(option);

    boolean gateOpened = parkingManager.processToLeave(car);
    assertTrue(gateOpened);
    assertTrue(log.toString().contains("Mock process to leave method."));
  }
}