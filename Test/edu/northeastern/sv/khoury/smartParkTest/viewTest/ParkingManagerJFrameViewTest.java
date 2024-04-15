package edu.northeastern.sv.khoury.smartParkTest.viewTest;

import edu.northeastern.sv.khoury.smartParkTest.mock.ParkingManagerJFrameViewMock;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Test code for ParkingManagerJFrameView.
 */
public class ParkingManagerJFrameViewTest {
  private ParkingManagerJFrameViewMock view;

  @Before
  public void setUp() {
    view = new ParkingManagerJFrameViewMock("Test");
  }

  @Test
  public void testButtonsNotNull() {
    assertNotNull(view.totalParkingCapacityButton());
    assertNotNull( view.availableParkingCapacityButton());
    assertNotNull(view.vehicleDetailsButton());
    assertNotNull(view.membershipStatusButton());
    assertNotNull( view.getExitButton());
  }

  @Test
  public void testButtonsActionCommands() {
    assertEquals("Total Parking Capacity", view.totalParkingCapacityButton().getActionCommand());
    assertEquals("Available Parking Capacity", view.availableParkingCapacityButton().getActionCommand());
    assertEquals( "Vehicle Details", view.vehicleDetailsButton().getActionCommand());
    assertEquals("Membership Status", view.membershipStatusButton().getActionCommand());
    assertEquals("Exit", view.getExitButton().getActionCommand());
  }


  @Test
  public void testGetInput() {
    String expect = "Test input";
    view.setInput(expect);
    assertEquals(expect, view.getInput("Prompt"));
  }


  @Test
  public void testGetInputNull() {
    assertNull(view.getInput("Prompt"));
  }


  @Test
  public void testDisplayMessage() {
    String expect = "Test message";
    view.displayMessage(expect);
    assertEquals(expect, view.getLastDisplayedMessage());
  }

  @Test
  public void testDisplayMessageNull() {
    assertNull(view.getLastDisplayedMessage());
  }

  @Test
  public void testShowOptionError() {
    view.showOptionError();
  }
}
