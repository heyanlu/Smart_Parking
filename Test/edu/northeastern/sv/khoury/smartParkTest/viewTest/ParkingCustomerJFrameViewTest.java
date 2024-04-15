package edu.northeastern.sv.khoury.smartParkTest.viewTest;

import edu.northeastern.sv.khoury.smartParkTest.mock.ParkingCustomerJFrameViewMock;
import edu.northeastern.sv.khoury.smartParkTest.model.VehicleType;
import org.junit.Before;
import org.junit.Test;
import java.time.Duration;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Test code for ParkingCustomerJFrameView.
 */
public class ParkingCustomerJFrameViewTest {
  private ParkingCustomerJFrameViewMock view;

  /**
   * Initialize components for the test
   */
  @Before
  public void setUp() {
    view = new ParkingCustomerJFrameViewMock("Test");
  }

  /**
   * Test that ensures all buttons in the view are not null.
   */
  @Test
  public void testButtonsNotNull() {
    assertNotNull("Park Vehicle button should not be null", view.getParkVehicleButton());
    assertNotNull("Process Payment button should not be null", view.getProcessPaymentButton());
    assertNotNull("Leave Parking Lot button should not be null", view.getLeaveParkingLotButton());
    assertNotNull("Exit button should not be null", view.getExitButton());
  }

  /**
   * Test that verifies the action commands of the buttons.
   */
  @Test
  public void testButtonsActionCommands() {
    assertEquals("Park Vehicle button should have action command 'Park Vehicle Button'", "Park Vehicle Button", view.getParkVehicleButton().getActionCommand());
    assertEquals("Process Payment button should have action command 'Process Payment Button'", "Process Payment Button", view.getProcessPaymentButton().getActionCommand());
    assertEquals("Leave Parking Lot button should have action command 'Process to Leave Button'", "Process to Leave Button", view.getLeaveParkingLotButton().getActionCommand());
    assertEquals("Exit button should have action command 'Exit Button'", "Exit Button", view.getExitButton().getActionCommand());
  }

  /**
   * Test that the view can getLicensePlateInput method.
   */
  @Test
  public void testLicensePlateInput() {
    view.setLicensePlateInput("ABC123");
    assertEquals("ABC123", view.getLicensePlateInput());
  }

  /**
   * Test the chooseVehicleType methods.
   */
  @Test
  public void testChooseVehicleType() {
    view.setVehicleType(VehicleType.CAR);

    assertEquals(VehicleType.CAR, view.chooseVehicleType());
  }

  /**
   * Test the getInput methods.
   */
  @Test
  public void testGetInput() {
    view.setInput("Test input");
    assertEquals("Test input", view.getInput("Prompt"));
  }

  /**
   * Test for getLastDisplayedDuration method.
   */
  @Test
  public void testDisplayParkedDuration() {
    Duration duration = Duration.ofHours(1).plusMinutes(30);
    view.displayParkedDuration(duration);
    assertEquals( duration, view.getLastDisplayedDuration());
  }

  /**
   * Test for showOptionError method
   */
  @Test
  public void testShowOptionError() {
    view.showOptionError();
  }
}
