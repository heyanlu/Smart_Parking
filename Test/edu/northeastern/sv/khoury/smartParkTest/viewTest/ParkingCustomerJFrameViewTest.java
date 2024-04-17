package edu.northeastern.sv.khoury.smartParkTest.viewTest;

import edu.northeastern.sv.khoury.smartParkTest.mock.ParkingCustomerJFrameViewMock;
import org.junit.Before;
import org.junit.Test;
import java.time.Duration;

import static org.junit.Assert.assertEquals;

/**
 * Test code for ParkingCustomerJFrameView.
 * This test code not suppose to go into the detail of the view. It only check if the input and output
 * can be updated accordingly.
 */
public class ParkingCustomerJFrameViewTest {
  private ParkingCustomerJFrameViewMock viewMock;
  private StringBuilder log;

  /**
   * Sets up the test environment.
   */
  @Before
  public void setUp() {
    log = new StringBuilder();
    viewMock = new ParkingCustomerJFrameViewMock(log);
  }

  /**
   * Tests the getLicensePlateInput method.
   */
  @Test
  public void testGetLicensePlateInput() {
    String result = viewMock.getLicensePlateInput();
    assertEquals("Mock getLicensePlateInput called.", log.toString());
  }

  /**
   * Tests the displayMessage method.
   */
  @Test
  public void testDisplayMessage() {
    viewMock.displayMessage("Test message");
    assertEquals("Mock displayMessage called.", log.toString());
  }

  /**
   * Tests the getInput method.
   */
  @Test
  public void testGetInput() {
    viewMock.getInput("Test prompt");
    assertEquals("Mock getInput called.", log.toString());
  }

  /**
   * Tests the displayParkedDuration method.
   */
  @Test
  public void testDisplayParkedDuration() {
    viewMock.displayParkedDuration(Duration.ofMinutes(30));
    assertEquals("Mock displayParkedDuration called.", log.toString());
  }

  /**
   * Tests the chooseVehicleType method.
   */
  @Test
  public void testChooseVehicleType() {
    viewMock.chooseVehicleType();
    assertEquals("Mock chooseVehicleType called.", log.toString());
  }

  /**
   * Tests the showOptionError method.
   */
  @Test
  public void testShowOptionError() {
    viewMock.showOptionError();
    assertEquals("Mock showOptionError called.", log.toString());
  }
}
